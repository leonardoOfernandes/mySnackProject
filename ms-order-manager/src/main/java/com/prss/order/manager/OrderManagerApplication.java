package com.prss.order.manager;

import com.prss.order.manager.resources.messaging.stream.InputStream;
import com.prss.order.manager.resources.messaging.stream.OutputStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
@EnableBinding(value = { OutputStream.class, InputStream.class})
@SpringBootApplication
public class OrderManagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(OrderManagerApplication.class, args);
	}
}
