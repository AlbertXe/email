package com.email;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.email.hello.EmailService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailApplicationTests {
	@Autowired
	EmailService emailService;
	@Test
	public void contextLoads() {
		emailService.sayHello();
	}

}
