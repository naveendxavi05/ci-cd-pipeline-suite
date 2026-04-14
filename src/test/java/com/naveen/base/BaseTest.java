package com.naveen.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    private static final ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return threadLocal.get();
    }

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser) {
        WebDriver driver = DriverFactory.createDriver(browser);
        threadLocal.set(driver);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        WebDriver driver = threadLocal.get();
        if (driver != null) {
            driver.quit();
        }
        threadLocal.remove();
    }
}