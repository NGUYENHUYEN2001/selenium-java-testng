package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_03_XPath_Css {


    //1 - Setup: OS/ Browser/ Web/ Page/ Data
    WebDriver driver; //khai bao bien

    @BeforeClass
    public void initialBrowser(){
        // Arrange (AAA-Arrage Action Assert)
        driver = new ChromeDriver();//khoi tao browser
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }
    @Test
    public void Register_01_Empty_Data(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html"); // mo trang

        //Action
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        //verify vs du lieu mong doi
        Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");
    }
    @Test
    public void Register_02_Invalid_Email(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html"); // mo trang

        //Action
        driver.findElement(By.id("txtFirstname")).sendKeys("Joe Biden");
        driver.findElement(By.id("txtEmail")).sendKeys("123@456@321");
        driver.findElement(By.id("txtCEmail")).sendKeys("123@456@321");

        //
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //verify vs du lieu mong doi

        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");


    }
    @Test
    public void Register_03_Incorrect_Confirm_Email(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html"); // mo trang

        // Action
        driver.findElement(By.id("txtFirstname")).sendKeys("Joe Biden");
        driver.findElement(By.id("txtEmail")).sendKeys("123@456@321");
        driver.findElement(By.id("txtCEmail")).sendKeys("123@456@3213");
        driver.findElement(By.id("txtPassword")).sendKeys("Huyen@123");
        driver.findElement(By.id("txtCPassword")).sendKeys("Huyen@123");
        driver.findElement(By.id("txtPhone")).sendKeys("0338797802");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        // Verify
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");
    }
    @Test
    public void Register_04_Incorrect_Password(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html"); // mo trang

        // Action
        driver.findElement(By.id("txtFirstname")).sendKeys("Joe Biden");
        driver.findElement(By.id("txtEmail")).sendKeys("123@456");
        driver.findElement(By.id("txtCEmail")).sendKeys("123@456");
        driver.findElement(By.id("txtPassword")).sendKeys("Hu");
        driver.findElement(By.id("txtCPassword")).sendKeys("123");
        driver.findElement(By.id("txtPhone")).sendKeys("0338797802");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        // Verify
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");

    }
    @Test
    public void Register_05_Incorrect_Confirm_Password(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html"); // mo trang

        // Action
        driver.findElement(By.id("txtFirstname")).sendKeys("Joe Biden");
        driver.findElement(By.id("txtEmail")).sendKeys("123@45");
        driver.findElement(By.id("txtCEmail")).sendKeys("123@45");
        driver.findElement(By.id("txtPassword")).sendKeys("Huyen123");
        driver.findElement(By.id("txtCPassword")).sendKeys("Huyen1234");
        driver.findElement(By.id("txtPhone")).sendKeys("0338797802");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        // Verify
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu bạn nhập không khớp");
        


    }
    @Test
    public void Register_06_Invalid_Phone_Number(){

        driver.get("https://alada.vn/tai-khoan/dang-ky.html"); // mo trang

        // Action
        driver.findElement(By.id("txtFirstname")).sendKeys("Joe Biden");
        driver.findElement(By.id("txtEmail")).sendKeys("123@45");
        driver.findElement(By.id("txtCEmail")).sendKeys("123@45");
        driver.findElement(By.id("txtPassword")).sendKeys("Huyen123");
        driver.findElement(By.id("txtCPassword")).sendKeys("Huyen123");
        //<10 so
        driver.findElement(By.id("txtPhone")).sendKeys("03387978");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");

        //>11 so
        driver.findElement(By.id("txtPhone")).clear();
        driver.findElement(By.id("txtPhone")).sendKeys("03387978121212");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");

        // khac dau so VN
        driver.findElement(By.id("txtPhone")).clear();
        driver.findElement(By.id("txtPhone")).sendKeys("7838380291");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");

    }


    //3 - Clean: Delete data test/ account/ close browser...
    @AfterClass
    public void cleanBrowser(){
        driver.quit(); //dong trinh duyet
    }


}