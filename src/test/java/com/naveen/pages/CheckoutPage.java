package com.naveen.pages;

import com.naveen.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {

    private final WebDriver driver;

    private final By firstNameField    = By.cssSelector("[data-test='firstName']");
    private final By lastNameField     = By.cssSelector("[data-test='lastName']");
    private final By postalCodeField   = By.cssSelector("[data-test='postalCode']");
    private final By continueButton    = By.cssSelector("[data-test='continue']");
    private final By finishButton      = By.cssSelector("[data-test='finish']");
    private final By confirmHeading    = By.cssSelector("[data-test='complete-header']");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        WaitUtils.waitForUrl(driver, "/checkout-step-one.html");
    }

    public void fillDetails(String firstName, String lastName, String postalCode) {
        WaitUtils.waitForVisible(driver, firstNameField);
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(postalCodeField).sendKeys(postalCode);
    }

    public void clickContinue() {
        WaitUtils.waitForClickable(driver, continueButton);
        driver.findElement(continueButton).click();
    }

    public void clickFinish() {
        WaitUtils.waitForClickable(driver, finishButton);
        driver.findElement(finishButton).click();
    }

    public String getConfirmationHeading() {
        WaitUtils.waitForVisible(driver, confirmHeading);
        return driver.findElement(confirmHeading).getText();
    }
}