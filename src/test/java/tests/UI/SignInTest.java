package tests.UI;

import commonLibs.maxpay.MaxPayApi;
import commonLibs.ui.pages.HomePage;
import commonLibs.ui.pages.SignIn;
import commonLibs.ui.user.User;
import commonLibs.ui.utilities.BrowsersDriver;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static commonLibs.ui.user.User.States.FULL;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class SignInTest extends BrowsersDriver {

    private SoftAssertions softAssertions = new SoftAssertions();

    private SignIn signInPage;
    private HomePage homePage;
    private User fullUser = new User(FULL);

    @BeforeClass
    public void userPreparation(){
        assertThat(MaxPayApi.createUser(fullUser)).isTrue();
    }

    @BeforeMethod
    public void setUp(){
        driver.manage().deleteAllCookies();
        signInPage = new SignIn(driver);
    }

    @Test
    public void signInWithValidUser(){
        homePage = (HomePage) signInPage.signIn(fullUser);
        assertThat(homePage.isHomePageOpened()).isTrue();
    }

    @Test
    public void signInWithNonExistedUser(){
        signInPage.signIn(new User(FULL));
        assertThat(signInPage.isAlertPresent()).isTrue();
    }

    @Test
    public void signInWithEmptyCredentials(){
        signInPage.signIn(new User());
        softAssertions.assertThat(signInPage.isEmailErrorMessagePresent()).isTrue();
        softAssertions.assertThat(signInPage.isPasswordErrorMessagePresent()).isTrue();
        softAssertions.assertAll();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
