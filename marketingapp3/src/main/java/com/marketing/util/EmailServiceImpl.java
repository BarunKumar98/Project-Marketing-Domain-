package com.marketing.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
//it is means now,this class is a special and object creation,maintenence of that object
//and destruction of that object is done by spring boot
@Component
public class EmailServiceImpl implements EmailService {
//To send Emailer
	@Autowired
	JavaMailSender mailsender;
	
	@Override
	public void sendEmail(String to, String subject, String message) {
  		SimpleMailMessage mailmessage=new SimpleMailMessage();
  		mailmessage.setTo(to);
  		mailmessage.setSubject(subject);
  		mailmessage.setText(message);
  		//To send Email
  		mailsender.send(mailmessage);
	}

}
