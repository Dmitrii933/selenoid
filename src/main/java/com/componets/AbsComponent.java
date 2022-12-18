package com.componets;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbsComponent<T> {
    protected WebDriver driver;

    public AbsComponent(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }
}
