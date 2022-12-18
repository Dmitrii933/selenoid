package com.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class AbsBasePage<T> {

    protected WebDriver driver;

    @FindBy(css = "h1[class = tn-atom]")
    private WebElement titleHeader;

    private String hostname = System.getProperty("webdriver.base.url");

    public AbsBasePage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public T open() {
        driver.get(hostname);
        return (T) this;
    }

    public T pageHeaderShouldBeSameAs(String header) {
        Assertions
                .assertTrue(titleHeader.getText().trim().contains(header));
        return (T) this;

    }

}
