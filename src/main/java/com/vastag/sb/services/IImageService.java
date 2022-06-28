package com.vastag.sb.services;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface IImageService {
	public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile);

	public BufferedImage cropSquare(BufferedImage jpgImage);

	public BufferedImage resize(BufferedImage jpgImage, Integer size);

	public InputStream getInputStream(BufferedImage jpgImage, String string);
}
