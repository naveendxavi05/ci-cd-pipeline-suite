package com.naveen.tests;

import com.naveen.base.BaseTest;
import com.naveen.pages.CartPage;
import com.naveen.pages.LoginPage;
import com.naveen.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    private ProductsPage login() {
        return new LoginPage(getDriver()).loginAs("standard_user", "secret_sauce");
    }

    @Test(groups = {"regression"})
    public void addTwoItemsToCart() {
        ProductsPage productsPage = login();
        productsPage.addBackpackToCart();
        productsPage.addBikeLightToCart();
        Assert.assertEquals(productsPage.getCartBadgeCount(), 2);
    }

    @Test(groups = {"regression"})
    public void removeOneItemFromCart() {
        ProductsPage productsPage = login();
        productsPage.addBackpackToCart();
        productsPage.addBikeLightToCart();
        CartPage cartPage = productsPage.goToCart();
        cartPage.removeBackpack();
        Assert.assertEquals(cartPage.getCartItemCount(), 1);
    }

    @Test(groups = {"regression"})
    public void cartBadgePersistsAcrossNavigation() {
        ProductsPage productsPage = login();
        productsPage.addBackpackToCart();
        CartPage cartPage = productsPage.goToCart();
        Assert.assertTrue(cartPage.isCartBadgeVisible());
    }
}