package io.camunda.onboarding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.camunda.zeebe.spring.client.annotation.Deployment;

@SpringBootApplication
@Deployment(resources =  {"classpath*:*.bpmn", "classpath*:*.dmn"})
public class ProcessOnboardingSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessOnboardingSpringbootApplication.class, args);
	}

}
