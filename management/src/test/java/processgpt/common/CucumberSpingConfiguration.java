package processgpt.common;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import processgpt.ManagementApplication;

@CucumberContextConfiguration
@SpringBootTest(classes = { ManagementApplication.class })
public class CucumberSpingConfiguration {}
