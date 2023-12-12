package processgpt.common;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import processgpt.TodolistApplication;

@CucumberContextConfiguration
@SpringBootTest(classes = { TodolistApplication.class })
public class CucumberSpingConfiguration {}
