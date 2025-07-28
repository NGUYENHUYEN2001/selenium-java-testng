package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Action_Part1 {

    WebDriver driver; //khai bao bien
    Actions action;

    @BeforeClass
    public void initialBrowser(){
        driver = new ChromeDriver();//khoi tao browser
        action = new Actions(driver);
        driver.manage().window().maximize();


    }

    @Test
    public void TC_01_Hover(){
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        WebElement ageTextbox = driver.findElement(By.cssSelector("INPUT#age"));
        action.moveToElement(ageTextbox).perform();
        Assert.assertTrue(driver.findElement(By.cssSelector("div.ui-tooltip-content")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),
                "We ask for your age only for statistical purposes.");



    }
    @Test
    public void TC_02(){
        driver.get("https://www.myntra.com/");
        action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Kids']"))).perform();
        driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()='Home & Bath']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(),"Kids Home Bath");

    }

    @Test
    public void TC_03_fahasa() throws InterruptedException {
        driver.get("https://www.fahasa.com/");
       //
        action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
        Thread.sleep(2000);
        action.moveToElement(driver.findElement(By.xpath("//span[text()='Sách Trong Nước']"))).perform();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class='fhs_menu_content fhs_column_left']//a[text()='Kỹ Năng Sống']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//ol[@class='breadcrumb']//strong[text()='Kỹ năng sống']")).isDisplayed());
    }
    //3 - Clean: Delete data test/ account/ close browser...
    @AfterClass
    public void cleanBrowser(){
        driver.quit(); //dong trinh duyet
    }


}