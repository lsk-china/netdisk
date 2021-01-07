package com.lsk.netdisk.commons.mail;

import com.lsk.netdisk.commons.mail.core.MailSender;
import com.lsk.netdisk.commons.mail.properties.MailProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(MailProperties.class)
public class MailAutoConfigure {
	private final MailProperties properties;

	@Bean(name="mailSender")
	public MailSender mailSender(){
		log.info("Creating bean: MailSender");
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol","smtp");
		properties.setProperty("mail.smtp.host",this.properties.getMyEmailSMTPHost());
		properties.setProperty("mail.smtp.auth",this.properties.getAuth().toString());
		//properties.setProperty("mail.smtp.ssl.enable",new Boolean(true).toString());
		return new MailSender(properties,this.properties.getMyEmailAddress(),this.properties.getMyEmailPassword());
	}
}
