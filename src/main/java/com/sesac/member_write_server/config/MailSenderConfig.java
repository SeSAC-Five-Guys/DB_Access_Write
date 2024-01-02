package com.sesac.member_write_server.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource("classpath:application-dev.yaml")
public class MailSenderConfig {
	@Value("${spring.mail.host}")
	private String host;

	@Value("${spring.mail.port}")
	private Integer port;

	@Value("${spring.mail.username}")
	private String adminMail;

	@Value("${spring.mail.password}")
	private String adminPassword;

	@Value("${spring.mail.properties.transport.protocol}")
	private String protocol;

	@Value("${spring.mail.properties.mail.smtp.auth}")
	private String auth;

	@Value("${spring.mail.properties.mail.smtp.starttls.enable}")
	private String starttls;

	@Value("${spring.mail.default-encoding}")
	private String encoding;
	@Bean
	public JavaMailSender javaMailService() {
		final JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost(host);
		javaMailSender.setUsername(adminMail);
		javaMailSender.setPassword(adminPassword);
		javaMailSender.setPort(port);
		javaMailSender.setDefaultEncoding(encoding);
		javaMailSender.setProtocol(protocol);
		javaMailSender.setJavaMailProperties(getMailProperties());
		return javaMailSender;
	}

	private Properties getMailProperties() {
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth", auth );
		properties.setProperty("mail.smtp.starttls.enable", starttls);
		return properties;
	}
}
