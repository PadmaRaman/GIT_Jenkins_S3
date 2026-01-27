package page_objects;

import generic_keywords.WebElementInteractions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageObjects extends WebElementInteractions {

    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginBtn = By.id("login-button");

    public LoginPageObjects(WebDriver driver)
    {
        super(driver);
    }

    public void userLogin(String url, String username, String password)
    {
        gotoApplication(url);
        inputText(usernameField,username);
        inputText(passwordField,password);
        clickElement(loginBtn);
    }

}
