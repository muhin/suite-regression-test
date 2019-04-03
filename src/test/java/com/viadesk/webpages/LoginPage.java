package com.viadesk.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;
    private static String BASE_URL = "http://gijs.local-server.com:8082/do/login";
    private String userEmail;
    private String userPassword;

    @FindBy(xpath = "//*[starts-with(@id,'emailInput_')]")
    private WebElement emailInput;
    @FindBy(xpath = "//*[starts-with(@id,'passwordInput_')]")
    private WebElement passwordInput;
    @FindBy(xpath = "//*[starts-with(@id,'submitButton_')]")
    private WebElement loginButton;
    @FindBy(xpath = "//*[starts-with(@id,'errorLabel_')]")
    private WebElement errorLabel;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        driver.get(BASE_URL);
        PageFactory.initElements(driver, this);
    }

    public void setEmailInput(String userEmail){
        this.userEmail = userEmail;
        emailInput.clear();
        emailInput.sendKeys(userEmail);
    }
    public void setPasswordInput(String userPassword){
        this.userPassword = userPassword;
        passwordInput.sendKeys(userPassword);
    }
    public void clickLoginButton(){
        loginButton.click();
    }
    public boolean isErrorMessageExist(){
        if(!driver.findElements(By.xpath("//*[starts-with(@id,'errorLabel_')]")).isEmpty()) {
            return true;
        } else return false;
    }
    public WebElement getErrorMessageLabel(){
        return errorLabel;
    }
}
