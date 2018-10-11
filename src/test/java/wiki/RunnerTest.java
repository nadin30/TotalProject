package wiki;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
@RunWith(value = Cucumber.class)
@CucumberOptions(
        features = "src/test/resourses/iUATest",
        glue = "wiki",
        tags = "@all"
)
public class RunnerTest {


}