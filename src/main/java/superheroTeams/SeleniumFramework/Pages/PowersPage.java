package superheroTeams.SeleniumFramework.Pages;

import org.openqa.selenium.WebDriver;

import supeheroTeams.SeleniumFramework.PageObject;

public class PowersPage extends PageObject {
    public PowersPage(WebDriver driver) {
        super(driver);
        goTo("http://localhost:8080/power");
    }
}
