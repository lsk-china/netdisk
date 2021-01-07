package com.lsk.netdisk.commons.mail.core;

import lombok.RequiredArgsConstructor;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Date;
import java.util.Properties;

@RequiredArgsConstructor
public class MailSender {
	private final Properties properties;
	private final String myAddress;
	private final String myPassword;

	public void sendEmail(String targetEmailAddress,String subject,String content) throws MessagingException {
		Session session = Session.getInstance(properties);
		session.setDebug(true);
		MimeMessage message = new MimeMessage(session);
		message.setFrom(myAddress);
		message.setRecipient(Message.RecipientType.TO,new InternetAddress(targetEmailAddress));
		message.setSubject(subject,"UTF-8");
		message.setContent(content,"text/html;charset=utf-8");
		message.setSentDate(new Date());
		message.saveChanges();
		Transport transport = session.getTransport();
		transport.connect(myAddress,myPassword);
		transport.sendMessage(message,message.getAllRecipients());
		transport.close();
	}
}
