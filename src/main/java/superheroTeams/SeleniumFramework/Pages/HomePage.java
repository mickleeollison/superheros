package superheroTeams.SeleniumFramework.Pages;

import org.openqa.selenium.WebDriver;

import superheroTeams.SeleniumFramework.PageObject;

public class HomePage extends PageObject {
	    public HomePage(WebDriver driver) {
	        super(driver);
	        goTo("http://localhost:8080/");
	    }

}
