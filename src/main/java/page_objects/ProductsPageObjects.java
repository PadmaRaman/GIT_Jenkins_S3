package page_objects;

import generic_keywords.WebElementInteractions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPageObjects extends WebElementInteractions {

    private final By retrieveText = By.className("title");
    private final By labelText = By.xpath("//a[@id='item_4_title_link']/div");
    public ProductsPageObjects(WebDriver driver)
    {
        super(driver);
    }

    public String getTitleofProductPage()
    {
        return retriveTextData(retrieveText);
    }

    public String getFirstTitleFromLabels()
    {
        return retriveTextData(labelText);
    }
}
