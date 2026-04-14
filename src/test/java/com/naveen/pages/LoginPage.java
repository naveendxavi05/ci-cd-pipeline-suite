package com.naveen.pages;

import com.naveen.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver driver;

    private final By usernameField = By.cssSelector("[data-test='username']");
    private final By passwordField = By.cssSelector("[data-test='password']");
    private final By loginButton   = By.cssSelector("[data-test='login-button']");
    private final By errorMessage  = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        driver.get("https://www.saucedemo.com");
    }

    public ProductsPage loginAs(String username, String password) {
        WaitUtils.waitForVisible(driver, usernameField);
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        WaitUtils.waitForClickable(driver, loginButton);
        driver.findElement(loginButton).click();
        return new ProductsPage(driver);
    }

    public String getErrorMessage() {
        WaitUtils.waitForVisible(driver, errorMessage);
        return driver.findElement(errorMessage).getText();
    }
}