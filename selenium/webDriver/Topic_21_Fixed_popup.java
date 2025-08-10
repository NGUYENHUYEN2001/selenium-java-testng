package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_21_Fixed_popup {


    //1 - Setup: OS/ Browser/ Web/ Page/ Data
    WebDriver driver; //khai bao bien

    @BeforeClass
    public void initialBrowser(){
        driver = new FirefoxDriver();}



    @Test
    public void TC_01_Ngoaingu24h() throws InterruptedException {

        driver.get("https://ngoaingu24h.vn/");
        Thread.sleep(5000);

        driver.findElement(By.xpath("//div[@class='action']//button[text()='Đăng nhập']")).click();

        By popupDangnhap = By.cssSelector("div.MuiPaper-root");
        //kiem tra 1 element hien thi co trong html
        //kiem tra hien thi UI => true
        //ko hien thi tren UI => false
        Assert.assertTrue(driver.findElement(popupDangnhap).isDisplayed());

        driver.findElement(By.xpath("//input[@autocomplete='username']")).sendKeys("automationfc");
        driver.findElement(By.xpath("//input[@autocomplete='new-password']")).sendKeys("automationfc");
        driver.findElement(By.xpath("//div[@class='auth-form']//button[@type='submit']")).click();

        // Khởi tạo wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By noti = By.cssSelector("div#notistack-snackbar");
        // Chờ đợi noti xuất hiện
        WebElement snackbar = wait.until(ExpectedConditions.visibilityOfElementLocated(noti));
        Assert.assertEquals(driver.findElement(noti).getText(),"Bạn đã nhập sai tài khoản hoặc mật khẩu!");

        driver.findElement(By.cssSelector("div.MuiPaper-root h2#mui-3 button.MuiButtonBase-root")).click();
        // Đợi popup biến mất khỏi DOM
        wait.until(ExpectedConditions.invisibilityOfElementLocated(popupDangnhap));
        System.out.println("Popup đã biến mất khỏi DOM thành công");


    }
    @Test
    public void TC_02_kynaenglish() throws InterruptedException {
        driver.get("https://skills.kynaenglish.vn/dang-nhap");

        By popupDangnhap = By.cssSelector("div.k-popup-account-mb-content");
        Assert.assertTrue(driver.findElement(popupDangnhap).isDisplayed());

        driver.findElement(By.cssSelector("input#user-login")).sendKeys("automation@gmail.com");
        driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#btn-submit-login")).click();

        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(),"Sai tên đăng nhập hoặc mật khẩu");

    }

    @Test
    public void TC_03_tiki() throws InterruptedException {
        driver.get("https://tiki.vn/");
        Thread.sleep(3000);

        //popup 01 - marketing
        // hien thi co dinh khi vua mo site ra
        // khi dong lai khong con trong html nua
        // khi refresh page lai hien ra
       // By marketingPopup = By.cssSelector("div#VIP_BUNDLE");
        // kiem tra hien thi
       // Assert.assertTrue(driver.findElement(marketingPopup).isDisplayed());
       driver.findElement(By.xpath("//img[@alt='close-icon']")).click();
        Thread.sleep(3000);
        // kiem tra ko hien thi => khi dong lai ko con trong html nua
        // Vi element ko co trong html nen ham findElement se ko tim thay => danh fail ngay step nay
       // Assert.assertFalse(driver.findElement(marketingPopup).isDisplayed());

        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
        Thread.sleep(3000);
        // popup hien thi
        By loginPopup = By.cssSelector("div.ReactModal__Content");
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
        // Kiem tra element co trong cay html
        // ko chac chan no co hien thi hoặc ko
        //Assert.assertEquals(driver.findElements(loginPopup).size(),0);

        driver.findElement(By.cssSelector("p.login-with-email")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='email']/parent::div/following-sibling::span[1]")).getText(),"Email không được để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='email']/parent::div/following-sibling::span[2]")).getText(),"Mật khẩu không được để trống");

        driver.findElement(By.cssSelector("img.close-img")).click();
        Thread.sleep(3000);
        // popup ko hien thi (ko co trong DOM/HTML)
        Assert.assertEquals(driver.findElements(loginPopup).size(),0);



    }

    @Test
    public void TC_04_Facebook() throws InterruptedException {
        driver.get("https://www.facebook.com/");
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();

        By loginPopup = By.xpath("//div[text()='Create a new account']/parent::div/parent::div");
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

        driver.findElement(By.xpath("//a[@aria-label='Already have an account?']")).click();
        Thread.sleep(3000);

        // popup ko hien thi (ko co trong DOM/HTML)
        Assert.assertEquals(driver.findElements(loginPopup).size(),0);




    }
    @AfterClass
    public void cleanBrowser(){
        driver.quit(); //dong trinh duyet
    }


}