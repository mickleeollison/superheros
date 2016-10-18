package superheroTeams.SeleniumFramework.Pages;

import org.openqa.selenium.WebDriver;

import superheroTeams.SeleniumFramework.PageObject;

public class TeamsPage extends PageObject {
    public TeamsPage(WebDriver driver) {
        super(driver);
        goTo("http://localhost:8080/team");
    }
}
