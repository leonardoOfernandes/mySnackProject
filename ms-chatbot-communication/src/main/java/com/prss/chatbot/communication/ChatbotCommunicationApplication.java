package com.prss.chatbot.communication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class ChatbotCommunicationApplication {
	public static void main(String[] args) {
		SpringApplication.run(ChatbotCommunicationApplication.class, args);
	}
}
