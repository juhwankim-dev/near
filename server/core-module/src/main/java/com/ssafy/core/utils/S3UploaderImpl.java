package com.ssafy.core.utils;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ssafy.core.exception.FileNotSupportException;
import com.ssafy.core.exception.FileSizeException;
import com.ssafy.core.exception.FileUploadException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3UploaderImpl implements Uploader {

	private final static String TEMP_FILE_PATH = "src/main/resources/";

	// application yml 설정파일에 설정한 값 사용
	@Value("${spring.servlet.multipart.location}")
	private String uploadPath = "";

	// application yml 설정파일에 설정한 값 사용
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	// application yml 설정파일에 설정한 값 사용
	@Value("${cloud.aws.path}")
	private String folderDir;

	private final AmazonS3Client amazonS3Client;

	@Override
	public String upload(String base64File, String dirName) {
		return null;
	}

	public String upload(MultipartFile multipartFile, String dirName, String sizeChkYN) throws Exception {
		if(sizeChkYN != null && sizeChkYN.equals("N")) {
			fileExtensionCheck(multipartFile);

			File convertedFile = convert(multipartFile);
			return upload(convertedFile, dirName);

		} else {
			return upload(multipartFile, dirName);
		}
	}

	public String uploadEx(MultipartFile multipartFile, String dirName) throws Exception {
		File convertedFile = convert(multipartFile);
		return upload(convertedFile, dirName);
	}

	public String uploadShop(MultipartFile multipartFile, String dirName) throws Exception {
		fileExtensionCheck(multipartFile);
		File convertedFile = convert(multipartFile);
		return upload(convertedFile, dirName);
	}

	// upload width 추가로 받는 메서드 생성
	public String uploadToResize(MultipartFile multipartFile, String dirName, int width) throws Exception {
		if(multipartFile == null || multipartFile.isEmpty())
			return "";

		File convertedFile = convert(multipartFile);

		convertedFile = resizeImageFile(convertedFile, width);
		return upload(convertedFile, dirName);
	}


	public String upload(MultipartFile multipartFile, String dirName) throws Exception {
		fileExtensionCheck(multipartFile);

		File convertedFile = convert(multipartFile);
		fileSizeCheck(convertedFile);
		return upload(convertedFile, dirName);
	}

	public String uploadPdf(MultipartFile multipartFile, String dirName) throws Exception {
		fileExtensionPdefCheck(multipartFile);
		File convertedFile = convert(multipartFile);
		return upload(convertedFile, dirName);
	}

	private String upload(File uploadFile, String dirName) {
		String fileName = dirName + "/" + uploadFile.getName();
		String uploadImageUrl = putS3(uploadFile, fileName);
		removeNewFile(uploadFile);
		return uploadImageUrl;
	}

	public String uploadForVideo(MultipartFile multipartFile, String dirName) throws Exception {
		File convertedFile = convert(multipartFile);
		return upload(convertedFile, dirName);
	}

	private String putS3(File uploadFile, String fileName) {
		amazonS3Client.putObject(new PutObjectRequest(bucket, folderDir + fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
		return amazonS3Client.getUrl(bucket, folderDir + fileName).toString();
	}

	private void removeNewFile(File targetFile) {
		if (targetFile.delete()) {
			return;
		}
		log.info("임시 파일이 삭제 되지 못했습니다. 파일 이름: {}", targetFile.getName());
	}

	private File convert(MultipartFile file) throws IOException {

		String originalfileName = file.getOriginalFilename();
		String extension = originalfileName.substring(originalfileName.lastIndexOf("."), originalfileName.length());
		UUID uuid = UUID.randomUUID();
		String newFileName = uuid.toString() + extension;

		File convertFile = new File(uploadPath + "/temp/" + newFileName);
		if (convertFile.createNewFile()) {
			try (FileOutputStream fos = new FileOutputStream(convertFile)) {
				fos.write(file.getBytes());
			}
			return convertFile;
		}
		throw new IllegalArgumentException(String.format("파일 변환이 실패했습니다. 파일 이름: %s", file.getName()));
	}


	public void copy(String orgKey, String copyKey) {
		try {

			//Copy 객체 생성
			CopyObjectRequest copyObjRequest = new CopyObjectRequest(
					bucket,
					orgKey,
					bucket,
					copyKey
			);
			//Copy
			amazonS3Client.copyObject(copyObjRequest);

			System.out.println(String.format("Finish copying [%s] to [%s]", orgKey, copyKey));

		} catch (AmazonServiceException e) {
			e.printStackTrace();
		} catch (SdkClientException e) {
			e.printStackTrace();
		}
	}

	public void delete(String key) {
		if(key == null || key.equals(""))
			return;

		try {
			//Delete 객체 생성
			DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucket, key);
			//Delete
			amazonS3Client.deleteObject(deleteObjectRequest);
			System.out.println(String.format("[%s] deletion complete", key));

		} catch (AmazonServiceException e) {
			e.printStackTrace();
		} catch (SdkClientException e) {
			e.printStackTrace();
		}
	}


	public void fileExtensionCheck(MultipartFile mfile) throws Exception{
		if ( mfile == null ) {
			throw new FileUploadException();
		}
		String originalfileName = mfile.getOriginalFilename();
		String extension = originalfileName.substring(originalfileName.lastIndexOf("."), originalfileName.length());

		if ( extension.toLowerCase().equals(".png") ) {
			extension = ".png";
		} else if ( extension.toLowerCase().equals(".jpeg") ) {
			extension = ".jpeg";
		} else if ( extension.toLowerCase().equals(".jpg") ) {
			extension = ".jpg";
		} else {
			throw new FileNotSupportException("");
		}
	}


	public void fileExtensionPdefCheck(MultipartFile mfile) throws Exception{
		if ( mfile == null ) {
			throw new FileUploadException();
		}
		String originalfileName = mfile.getOriginalFilename();
		String extension = originalfileName.substring(originalfileName.lastIndexOf("."), originalfileName.length());
		if ( extension.toLowerCase().equals(".pdf") ) {
			extension = ".pdf";
		} else {
			throw new FileNotSupportException("");
		}
	}

	public void fileSizeCheck(File dest) throws Exception{
		try {
			BufferedImage bi = ImageIO.read( dest );
			if ( bi.getWidth() > 5000  ) {
				throw new FileSizeException();
			}
		} catch( FileSizeException e ) {
			if ( dest.exists() ) {
				dest.delete();
			}
			throw new FileSizeException();
		} catch( Exception e ) {
			if ( dest.exists() ) {
				dest.delete();
			}
			throw new FileNotSupportException();
		}
	}


	private File resizeImageFile(File file, int width) throws Exception {
		Image originalImage = ImageIO.read(file);

		int originWidth = originalImage.getWidth(null);
		int originHeight = originalImage.getHeight(null);

		int newWidth = width;

		if (originWidth > newWidth) {
			int newHeight = (originHeight * newWidth) / originWidth;

			try {
				Image resizeImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

				BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
				Graphics graphics = newImage.getGraphics();
				graphics.drawImage(resizeImage, 0, 0, null);
				graphics.dispose();


				String resizeImgName = file.getName().substring(0, file.getName().indexOf("."));
				File newFile = new File(resizeImgName);
				String formatName = file.getName().substring(file.getName().indexOf(".") + 1);
				ImageIO.write(newImage, formatName, newFile);

				return newFile;
			}catch (Exception e){
				e.printStackTrace();
				return file;
			}
		} else {
			return file;
		}
	}

}
