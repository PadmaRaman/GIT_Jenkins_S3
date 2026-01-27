package page_tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import page_objects.LoginPageObjects;
import page_objects.ProductsPageObjects;

import java.sql.SQLOutput;


public class LoginPageTests extends BaseTests
{
    LoginPageObjects loginPageObject;
    ProductsPageObjects productPageObject;

    private static final Logger logger = LogManager.getLogger(LoginPageTests.class);

    @Test
    public void userLoginTest()
    {
        String username = "standard_user";
        String password = "secret_sauce";
        loginPageObject = new LoginPageObjects(driver);
        productPageObject = new ProductsPageObjects(driver);
        loginPageObject.userLogin("https://www.saucedemo.com/",username,password);
        logger.info("Username is:"+username+"Password is"+password);
        //System.out.println(productPageObject.getTitleofProductPage());
        logger.info("=========================================");
        logger.info("Title of the Products Page is"+productPageObject.getTitleofProductPage());
        //System.out.println(productPageObject.getFirstTitleFromLabels());
        logger.info("=========================================");
        logger.info("First Title from the form labels is"+productPageObject.getFirstTitleFromLabels());
        //System.out.println(driver.findElement(By.className("title")));
    }

}
