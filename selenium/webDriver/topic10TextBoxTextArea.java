package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class topic10TextBoxTextArea {
    WebDriver driver;
    Random rand = new Random();
    String firstName, lastName, fullname, email,password;

    @BeforeClass
    public void initialBrowser(){
        driver = new ChromeDriver();//khoi tao browser

        firstName ="Joe";
        lastName = "Biden";
        fullname=firstName  +" "+ lastName;
        email="joe"+rand.nextInt(99999)+"@gmail.com";
        password ="123456";

    }


    @Test
    public void TC_01() throws InterruptedException {
        driver.get("https://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(email);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);

        driver.findElement(By.cssSelector("button[title='Register']")).click();
        Thread.sleep(3000); // nghỉ 3 giây

        driver.findElement(By.xpath("//button[text()='Send anyway']")).click();
        Thread.sleep(3000); // nghỉ 3 giây

        //tuyet doi
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(),
                "Thank you for registering with Main Website Store.");
        String contactIT= driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();

        System.out.println(contactIT);
        //tuong doi
        Assert.assertTrue(contactIT.contains(fullname) && contactIT.contains(email)); //fullname + email

        driver.findElement(By.xpath("//h3[text()='Contact Information']/following-sibling::a")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("input#firstname")).getAttribute("value"),firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#lastname")).getAttribute("value"),lastName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#email")).getAttribute("value"),email);
        //product review
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        driver.findElement(By.xpath("//h2//a[@title='Samsung Galaxy']")).click();
        driver.findElement(By.xpath("//a[text()='Add Your Review']")).click();

        driver.findElement(By.cssSelector("input[id='Quality 1_5']")).click();
        driver.findElement(By.cssSelector("textarea#review_field")).sendKeys("Good app\nMai dinh");
        driver.findElement(By.cssSelector("input#summary_field")).sendKeys("Good product");
        driver.findElement(By.cssSelector("input#nickname_field")).clear();
        driver.findElement(By.cssSelector("input#nickname_field")).sendKeys("huyen");

        driver.findElement(By.cssSelector("button[title='Submit Review']")).click();
        Thread.sleep(3000); // nghỉ 3 giây

        driver.findElement(By.xpath("//button[text()='Send anyway']")).click();
        Thread.sleep(3000); // nghỉ 3 giây

        //tuyet doi
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(),
                "Your review has been accepted for moderation.");







        //logout
        driver.findElement(By.cssSelector("div.account-cart-wrapper>a")).click();
        driver.findElement(By.xpath("//a[@title='Log Out']")).click();





    }
    @Test
    public void TC_02(){

    }


    @AfterClass
    public void cleanBrowser(){
        driver.quit(); //dong trinh duyet
    }


}