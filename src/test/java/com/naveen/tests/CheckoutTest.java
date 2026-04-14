package com.naveen.tests;

import com.naveen.base.BaseTest;
import com.naveen.pages.CartPage;
import com.naveen.pages.CheckoutPage;
import com.naveen.pages.LoginPage;
import com.naveen.pages.ProductsPage;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @Test(groups = {"smoke", "regression"})
    public void fullCheckoutFlow() {
        Faker faker = new Faker();

        ProductsPage productsPage = new LoginPage(getDriver())
                .loginAs("standard_user", "secret_sauce");

        productsPage.addBackpackToCart();

        CartPage cartPage = productsPage.goToCart();
        CheckoutPage checkoutPage = cartPage.proceedToCheckout();

        checkoutPage.fillDetails(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.address().zipCode()
        );
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();

        Assert.assertTrue(
                checkoutPage.getConfirmationHeading().contains("Thank you for your order")
        );
    }
}