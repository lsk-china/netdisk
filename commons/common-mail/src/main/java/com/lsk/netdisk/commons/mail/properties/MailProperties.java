package com.lsk.netdisk.commons.mail.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("lsk.mail")
public class MailProperties {
	private String myEmailAddress;
	private String myEmailPassword;
	private String myEmailSMTPHost;
	private Boolean auth;
}
