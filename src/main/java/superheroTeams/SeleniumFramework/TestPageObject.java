package superheroTeams.SeleniumFramework;

import org.openqa.selenium.WebDriver;
import org.junit.After;
import org.junit.Before;


public abstract class TestPageObject {
    protected WebDriver driver;
    SeleniumSettings seleniumSettings;

    @Before
    public void setup(){
        seleniumSettings = new SeleniumSettings();
        driver = seleniumSettings.getDriver();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
