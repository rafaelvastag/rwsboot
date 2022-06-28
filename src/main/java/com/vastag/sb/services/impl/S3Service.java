package com.vastag.sb.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.vastag.sb.services.IS3Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class S3Service implements IS3Service {

	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	@Value("${aws.s3.bucket}")
	private String bucket;

	private final AmazonS3 client;

	@Override
	public URI uploadFile(String localFilePath) {
		try {

			LOG.info("Iniciando Upload");
			File file = new File(localFilePath);
			InputStream is = new FileInputStream(file);
			return uploadFile(is, file.getName(), URLConnection.guessContentTypeFromName(file.getName()));
		} catch (AmazonServiceException e) {
			LOG.info("AmazonServiceException: " + e.getErrorMessage());
			LOG.info("Status Code: " + e.getStatusCode());
		} catch (AmazonClientException e) {
			LOG.info("AmazonClientException: " + e.getMessage());
		} catch (FileNotFoundException e) {
			LOG.info("File not found: " + e.getMessage());
		}
		return null;
	}

	@Override
	public URI uploadFile(MultipartFile file) throws IOException {
		LOG.info("Iniciando Upload");
		String fileName = file.getOriginalFilename();
		InputStream is = file.getInputStream();
		String contentType = file.getContentType();
		return uploadFile(is, fileName, contentType);
	}

	@Override
	public URI uploadFile(InputStream is, String fileName, String contentType) {
		try {
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType(contentType);
			client.putObject(bucket, fileName, is, metadata);
			LOG.info("Finalizando Upload");
			return client.getUrl(bucket, fileName).toURI();
		} catch (AmazonServiceException e) {
			LOG.info("AmazonServiceException: " + e.getErrorMessage());
			LOG.info("Status Code: " + e.getStatusCode());
		} catch (AmazonClientException e) {
			LOG.info("AmazonClientException: " + e.getMessage());
		} catch (URISyntaxException e) {
			throw new RuntimeException("Error URL to URI convertion");
		}
		return null;
	}

}
