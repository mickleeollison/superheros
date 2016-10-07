package supeheroTeams.SeleniumFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public abstract class PageObject {
    private WebDriver driver;

    
    private By BODY = By.xpath("//body");

    public PageObject(WebDriver driver){
        this.driver = driver;
    }

  
    public void click(By by){
        find(by).click();
    }

  
    public WebElement find(By by){
        return driver.findElement(by);
    }

    public String getInnerHtml(By by){
        return find(by).getText();
    }

 
    public String getText(By by){
        return find(by).getText();
    }

    
    public String getTitle(){
        return driver.getTitle();
    }

   
    public String getUrl(){
        return driver.getCurrentUrl();
    }


    public void goTo(String url){
        driver.get(url);
    }

  
    public void sendKeys(By by, String value){
        find(by).sendKeys(value);
    }

   
    public void selectByText(By by, String text){
        Select select = new Select(find(by));
        select.selectByVisibleText(text);
    }

}
