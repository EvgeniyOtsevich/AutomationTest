package commonLibs.ui.pages;

import commonLibs.ui.user.User;
import commonLibs.ui.user.UserAccount;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignIn extends InitPage {

    private final String BASE_URL = "https://my-sandbox.maxpay.com/#";

    @FindBy(id = "login-email")
    private WebElement emailField;

    @FindBy(id = "login-password")
    private WebElement passwordField;

    @FindBy(css = "[type=submit]")
    private WebElement signInButton;

    @FindBy(id = "login-email-error")
    private WebElement emailError;

    @FindBy(id = "login-password-error")
    private WebElement passwordError;

    @FindBy(css = "div.alert.alert-danger p")
    private WebElement alert;

    public SignIn(WebDriver driver) {
        super(driver);
        driver.get(BASE_URL + "/signin");
    }

    @Step("Input user Email: {email}")
    private void inputEmail(String email){
        if (email == null) System.out.println("Email null");
        else {
            wait.until(ExpectedConditions.visibilityOf(emailField)).clear();
            emailField.sendKeys(email);
        }
    }

    @Step("Input user Password: {pass}")
    private void inputPassword(String pass){
        if (pass == null) System.out.println("Pass null");
        else {
            passwordField.clear();
            passwordField.sendKeys(pass);
        }
    }

    @Step("User Sign In")
    public Object signIn(UserAccount user){
        inputEmail(user.getEmail());
        inputPassword(user.getPassword());
        signInButton.click();
        try {
            wait.until(ExpectedConditions.titleContains("Dashboard"));
            return new HomePage(driver);
        } catch (org.openqa.selenium.TimeoutException exception){
            return new SignIn(driver);
        }
    }

    @Step("Verify is Alert present")
    public boolean isAlertPresent(){
        try {
            wait.until(ExpectedConditions.visibilityOf(alert));
            return true;
        } catch (org.openqa.selenium.TimeoutException exception){
            return false;
        }
    }

    @Step("Verify is Email Error Message present")
    public boolean isEmailErrorMessagePresent(){
        try {
            wait.until(ExpectedConditions.visibilityOf(emailError));
            return true;
        } catch (org.openqa.selenium.TimeoutException exception){
            return false;
        }
    }

    @Step("Verify is Password Error Message present")
    public boolean isPasswordErrorMessagePresent(){
        try {
            wait.until(ExpectedConditions.visibilityOf(passwordError));
            return true;
        } catch (org.openqa.selenium.TimeoutException exception){
            return false;
        }
    }

}
