package wiki;

import org.openqa.selenium.WebDriver;

public class DriverHolder {
    private static WebDriver instance = null;

    public static void setInstance(WebDriver instance) {
        DriverHolder.instance = instance;
    }

    public static synchronized WebDriver getInstance() {
        try {
//            if (instance == null) {
                instance = WebdriverConfig.webDriver("Chrome");
//            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return instance;
    }

    public static synchronized void restart() {
        try {
            if (instance != null) {
                instance.close();
                instance =  WebdriverConfig.webDriver("Chrome");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
