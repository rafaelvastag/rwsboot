package com.vastag.sb.services;

import org.springframework.mail.SimpleMailMessage;

import com.vastag.sb.domain.Pedido;

public interface IEmailService {

	public void sendOrderConfirmation(Pedido obj);
	
	public void sendEmail(SimpleMailMessage msg);
}
