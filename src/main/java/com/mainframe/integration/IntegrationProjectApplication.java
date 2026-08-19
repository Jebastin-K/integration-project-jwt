package com.mainframe.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*import com.mainframe.integration.service.PaymentService; */


@SpringBootApplication
public class IntegrationProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegrationProjectApplication.class, args);

		/*System.out.println(context.getBean(PaymentService.class));*/

	}
}
