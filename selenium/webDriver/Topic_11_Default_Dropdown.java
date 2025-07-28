package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Topic_11_Default_Dropdown {

    WebDriver driver; //khai bao bien
    Select select;
    Random rand;
    String firstName, lastName, email, password, company, date, month, year;
    @BeforeClass
    public void initialBrowser(){
        driver = new ChromeDriver();//khoi tao browser
        rand = new Random();
        firstName = "msdd";
        lastName= "aaa";
        email = "msdd"+rand.nextInt(99999)+"@gmail.com";
        password = "123456";
        company  = "ABC";
        date="21";
        month ="8";
        year="1996";

    }


    @Test
    public void TC_01_Facebook_SignUp(){
        driver.get("https://www.facebook.com/reg");
        select = new Select(driver.findElement(By.cssSelector("select#day")));
        //select.selectByIndex(20);
        // 1-index
        //index thay doi vi tri
        //Đọc code khong biet no la tinh nao => fail => tai hien se kho
        //select.selectByValue("9433");
        //2-value
        //option ko bat buoc phai co attribute value
        //Đọc code khong biet no la tinh nao => fail => tai hien se kho
        //them 1 so buoc de di tim no la tinh gi/ ở đâu

       // select.selectByVisibleText("thành phố Hà Nội");
        // 3- text
        // giống như end user chọn
        // khong bi trùng dữ liệu/ ko để trống dữ liệu
        // không thay đổi text nếu đổi vị trí
        // => chạy fail => ít mất thời gian tái hiện

        // 2 ham nhieu nhat: chọn: selectByText, verify: getFirstSelectOption

        // chọn 1 item
        select.selectByVisibleText("12");

        //chọn xong verify đã chọn thành công hay chưa?
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "12" );

        // Verify cái dropdown có phải là multiple select hay không?
        //nếu là multiple => trả về true
        // nếu là sigle => trả về false
        Assert.assertFalse(select.isMultiple());

        // verify tổng số lượng item trong dropdown là bao nhiêu
        Assert.assertEquals(select.getOptions().size(), 31);

        select = new Select(driver.findElement(By.cssSelector("select#month")));
        select.selectByVisibleText("Jun");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Jun" );

        select = new Select(driver.findElement(By.cssSelector("select#year")));
        select.selectByVisibleText("2006");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "2006" );


    }
    @Test
    public void TC_02_BTVN_1(){
        driver.get("https://demo.nopcommerce.com/register");
        driver.findElement(By.cssSelector("a.ico-register")).click();

        driver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);

        new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay]"))).selectByVisibleText(date);
        new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth]"))).selectByVisibleText(month);
        new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear]"))).selectByVisibleText(year);

        driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
        driver.findElement(By.cssSelector("input#Company")).sendKeys(company);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys(password);
        driver.findElement(By.cssSelector("button#register-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.registration-result-page div.result")).getText(),"Your registration completed");

        driver.findElement(By.cssSelector("a.a.ico-login")).click();
        driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
        driver.findElement(By.cssSelector("button.login-button")).click();

        driver.findElement(By.cssSelector("a.ico-account")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute("value"),firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#LastName")).getAttribute("value"),lastName);
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay]"))).getFirstSelectedOption().getText(), date);
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth]"))).getFirstSelectedOption().getText(), month);
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear]"))).getFirstSelectedOption().getText(), year);


    }

        @Test
    public void TC_03_Rode() throws InterruptedException {
        driver.get("https://rode.com/en/support/where-to-buy");
        driver.findElement(By.xpath("//div[text()='Allow All']")).click();
        sleep(3000);
        new Select(driver.findElement(By.cssSelector("select#country"))).selectByVisibleText("Vietnam");
        driver.findElement(By.cssSelector("input#map_search_query")).sendKeys("HO CHI MINH");
        driver.findElement(By.xpath("//button[text()='Search']")).click();

        List<WebElement> dealers= driver.findElements(By.xpath("//h3[text()='Dealers']/following-sibling::div//h4"));
        Assert.assertEquals(dealers.size(), 16);

            for (WebElement element:dealers) {
                System.out.println(element.getText());
            }

        }

    @AfterClass
    public void cleanBrowser(){
       driver.quit(); //dong trinh duyet
    }


}