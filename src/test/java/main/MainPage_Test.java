package main;

import com.annotations.Driver;
import com.componets.PopularCursComponent;
import com.extensions.UIExtension;

import com.pages.SolutionarchitectPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.openqa.selenium.WebDriver;
import com.pages.MainPage;

import java.net.MalformedURLException;
import java.util.Locale;


@ExtendWith(UIExtension.class)
public class MainPage_Test {

    @Driver
    private WebDriver driver;

    @Test
    public void checkElementToTitleTest() throws MalformedURLException {
        String title = "Специализация QA Automation Engineer";
        new MainPage(driver)
                .open();

        new PopularCursComponent(driver).
                filterStream(title);

        new SolutionarchitectPage(driver)
                .pageHeaderShouldBeSameAs("QA Automation");

    }

    @Test
    public void maxDateTest() throws MalformedURLException {

        new MainPage(driver)
                .open();

        new PopularCursComponent(driver)
                .dateStreamReduce((x, y) -> y.isAfter(x) ? y : x);
    }

    @Test
    public void minDateTest() throws MalformedURLException {

        new MainPage(driver)
                .open();

        new PopularCursComponent(driver)
                .dateStreamReduce((x, y) -> y.isAfter(x) ? x : y);

    }

    @Test
    public void checkElementMoveTest() throws MalformedURLException {

        new MainPage(driver)
                .open();

        new PopularCursComponent(driver)
                .movePopularCursItems(1);
    }

}
