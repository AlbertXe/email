package com.email;

import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.email.hello.EmailService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTest {
	String to = "563000409@qq.com";
	@Autowired
	EmailService emailService;
	@Test
	public void sendSimple() {
		emailService.sendSimpleMail("563000409@qq.com", "first", "大家好，这是第一封邮件");
	}
	@Test
	public void sendHtml() {
		String text = "<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "<title>测试邮件2</title>"
				+ "<meta name=\"content-type\" content=\"text/html; charset=UTF-8\">"
				+ "</head>"
				+ "<body>"
				+ "<p style=\"color:#0FF\">这是一封测试邮件~</p>"
				+ "</body>"
				+ "</html>";
		try {
			emailService.sendHtmlEmail(to, "这是第二封邮件", text);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void sendAttchmentsMail() throws MessagingException{
		String filePath = "email.zip";
		emailService.sendAttchmentMail(to, "带邮件", "这是一篇带附件的邮件", filePath);
	}
}
