package com.naveen.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private static final int DEFAULT_TIMEOUT = 10;

    public static void waitForVisible(WebDriver driver, By locator, int seconds) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitForVisible(WebDriver driver, By locator) {
        waitForVisible(driver, locator, DEFAULT_TIMEOUT);
    }

    public static void waitForClickable(WebDriver driver, By locator, int seconds) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForClickable(WebDriver driver, By locator) {
        waitForClickable(driver, locator, DEFAULT_TIMEOUT);
    }

    public static void waitForUrl(WebDriver driver, String urlFragment, int seconds) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.urlContains(urlFragment));
    }

    public static void waitForUrl(WebDriver driver, String urlFragment) {
        waitForUrl(driver, urlFragment, DEFAULT_TIMEOUT);
    }
}