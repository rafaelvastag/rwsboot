package com.vastag.sb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class S3Config {
	
	@Value("${aws.access_key_id}")
	private String id;
	
	@Value("${aws.secret_access_key}")
	private String secret;
	
	@Value("${aws.s3.region}")
	private String region;

	@Bean
	public AmazonS3 s3client() {
		BasicAWSCredentials credentials = new BasicAWSCredentials(id, secret);
		AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(region))
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
		
		return s3Client;
	}
}
