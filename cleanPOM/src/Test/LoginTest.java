package Test;

import Common.DefaultBrowser.BaseTest;
import Page.LoginPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeClass
    public void setupPage(){
        loginPage = new LoginPage(this.webDriver);
    }

    @Test
    public void SignInTest(){
        this.webDriver.get(LoginPage.url);
        loginPage.login("lqvinh.contact@gmail.com", "Yeuhuyen1501@!");
    }
}
