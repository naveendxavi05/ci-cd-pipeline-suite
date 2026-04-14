package com.naveen.tests;

import com.naveen.base.BaseTest;
import com.naveen.pages.LoginPage;
import com.naveen.pages.ProductsPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ProductTest extends BaseTest {

    private ProductsPage login() {
        return new LoginPage(getDriver()).loginAs("standard_user", "secret_sauce");
    }

    @Test(groups = {"regression"})
    public void inventoryItemCountIsSix() {
        ProductsPage productsPage = login();
        Assert.assertEquals(productsPage.getInventoryItemCount(), 6);
    }

    @Test(groups = {"regression"})
    public void cartBadgeIncrementsOnAdd() {
        ProductsPage productsPage = login();
        productsPage.addBackpackToCart();
        Assert.assertEquals(productsPage.getCartBadgeCount(), 1);
    }

    @Test(groups = {"regression"})
    public void sortByPriceAscending() {
        ProductsPage productsPage = login();
        productsPage.sortByPriceAscending();
        List<WebElement> prices = productsPage.getInventoryItemPrices();
        double first = Double.parseDouble(prices.get(0).getText().replace("$", ""));
        double last  = Double.parseDouble(prices.get(prices.size() - 1).getText().replace("$", ""));
        Assert.assertTrue(first <= last, "Products are not sorted by price ascending");
    }
}