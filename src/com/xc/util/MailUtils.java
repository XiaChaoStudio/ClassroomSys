package com.xc.util;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

public class MailUtils {
	public static Session getSession() throws GeneralSecurityException {
		Properties properties = new Properties();
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.host", "smtp.qq.com");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.debug", "true");

		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.ssl.socketFactory", sf);

		return Session.getInstance(properties);
	}

	public static void sendMail(Session session, MimeMessage mimeMessage) throws MessagingException {
		Transport transport = session.getTransport();
		transport.connect("379299677@qq.com", "fcddxfaoyjmbcbeg");
		transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
	}

}
