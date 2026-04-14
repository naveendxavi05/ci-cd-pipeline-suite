package com.naveen.pages;

import com.naveen.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductsPage {

    private final WebDriver driver;

    private final By pageHeading    = By.cssSelector("[data-test='title']");
    private final By inventoryItems = By.cssSelector("[data-test='inventory-item']");
    private final By cartBadge      = By.cssSelector("[data-test='shopping-cart-badge']");
    private final By sortDropdown   = By.cssSelector("[data-test='product-sort-container']");
    private final By addBackpack    = By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']");
    private final By addBikeLight   = By.cssSelector("[data-test='add-to-cart-sauce-labs-bike-light']");
    private final By cartLink       = By.cssSelector("[data-test='shopping-cart-link']");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        WaitUtils.waitForUrl(driver, "/inventory.html");
    }

    public boolean isHeadingVisible() {
        WaitUtils.waitForVisible(driver, pageHeading);
        return driver.findElement(pageHeading).isDisplayed();
    }

    public int getInventoryItemCount() {
        WaitUtils.waitForVisible(driver, inventoryItems);
        List<WebElement> items = driver.findElements(inventoryItems);
        return items.size();
    }

    public void addBackpackToCart() {
        WaitUtils.waitForClickable(driver, addBackpack);
        driver.findElement(addBackpack).click();
    }

    public void addBikeLightToCart() {
        WaitUtils.waitForClickable(driver, addBikeLight);
        driver.findElement(addBikeLight).click();
    }

    public int getCartBadgeCount() {
        WaitUtils.waitForVisible(driver, cartBadge);
        return Integer.parseInt(driver.findElement(cartBadge).getText());
    }

    public void sortByPriceAscending() {
        WaitUtils.waitForVisible(driver, sortDropdown);
        new Select(driver.findElement(sortDropdown)).selectByValue("lohi");
    }

    public List<WebElement> getInventoryItemPrices() {
        return driver.findElements(By.cssSelector("[data-test='inventory-item-price']"));
    }

    public CartPage goToCart() {
        WaitUtils.waitForClickable(driver, cartLink);
        driver.findElement(cartLink).click();
        return new CartPage(driver);
    }
}