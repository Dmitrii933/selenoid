package com.driver;

import com.exceptions.BrowserNotSupportedException;
import org.openqa.selenium.WebDriver;

public interface IDriverFactory {
    WebDriver getDriver(String browser) throws BrowserNotSupportedException;
}
