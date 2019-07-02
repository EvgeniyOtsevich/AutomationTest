package commonLibs.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class HomePage extends InitPage {

    HomePage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify is Home Page is opened")
    public boolean isHomePageOpened(){
        return driver.getTitle().contains("Dashboard");
    }
}
