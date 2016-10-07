package superheroTeams.SeleniumFramework.Pages;

import org.openqa.selenium.WebDriver;

import supeheroTeams.SeleniumFramework.PageObject;

public class HeroPage extends PageObject {
    public HeroPage(WebDriver driver) {
        super(driver);
        goTo("http://localhost:8080/hero");
    }

}
