package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_25_javascriptExcecutor {

    WebDriver driver;
    JavascriptExecutor jsExecutor;

    WebDriverWait explicitWait;

    @BeforeClass
    public void initialBrowser(){
        driver = new ChromeDriver();//khoi tao browser
        jsExecutor = (JavascriptExecutor) driver;// ep kieu tuong minh

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    @Test
    public void TC_01() throws InterruptedException {
        jsExecutor.executeScript("window.location='http://live.techpanda.org/'");
        Thread.sleep(3000);

        String techPandaDomain = (String) jsExecutor.executeScript("return document.domain;");
        Assert.assertEquals(techPandaDomain, "live.techpanda.org");

        String URLPage= (String) jsExecutor.executeScript("return document.URL;");
        Assert.assertEquals(URLPage, "http://live.techpanda.org");


        jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.xpath("//a[text()='Mobile']")));


        jsExecutor.executeScript("arguments[0].click()",driver.findElement(
                By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button")));

        String SamsungText = (String) jsExecutor.executeScript("return document.documentElement.innerText;");
        Assert.assertTrue(SamsungText.contains("Samsung Galaxy was added to your shopping cart."));

        jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.xpath("//a[text()='Customer Service']")));
        Thread.sleep(3000);

        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector("input#newsletter")));








    }
    @Test
    public void TC_02(){

    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit(); //dong trinh duyet
    }


}