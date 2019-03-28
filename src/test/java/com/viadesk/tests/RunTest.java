package com.viadesk.tests;

import com.viadesk.webpages.DashboardPage;
import com.viadesk.webpages.LoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class RunTest {
    static WebDriver driver;

    @BeforeAll
    public static void launchBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\muhin\\IdeaProjects\\viadeskLoginDemo\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    @Order(1)
    public void validLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmailInput("rubel@metatude.com");
        loginPage.setPasswordInput("test");
        Thread.sleep(2000);
        loginPage.clickLoginButton();
        Thread.sleep(2000);
        assertFalse(loginPage.isElementExist());
    }

    @Test
    @Order(3)
    public void inValidLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmailInput("rubel@metatude.com");
        loginPage.setPasswordInput("test1");
        Thread.sleep(2000);
        loginPage.clickLoginButton();
        Thread.sleep(2000);
        assertTrue(loginPage.isElementExist());
    }

    @Test
    @Order(2)
    public void testClickOnPortal() throws InterruptedException {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.clickOnPortal();
        Thread.sleep(2000);
    }

    @AfterAll
    public static void close() {
        driver.close();
    }
}
