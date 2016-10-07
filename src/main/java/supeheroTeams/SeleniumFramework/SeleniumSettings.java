package supeheroTeams.SeleniumFramework;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class SeleniumSettings {

    private WebDriver driver;

    private String CHROME_MAC = "src/test/java/superheroTeams/PageObjectFramework/Drivers/chromedriver";
    private String CHROME_WIN = "src/test/java/superheroTeams/PageObjectFramework/Drivers/chromedriver.exe";
    private String CHROME_LNX = "src/test/java/superheroTeams/PageObjectFramework/Drivers/chromedriverlinux";

    public SeleniumSettings(){
        if (SystemUtils.IS_OS_MAC)
            System.setProperty("webdriver.chrome.driver", CHROME_MAC);
        else if (SystemUtils.IS_OS_WINDOWS)
            System.setProperty("webdriver.chrome.driver", CHROME_WIN);
        else if (SystemUtils.IS_OS_LINUX)
            System.setProperty("webdriver.chrome.driver", CHROME_LNX);
        else System.out.println("need to find driver!!!!!");
        driver = new ChromeDriver();
    }

    public WebDriver getDriver(){
        return driver;
    }

    public void setDriver(WebDriver driver){
        this.driver = driver;
    }

}
