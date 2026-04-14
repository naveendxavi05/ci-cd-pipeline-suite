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

    @Test(groups = {"regression"})
    public void invalidPassword() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.attemptLogin("standard_user", "wrong_password");
        Assert.assertTrue(
                loginPage.getErrorMessage().contains("Username and password do not match")
        );
    }

    @Test(groups = {"regression"})
    public void lockedOutUser() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.attemptLogin("locked_out_user", "secret_sauce");
        Assert.assertTrue(
                loginPage.getErrorMessage().contains("locked out")
        );
    }

    @Test(groups = {"regression"})
    public void emptyCredentials() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.attemptLogin("", "");
        Assert.assertFalse(loginPage.getErrorMessage().isEmpty());
    }
}