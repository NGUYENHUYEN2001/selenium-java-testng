package webDriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.Duration;
import java.util.Set;

public class Topic_06_WebBrowser_Commands {

    WebDriver driver; //khai bao bien

    @BeforeClass
    public void initialBrowser(){
        //Run with browser
        driver = new FirefoxDriver();
        driver = new EdgeDriver();
        driver = new ChromeDriver();
        driver = new SafariDriver();
        driver = new InternetExplorerDriver();

        // Remote (Grid/docker/ CLoud testing)
        ChromeOptions chromeOptions = new ChromeOptions();
     //   driver = new RemoteWebDriver(new URL("http://localhost:4444"), chromeOptions);




    }

    // sau dau . hien mau dam thi la cac ham thao tac voi browser
    // mau nha la thao tac voi object
    @Test
    public void TC_01(){
        //  mo ra 1 url bat ky (luu y phai bat dau bang http/https)
        driver.get("https://demo.nopcommerce.com/");//**
        // Dong browser: dong tai dung tab dang dung
        driver.close();//*
        // Dong browser gom tat ca cac tab/window
        driver.quit();//**
        // Lay ra title cua page hien tai
        // 1- Luu du lieu lai roi kiem tra sau
        String homePageTitle = driver.getTitle();
        Assert.assertEquals(homePageTitle, "Chờ một chút...");
        //2- kiem tra truc tiep
        Assert.assertEquals(driver.getTitle(), "Chờ một chút...");

        // lay ra URL cua page hien tai

        Assert.assertEquals(driver.getCurrentUrl(), "https://demo.nopcommerce.com/cart");

        //Lay ra Page Source Code
        String homePAgeSourceCode=driver.getPageSource();

        // kiem tra tuogn doi
        Assert.assertTrue(homePAgeSourceCode.contains("Conditions of Use"));

        // lay ra ID cua tab/window dang active
        driver.getWindowHandle();

        // lay ra tat ca ID cua tab/window dang co
        driver.getWindowHandles();

        //di tim 1 element
        driver.findElement(By.xpath("")); //**
        //di tim n element
        driver.findElements(By.xpath("")); //**

        //Dung de cho cho viec tim element (findElement/findElements)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)); //*

        //Dung de cho cho viec page duoc load xong
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));

        //dung de cho cho 1 doan script duoc thuc thi xong
        //JavascriptExecutor - js
        driver.manage().timeouts().scriptTimeout(Duration.ofDays(15));
        driver.manage().timeouts().scriptTimeout(Duration.ofHours(15));
        driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(15));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(15));

        // thu nho vr task bar de chay
        driver.manage().window().minimize();
        //phong to len
        driver.manage().window().maximize(); //*
        //tran man hinh
        driver.manage().window().fullscreen();
        //
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().window().getSize();

        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().getSize();

        //lay het tat ca cookieL : test class 01 (register tai khoan - luu cookie lai)

        Set<Cookie> cookies = driver.manage().getCookies(); //*
        driver.manage().getCookieNamed(".Nop.Antiforgery");
        // xoa het Cookie
        driver.manage().deleteAllCookies();
        for (Cookie cookie : cookies) {
            //xoa cookie theo thu tu
            driver.manage().deleteCookie(cookie);
        }
        //xoa cookie theo ten
        driver.manage().deleteCookieNamed(".Nop.Antiforgery");

        // den 1 test class khac - ko can login - set cookie da co vao day roi refresh lai
        for (Cookie cookie : cookies) {
            //add cookie theo thu tu
            driver.manage().addCookie(cookie);
        }
        driver.navigate().refresh();// login thanh cong

        Logs log =driver.manage().logs();
        LogEntries logEntries=log.get("BROWSER");

        for (LogEntry logEntry : logEntries) {
            System.out.println(logEntry);
        }
        log.getAvailableLogTypes();

        WebDriver.Navigation navigation=driver.navigate();
        //refresh
        navigation.refresh();
        //quay laij trang truoc do
        navigation.back();
        // chuyen tiep toi trang truoc do
        navigation.forward();
        //mo url bat ky
        navigation.to("https://demo.nopcommerce.com/registerresult/1?returnUrl=/");

        //Alert/Iframe/Windows (Tab)
        WebDriver.TargetLocator targetLocator= driver.switchTo();
        targetLocator.alert().accept();
        targetLocator.alert().dismiss();
        //frame/iframe
        targetLocator.frame(""); //*
        //windows
        targetLocator.window(""); //*

        // lay ra ID cua tab/window dang active
        driver.getWindowHandle(); //*

        // lay ra tat ca ID cua tab/window dang co
        driver.getWindowHandles(); //*
        // nhung cai dung nhieu nhat
        //driver.get();
        //driver.quit();
       // driver.findElements();
        //driver.findElement();
       // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));




    }
    @Test
    public void TC_02(){

    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit(); //dong trinh duyet
    }


}