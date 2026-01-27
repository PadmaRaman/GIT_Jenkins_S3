package page_tests;

import base.AppConstants;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import generic_keywords.WebElementInteractions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Remote;
import java.time.LocalDateTime;

import static utils.ExtentReportHelper.getReporter;

public class BaseTests{

    protected WebDriver driver;
    protected String browser;
    protected String platform;
    protected static ThreadLocal<ExtentTest> testlogger = new ThreadLocal<>(); //to create independent thread
    private static final ExtentReports reports = getReporter();
    private static final Logger logger = LogManager.getLogger(BaseTests.class);
    ChromeOptions co = new ChromeOptions();
    FirefoxOptions fo = new FirefoxOptions();

    @Parameters({"browserName"})
    @BeforeMethod
    public void setuptest(@Optional String browserName, ITestResult iTestResult)
    {
        //browser = AppConstants.browserName; - From AppConstants class
        //browser = browserName;  From testng.xml file

        if(browserName != null)
        {
            browser = browserName;
        }
        else
        {
            browser = AppConstants.browserName;
        }
        System.out.println(browser);

        platform = AppConstants.platform;
        if(platform.equalsIgnoreCase("local")) {
            if (browser.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver();
                //driver = new ChromeDriver(options);
            } else if (browser.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();
            }
            else
            {
                logger.info("Browser name is"+browser);
            }

            ExtentTest test = reports.createTest(iTestResult.getMethod().getMethodName());
            testlogger.set(test);
            testlogger.get().log(Status.INFO, "Driver start time: "+ LocalDateTime.now());
        }
        else if(AppConstants.platform.equalsIgnoreCase("remote"))
        {
            if(browser.equalsIgnoreCase("chrome")) {
                co.setPlatformName("linux");
                co.setPageLoadStrategy(PageLoadStrategy.EAGER);
                try {
                    driver = new RemoteWebDriver(new URL("http://localhost:4444"),co);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                //driver = new ChromeDriver(options);
            }
            else if(browser.equalsIgnoreCase("firefox"))
            {
                fo.setPlatformName("linux");
                fo.setPageLoadStrategy(PageLoadStrategy.EAGER);

                try {
                    driver=new RemoteWebDriver(new URL("http://localhost:4444"),fo);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }

            }

        }
        else {
            logger.error("Platform not supported");
        }

    }

    @AfterMethod
    public void tearDown(ITestResult iTestResult)
    {
        if(iTestResult.isSuccess())
        {
            testlogger.get().log(Status.PASS, MarkupHelper.createLabel(iTestResult.getMethod().getMethodName()+" is successful", ExtentColor.GREEN));
        }
        else
        {
            testlogger.get().log(Status.FAIL, "Test is failed" + iTestResult.getThrowable());

        }
        driver.quit();
    }

}
