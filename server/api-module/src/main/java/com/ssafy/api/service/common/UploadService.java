package com.ssafy.api.service.common;

import com.ssafy.core.repository.UserRepository;
import com.ssafy.core.utils.Base64Util;
import com.ssafy.core.utils.S3UploaderImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UploadService {
    private final UserRepository userRepository;
    private final S3UploaderImpl s3Uploader;

    // application yml 설정파일에 설정한 값 사용
    @Value("${cloud.aws.urlPrefix}")
    private String prefix;


    public String s3Upload(String img, String dirFolder) throws Exception{
        if (img != null){
            MultipartFile file = Base64Util.base64ToMultipart(img);
            String imageUrl = s3Uploader.uploadToResize(file, dirFolder, 4000);
            return imageUrl;
        }

        return "";
    }


    public void delete(String url){
        if(StringUtils.isEmpty(url))
            return;

        s3Uploader.delete(url.replaceAll(prefix, ""));
    }
}


