package com.extensions;

import com.annotations.Driver;
import com.driver.DriverFactory;
import com.exceptions.BrowserNotSupportedException;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URI;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashSet;
import java.util.Set;

public class UIExtension implements BeforeEachCallback, AfterEachCallback {

    private WebDriver driver = null;
  //  private String browser = System.getProperty("browser");

    private Set<Field> getAnnotatedFields(Class<? extends Annotation> annotation, ExtensionContext extensionContext) {
        Set<Field> set = new HashSet<>();
        Class<?> testClass = extensionContext.getTestClass().get();
        while (testClass != null) {
            for (Field field : testClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(annotation)) {
                    set.add(field);
                }
            }
            testClass = testClass.getSuperclass();
        }
        return set;
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws BrowserNotSupportedException, MalformedURLException {
        Set<Field> fields = getAnnotatedFields(Driver.class, extensionContext);
       // driver = new DriverFactory().getDriver(browser);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        capabilities.setCapability(CapabilityType.BROWSER_VERSION, "107.0");
        capabilities.setCapability("enableVNC", true);
        driver = new RemoteWebDriver(
                URI.create(System.getProperty("webdriver.remote.url")).toURL(), capabilities);
        for (Field field : fields) {
            if (field.getType().getName().equals(WebDriver.class.getName())) {
                AccessController.doPrivileged((PrivilegedAction<Void>)
                        () -> {
                            try {
                                field.setAccessible(true);
                                field.set(extensionContext.getTestInstance().get(), driver);
                            } catch (IllegalAccessException e) {
                                throw new Error(String.format("Could not access or set webdriver in field: %s - is this field public?", field), e);
                            }
                            return null;
                        }
                );
            }
        }
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
