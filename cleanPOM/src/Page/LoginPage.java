package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;
    public static String url = "https://live.techpanda.org/index.php/customer/account/login/";
    private final By emailXpath = By.cssSelector("input#email");
    private final By passwordXpath = By.cssSelector("input#pass");
    private final By loginButton = By.cssSelector("button#send2");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void login(String email, String password){
        driver.findElement(emailXpath).sendKeys(email);
        driver.findElement(passwordXpath).sendKeys(password);
        driver.findElement(loginButton).click();
    }
}
