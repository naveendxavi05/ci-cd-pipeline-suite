package com.naveen.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    public static WebDriver createDriver(String browser) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--window-size=1920,1080");

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--width=1920");
        firefoxOptions.addArguments("--height=1080");

        if (GridConfig.isRemote()) {
            try {
                URL gridUrl = new URL(GridConfig.getGridUrl());
                if (browser.equalsIgnoreCase("firefox")) {
                    return new RemoteWebDriver(gridUrl, firefoxOptions);
                } else {
                    return new RemoteWebDriver(gridUrl, chromeOptions);
                }
            } catch (MalformedURLException e) {
                throw new RuntimeException("Invalid Grid URL: " + GridConfig.getGridUrl(), e);
            }
        } else {
            // SeleniumManager (built into Selenium 4.6+) handles driver download automatically
            if (browser.equalsIgnoreCase("firefox")) {
                return new FirefoxDriver(firefoxOptions);
            } else {
                return new ChromeDriver(chromeOptions);
            }
        }
    }
}