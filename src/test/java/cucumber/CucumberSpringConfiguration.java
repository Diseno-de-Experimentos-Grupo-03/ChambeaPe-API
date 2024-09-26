package cucumber;

import com.digitaldark.ChambeaPe_Api.ChambeaPeApiApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = ChambeaPeApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = ChambeaPeApiApplication.class, loader = SpringBootContextLoader.class)
public class CucumberSpringConfiguration {
}
