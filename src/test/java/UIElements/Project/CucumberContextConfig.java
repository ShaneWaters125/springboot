package UIElements.Project;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = ProjectApplication.class)
public class CucumberContextConfig {
}
