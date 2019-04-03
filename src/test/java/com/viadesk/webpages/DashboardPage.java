package com.viadesk.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
    private WebDriver driver;

    @FindBy(xpath = "//*[starts-with(@id,'portalLabel_')]")
    private WebElement portalLabel;

    public DashboardPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void clickOnPortal(){
        portalLabel.click();
    }
    public WebElement getElementPortalLabel(){
        return portalLabel;
    }
    public boolean isPortalLabelExist() {
        if (!driver.findElements(By.xpath("//*[starts-with(@id,'portalLabel_')]")).isEmpty()) {
            return true;
        } else return false;
    }
}
