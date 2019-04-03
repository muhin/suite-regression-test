package com.viadesk.tests;

import com.viadesk.webpages.DashboardPage;
import com.viadesk.webpages.LoginPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.activation.DataHandler;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class RunTest {
    static WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, 5);

    @BeforeAll
    public static void launchBrowser() {
        String sysPath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", sysPath + "\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    @DisplayName("Try login with proper credential")
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @Order(1)
    @CsvFileSource(resources = "/ValidLoginData.csv")
    public void validLogin(String userEmail, String userPassword) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        loginPage.setEmailInput(userEmail);
        loginPage.setPasswordInput(userPassword);
        Thread.sleep(2000);
        loginPage.clickLoginButton();
        WebElement element = wait.until(ExpectedConditions.visibilityOf(dashboardPage.getElementPortalLabel()));
        assertTrue(dashboardPage.isPortalLabelExist());
    }

    @DisplayName("Try login with invalid credential")
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @CsvFileSource(resources = "/InvalidLoginData.csv")
    @Order(3)
    public void invalidLogin(String userEmail, String userPassword) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmailInput(userEmail);
        loginPage.setPasswordInput(userPassword);
        Thread.sleep(2000);
        loginPage.clickLoginButton();
        Thread.sleep(2000);
        WebElement element = wait.until(ExpectedConditions.visibilityOf(loginPage.getErrorMessageLabel()));
        assertTrue(loginPage.isErrorMessageExist());
    }

    @Test
    @Order(2)
    public void testClickOnPortal() throws InterruptedException {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.clickOnPortal();
    }

    @AfterAll
    public static void close() {
        driver.close();
    }
}