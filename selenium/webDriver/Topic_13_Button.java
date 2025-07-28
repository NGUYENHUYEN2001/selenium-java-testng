package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.openqa.selenium.support.Color;

public class Topic_13_Button {


    //1 - Setup: OS/ Browser/ Web/ Page/ Data
    WebDriver driver; //khai bao bien

    @BeforeClass
    public void initialBrowser(){
        driver = new ChromeDriver();//khoi tao browser

    }

    //2 - Action/ Execute: Tuong tac len element nao/ nhap lieu/ verify...

    @Test
    public void TC_01_Fahasa() throws InterruptedException {
        driver.get("https://www.fahasa.com/customer/account/create"); // mo trang
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
        By loginButton = By.cssSelector("button.fhs-btn-login");

        // isEnable
        Assert.assertFalse(driver.findElement(loginButton).isEnabled());

        String loginBackground = driver.findElement(loginButton).getCssValue("background-color");

        Color loginColor = Color.fromString(loginBackground);

        Assert.assertEquals(loginColor.asHex().toUpperCase(), "#000000");

        driver.findElement(By.cssSelector("input#login_username")).sendKeys("dao@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456789");
      // mong doi enable
        Assert.assertTrue(driver.findElement(loginButton).isEnabled());
        Assert.assertEquals(Color.fromString(driver.findElement(loginButton).getCssValue("background-color")).asHex().toUpperCase(), "#C92127");

        driver.findElement(loginButton).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.fhs-login-msg")).getText(), "Số điện thoại/Email hoặc Mật khẩu sai!");


    }
    @Test
    public void TC_02(){

    }


    //3 - Clean: Delete data test/ account/ close browser...
    @AfterClass
    public void cleanBrowser(){
        driver.quit(); //dong trinh duyet
    }


}