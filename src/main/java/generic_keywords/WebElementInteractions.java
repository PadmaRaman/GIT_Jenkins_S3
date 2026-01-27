package generic_keywords;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebElementInteractions {
    protected WebDriver driver;

    public WebElementInteractions(WebDriver driver) {
        this.driver=driver;
    }

    //Custom keywords helps with keyword driven strategy
    public void clickElement(By locator)
    {
        driver.findElement(locator).click();
    }

    public void inputText(By locator, String Text)
    {
        driver.findElement(locator).sendKeys(Text);
    }

    public void gotoApplication(String url)
    {
        driver.get(url);
    }

    public String retriveTextData(By locator)
    {
        return driver.findElement(locator).getText();
    }
}
