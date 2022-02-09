package com.ssafy.near.src.main.game.oxquiz

import android.Manifest
import android.animation.Animator
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import com.ssafy.near.R
import com.ssafy.near.config.BaseFragment
import com.ssafy.near.databinding.FragmentOXQuizBinding
import com.ssafy.near.ml.Model
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.concurrent.timer

class OXQuizFragment : BaseFragment<FragmentOXQuizBinding>(R.layout.fragment_o_x_quiz) {
    private val TAG = "OXQuizFragment"
    private lateinit var oxQuizViewModel: OXQuizViewModel

    private lateinit var cameraExecutor: ExecutorService
    private var imageCapture: ImageCapture? = null
    private val IMAGE_SIZE = 224

    private val classes = arrayOf("네", "아니오", "싫어요", "고맙습니다")
    private var detectCnt = IntArray(4)

    private val GIVEN_TIME = 3.0 // 주어진 시간
    private val CHECK_CNT = 10 // GIVEN_TIME 동안 검사할 횟수
    private val INTERVAL = GIVEN_TIME * 1000 / CHECK_CNT // 검사 하는 시간 간격 (ms)
    private val SUCCESS_RATE = 0.8 // 성공률 80% = 0.8

    private val permissions = arrayOf(
        Manifest.permission.CAMERA
    )

    private val askMultiplePermissionsLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            if (it.all { permission -> permission.value == true }) {
                openCamera()
            } else {
                showToastMessage("카메라 권한을 허용해주세요")
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        oxQuizViewModel = ViewModelProvider(requireActivity(), OXQuizViewModelFactory())
            .get(OXQuizViewModel::class.java)

        if (requireActivity().checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            openCamera()
        } else if (!checkPermission(permissions)) {
            askMultiplePermissionsLauncher.launch(permissions)
        }

        cameraExecutor = Executors.newSingleThreadExecutor()

        initEvent()
        startRound()
    }

    private fun initEvent() {
        binding.lottieViewReadygo.addAnimatorListener(object : Animator.AnimatorListener{
            override fun onAnimationStart(p0: Animator?) {}

            override fun onAnimationEnd(p0: Animator?) {
                binding.viewOpaqueScreen.visibility = View.GONE
                binding.lottieViewReadygo.visibility = View.GONE
                setWord()
                binding.pbTimer.progress = 300
                binding.lottieViewHourglass.playAnimation()

                var time = GIVEN_TIME

                timer(period = INTERVAL.toLong()) {
                    if(time < 0) {
                        requireActivity().runOnUiThread {
                            evaluateAction()
                            binding.lottieViewHourglass.progress = 0.0F
                            binding.lottieViewHourglass.cancelAnimation()
                        }
                        this.cancel()
                    }
                    time -= GIVEN_TIME / CHECK_CNT
                    requireActivity().runOnUiThread {
                        takePhoto()
                    }
                }

                timer(period = 10) {
                    binding.pbTimer.incrementProgressBy(-1)
                }
            }

            override fun onAnimationCancel(p0: Animator?) {}
            override fun onAnimationRepeat(p0: Animator?) {}
        })
    }

    private fun showLottie(lottie: LottieAnimationView) {
        binding.viewOpaqueScreen.visibility = View.VISIBLE
        lottie.apply {
            visibility = View.VISIBLE
            playAnimation()
        }
    }

    private fun startRound() {
        for (i in detectCnt.indices) {
            detectCnt[i] = 0
        }
        oxQuizViewModel.yourAnswer.clear()
        binding.header = "${oxQuizViewModel.currentRound}번째 라운드 퀴즈"

        showLottie(binding.lottieViewReadygo)
    }

    private fun setWord() {
        var word = oxQuizViewModel.question[oxQuizViewModel.currentRound - 1]
        for(i in word.indices) {
            binding.layoutWord.addView(createWord(word[i].toString()))
        }
    }

