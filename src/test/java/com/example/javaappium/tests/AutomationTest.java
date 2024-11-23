package com.example.javaappium.tests;

import com.example.javaappium.base.AppiumSetup;
import com.example.javaappium.page.HomePage;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AutomationTest {
    private RemoteWebDriver appiumDriver;
    private HomePage homePage;

    @BeforeClass
    public void setup() {
        AppiumSetup appiumSetup = new AppiumSetup();
        appiumDriver = appiumSetup.initializeDriver();
        homePage = new HomePage(appiumDriver);
    }

    @Test
    public void testCheckout() {
        homePage.fazerCheckout();
    }

    @AfterClass
    public void teardown() {
        if (appiumDriver != null) {
            appiumDriver.quit();
        }
    }
}
