package com.naveen.tests;

import com.naveen.base.BaseTest;
import com.naveen.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(groups = {"smoke"})
    public void validLogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginAs("standard_user", "secret_sauce");
        Assert.assertTrue(getDriver().getCurrentUrl().contains("/inventory.html"));
    }
}