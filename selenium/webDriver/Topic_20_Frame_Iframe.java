package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class Topic_20_Frame_Iframe {


    //1 - Setup: OS/ Browser/ Web/ Page/ Data
    WebDriver driver; //khai bao bien

    @BeforeClass
    public void initialBrowser(){
        driver = new FirefoxDriver();//khoi tao browser
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    //2 - Action/ Execute: Tuong tac len element nao/ nhap lieu/ verify...

    @Test
    public void TC_01_Iframe() throws InterruptedException {
        // trang html A
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[text()='Reject All']")).click();
        driver.findElement(By.cssSelector("img[alt='Campus Safety Survey']")).click();
        //switch to iframe
        // index: page hien tai co nhieu iframe/ frame
        // frame/ iframe dau tien se co index = 0
        // khi them moi/ update lai/ xoa bot di thi doi index cua cac iframe

        //driver.switchTo().frame(0);
        // ida/ name (page co iframe/ frame co id/ name)
        //driver.switchTo().frame("iframe#frame-one85593366");
        // webelement co the cover ca 2 cach tren
        driver.switchTo().frame(driver.findElement(By.cssSelector("div#formTemplateContainer>iframe")));
        // element thuộc trang html B
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Sophomore");
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-3"))).selectByVisibleText("South Dorm");
        driver.findElement(By.xpath("//label[text()='Male']")).click();

        Thread.sleep(3000);
        // tu B quay lai A
        driver.switchTo().defaultContent();

        // driver đã quay lại A rồi
        driver.findElement(By.cssSelector("a.menu-item-login.fs-btn--transparent-kashmir")).click();

        driver.findElement(By.cssSelector("button#login")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(), "Username and password are both required.");

        // A qua B
        driver.switchTo().frame(0);

        // B QUA C
        driver.switchTo().frame(0);

        // C quay lai B
        driver.switchTo().parentFrame();
        // B quay lai A
        driver.switchTo().defaultContent();
    }
    @Test
    public void TC_02_Iframe_ToiDiCodeDao(){
        driver.get("https://toidicodedao.com/");

        driver.switchTo().frame(driver.findElement(By.cssSelector("div.fb_iframe_widget iframe")));

        By followerText= By.xpath("//a[@title='Tôi đi code dạo']/parent::div/following-sibling::div[text()]");

        Assert.assertEquals(driver.findElement(followerText).getText(), "397,355 followers");

    }

    @Test
    public void TC_03_frame() throws InterruptedException {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");

        driver.switchTo().frame("login_page");

        driver.findElement(By.xpath("//input[@name='fldLoginUserId']")).sendKeys("Automationfc");
        driver.findElement(By.xpath("//a[text()='CONTINUE']")).click();

        Thread.sleep(5000);
        driver.switchTo().defaultContent();

        driver.findElement(By.cssSelector("input#keyboard")).sendKeys("123456");
        driver.findElement(By.cssSelector("a#loginBtn")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("p.error-msg")).getText(), "Customer ID/IPIN (Password) is invalid. Please try again.");
    }


    //3 - Clean: Delete data test/ account/ close browser...
    @AfterClass
    public void cleanBrowser(){
        driver.quit(); //dong trinh duyet
    }


}