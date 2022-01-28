package com.ssafy.core.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface Uploader {

	String upload(String base64File, String dirName);

	String upload(MultipartFile file, String dirName) throws IOException, Exception;
}
