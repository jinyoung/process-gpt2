package processgpt.common;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import processgpt.ExecuteApplication;

@CucumberContextConfiguration
@SpringBootTest(classes = { ExecuteApplication.class })
public class CucumberSpingConfiguration {}
