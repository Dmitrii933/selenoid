package com.driver;

import com.exceptions.BrowserNotSupportedException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;


public class DriverFactory implements IDriverFactory {

    @Override
    public WebDriver getDriver(String browser) throws BrowserNotSupportedException {
        switch (browser) {
            case "EDGE":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case "OPERA":
                WebDriverManager.operadriver().setup();
                return new OperaDriver();
            default:
                throw new BrowserNotSupportedException("Incorrect BrowserName");
        }
    }
}
