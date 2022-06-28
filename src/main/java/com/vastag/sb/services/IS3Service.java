package com.vastag.sb.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.springframework.web.multipart.MultipartFile;

public interface IS3Service {

	public URI uploadFile(String localFilePath);
	
	public URI uploadFile(MultipartFile file) throws IOException;
	
	public URI uploadFile(InputStream is, String fileName, String contentType);
}
