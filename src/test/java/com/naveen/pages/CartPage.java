package com.naveen.pages;

import com.naveen.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {

    private final WebDriver driver;

    private final By cartItems       = By.cssSelector("[data-test='inventory-item']");
    private final By cartBadge       = By.cssSelector("[data-test='shopping-cart-badge']");
    private final By checkoutButton  = By.cssSelector("[data-test='checkout']");
    private final By removeBackpack  = By.cssSelector("[data-test='remove-sauce-labs-backpack']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        WaitUtils.waitForUrl(driver, "/cart.html");
    }

    public int getCartItemCount() {
        List<WebElement> items = driver.findElements(cartItems);
        return items.size();
    }

    public void removeBackpack() {
        WaitUtils.waitForClickable(driver, removeBackpack);
        driver.findElement(removeBackpack).click();
    }

    public boolean isCartBadgeVisible() {
        return !driver.findElements(cartBadge).isEmpty();
    }

    public CheckoutPage proceedToCheckout() {
        WaitUtils.waitForClickable(driver, checkoutButton);
        driver.findElement(checkoutButton).click();
        return new CheckoutPage(driver);
    }
}