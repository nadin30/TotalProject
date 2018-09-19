package wiki;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.IOException;

import static org.openqa.selenium.chrome.ChromeOptions.CAPABILITY;

public class WebdriverConfig {

    private static final String CHROME = "Chrome";
    private static final String FIREFOX = "Firefox";
    private static final String REMOTE_EDGE = "RemoteEdge";
    private static final String EDGE = "Edge";
    private static final String HTMLUNIT = "htmlUnit";
    private static final String USERNAME = "anton1007";
    private static final String AUTOMATE_KEY = "123";
    private static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";


    private static String browserName = "Chrome";

    public static WebDriver webDriver(String chrome) throws IOException {
        WebDriver res = null;
       DriverHolder.setInstance(res);
        if (chrome.compareTo("Chrome") == 0){
            res=initChrome();
        }else if (chrome.compareTo("Firefox") == 0){
            res=initFirefox();
        }else if(chrome.compareTo("Edge") == 0){
            res=initEdge();
        }

        return res;
    }

    private static WebDriver initFirefox() {
        FirefoxDriverManager.getInstance().arch32().setup();
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        //return new WrappedWebdriver(driver);
        return (driver);
    }

    private static WebDriver initChrome() {
        ChromeDriverManager.getInstance().setup();
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-gpu", "--no-sandbox");
        //chromeOptions.addArguments("--headless");
        capabilities.setCapability(CAPABILITY, chromeOptions);
        WebDriver driver = new ChromeDriver(capabilities);
        driver.manage().window().maximize();
        return (driver);
    }

    private static WebDriver initEdge() {
        EdgeDriverManager.getInstance().setup();
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        return (driver);
    }



}