    private fun evaluateAction() {
        var maxPos = 0
        for (i in detectCnt.indices) {
            if (detectCnt[i] > detectCnt[maxPos]) {
                maxPos = i
            }
        }

        if (oxQuizViewModel.question[oxQuizViewModel.currentRound - 1] == classes[maxPos]
            && (detectCnt[maxPos] / CHECK_CNT.toFloat()) >= SUCCESS_RATE
        ) {
            oxQuizViewModel.yourScore += 20
            (context as OXQuizActivity).onChangeFragment(CorrectFragment())
        } else {
            (context as OXQuizActivity).onChangeFragment(WrongFragment())
        }
    }

    private fun classifyImage(image: Bitmap, originImage: Bitmap) {
        try {
            var model: Model = Model.newInstance(requireContext())

            val inputFeature0: TensorBuffer = TensorBuffer.createFixedSize(intArrayOf(1, IMAGE_SIZE, IMAGE_SIZE, 3), DataType.FLOAT32)
            val byteBuffer = ByteBuffer.allocateDirect(4 * IMAGE_SIZE * IMAGE_SIZE * 3)
            byteBuffer.order(ByteOrder.nativeOrder())

            val intValues = IntArray(IMAGE_SIZE * IMAGE_SIZE)
            image.getPixels(intValues, 0, IMAGE_SIZE, 0, 0, image.width, image.height)

            var pixel = 0
            for (i in 0 until IMAGE_SIZE) {
                for (j in 0 until IMAGE_SIZE) {
                    val values = intValues[pixel++] // RGB

                    byteBuffer.putFloat((values shr 16 and 0xFF) * (1f / 255f))
                    byteBuffer.putFloat((values shr 8 and 0xFF) * (1f / 255f))
                    byteBuffer.putFloat((values and 0xFF) * (1f / 255f))
                }
            }

            inputFeature0.loadBuffer(byteBuffer)

            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer

            val confidences: FloatArray = outputFeature0.floatArray
            // find the index of the class with the biggest confidence.
            var maxPos = 0
            var maxConfidence = 0f
            for (i in confidences.indices) {
                if (confidences[i] > maxConfidence) {
                    maxConfidence = confidences[i]
                    maxPos = i
                }
            }

            detectCnt[maxPos]++

            oxQuizViewModel.yourAnswer.add(originImage)

            model.close()
        } catch (e: IOException) {
            Log.e(TAG, "Photo capture failed: ${e.message}")
        }
    }

    private fun takePhoto() {
        val imageCapture = imageCapture ?: return

        imageCapture.takePicture(
            ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageCapturedCallback() {
                override fun onError(exc: ImageCaptureException) {
                    Log.e(TAG, "Photo capture failed: ${exc.message}", exc)
                }

                @SuppressLint("UnsafeExperimentalUsageError")
                override fun onCaptureSuccess(image: ImageProxy) {
                    val buffer: ByteBuffer = image.planes[0].buffer
                    val bytes = ByteArray(buffer.capacity())
                    buffer.get(bytes)
                    var bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size, null)

                    image.close()

                    val sideInversion = Matrix()
                    sideInversion.setScale(-1.0f, 1.0f) // 좌우반전
                    val flipImage: Bitmap = Bitmap.createBitmap(
                        bitmap, 0, 0,
                        bitmap.width, bitmap.height, sideInversion, false
                    )

                    var cropImage = Bitmap.createScaledBitmap(flipImage, IMAGE_SIZE, IMAGE_SIZE, false)

                    classifyImage(cropImage, flipImage)
                    super.onCaptureSuccess(image)
                }
            })
    }

    private fun openCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.previewView.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder().build()

            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)
            } catch (e: Exception) {
                Log.d(TAG, "openCamera: ${e.message}")
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun createWord(word: String): View {
        val customWordTextView = CustomWordTextView(requireContext())
        customWordTextView.setText(word)
        val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        customWordTextView.layoutParams = lp
        customWordTextView.id = ViewCompat.generateViewId()
        return customWordTextView
    }

    private fun checkPermission(permissions: Array<String>): Boolean {
        return permissions.all {
            ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }
}