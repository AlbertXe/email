package com.email.hello;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.carrotsearch.ant.tasks.junit4.dependencies.com.google.common.io.Files;

@Service
public class EmailService {
	@Value("${spring.mail.username}")
	private String from;
	@Autowired
	private JavaMailSender mailSender;
	
	public void sayHello(){
		System.out.println("Hello World!");
	}
	
	public void sendSimpleMail(String to,String subject,String text){
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		message.setFrom(from);
		
		mailSender.send(message);
	}
	
	/**
	 * 发送html
	 * @param to
	 * @param subject
	 * @param text
	 * @throws MessagingException
	 */
	public void sendHtmlEmail(String to ,String subject,String text) throws MessagingException{
		MimeMessage message = mailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message,true);
		
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text);
		mailSender.send(message);
	}
	/**
	 * 发送带附件
	 * @throws MessagingException 
	 */
	public void sendAttchmentMail(String to ,String subject,String text,String filePath) throws MessagingException{
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,true);
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text);
		
		FileSystemResource file = new FileSystemResource(new File(filePath));
		String filename = file.getFilename();
		helper.addAttachment(filename, file);
//		helper.addAttachment(filename+"test", file);// 发送多个附件 直接追加
		mailSender.send(message);
		
	}
	
	/**
	 * 发送图片
	 * @param to
	 * @param subject
	 * @param text
	 * @param srcPath
	 * @param srcId
	 * @throws MessagingException 
	 */
	public void sendInlineResourceMail(String to ,String subject,String text,String srcPath,String srcId) throws MessagingException{
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,true);
		
		helper.setTo(to);
		helper.setFrom(from);
		helper.setSubject(subject);
		helper.setText(text);
		
		FileSystemResource fsr = new FileSystemResource(new File(srcPath));
		helper.addInline(srcId, fsr);
		mailSender.send(message);
	}
	
}
