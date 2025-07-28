package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Practice_1 {


    //1 - Setup: OS/ Browser/ Web/ Page/ Data
    WebDriver driver; //khai bao bien

    @BeforeClass
    public void initialBrowser(){
        driver = new ChromeDriver();//khoi tao browser

        driver.get("https://alada.vn/tai-khoan/dang-ky.html"); // mo trang
        //thu bat cac locator voi cac loại: id/class/name/link/partal link/tagname/css/xpath



    }

    //2 - Action/ Execute: Tuong tac len element nao/ nhap lieu/ verify...

    @Test
    public void TC_01_id() throws InterruptedException {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html"); // mo trang

        driver.findElement(By.id("txtSearch"));

        try {// ko loi
            Thread.sleep(3000);
        } catch (InterruptedException e) {//co loi
            throw new RuntimeException(e);
        }
        driver.findElement(By.id("txtSearch"));
        Thread.sleep(3000);
    }
    @Test
    public void TC_02_class() throws InterruptedException {
        driver.findElement(By.className("inputsearch2"));
        Thread.sleep(3000);

    }
    @Test
    public void TC_03_name() throws InterruptedException {
        driver.findElement(By.name("txtSearch"));
        Thread.sleep(3000);


    }
    @Test
    public void TC_04_css() throws InterruptedException {
        driver.findElement(By.cssSelector("input[id='txtSearch']"));
        Thread.sleep(3000);
    }
    @Test
    public void TC_05_xpath() throws InterruptedException {
        driver.findElement(By.xpath("//input[@id='txtSearch']"));
        Thread.sleep(3000);
    }
    @Test
    public void TC_06_dangky() throws InterruptedException {
        driver.findElement(By.partialLinkText("Đăng Ký"));
        Thread.sleep(3000);
    }
    @Test
    public void TC_07_dangky() throws InterruptedException {
        driver.findElement(By.linkText("Đăng Nhập"));
        Thread.sleep(3000);
    }
    @Test
    public void TC_08_dangkyngay() throws InterruptedException {
        driver.findElement(By.id("btndknfooter"));
        Thread.sleep(3000);
    }
    @Test
    public void TC_09_hovaten() throws InterruptedException {
        driver.findElement(By.id("txtFirstname"));
        driver.findElement(By.id("txtEmail"));
        driver.findElement(By.id("txtCEmail"));
        driver.findElement(By.id("txtPassword"));
        driver.findElement(By.id("txtCPassword"));
        driver.findElement(By.id("txtPhone"));
        driver.findElement(By.id("chkRight"));
        driver.findElement(By.xpath("//button[@type='submit']"));
        Thread.sleep(3000);
    }

    //3 - Clean: Delete data test/ account/ close browser...
    @AfterClass
    public void cleanBrowser(){
        driver.quit(); //dong trinh duyet
    }


}