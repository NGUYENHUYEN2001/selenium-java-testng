package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_22_Random_Popup {

    WebDriver driver; //khai bao bien

    @BeforeClass
    public void initialBrowser(){
        driver = new FirefoxDriver();//khoi tao browser
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(45));
    }

    @Test
    public void TC_01_JavaCodeGeeks() throws InterruptedException {
        driver.get("https://www.javacodegeeks.com/");

        By newLetterPoup = By.xpath("//div[@data-title='Newsletter Free eBooks' "+
                "and not(contains(@style,'display:none'))]");
        //Hiển thị thì close đi
        if(driver.findElements(newLetterPoup).size() > 0 && driver.findElements(newLetterPoup).get(0).isDisplayed()){
            System.out.println("... go to if ...");
            driver.findElement(By.xpath("//div[@data-title='Newsletter Free eBooks' " +
                    "and not(contains(@style,'display:none'))]//a[contains(@onclick,'lepopup_close')]")).click();
            Thread.sleep(3000);
        }
        // ko hiển thị thì action luôn
        System.out.println("--- ko vao if ----");
        driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile");
        driver.findElement(By.cssSelector("button#search-submit")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("header>h1.page-title")).isDisplayed());



    }
    @Test
    public void TC_02_() throws InterruptedException {
        driver.get("https://vnk.edu.vn/");
        By popup= By.cssSelector("div.popmake-content");

        if ( driver.findElements(popup).size()>0 && driver.findElement(popup).isDisplayed() ){
            System.out.println("... go to if ...");
            driver.findElement(By.cssSelector("button.popmake-close")).click();
        }
        System.out.println("--- ko vao if ----");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//ul[@id='mega-menu-primary']//a[text()='Liên hệ']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("div.title-content > h1")).isDisplayed());


    }
    @Test
    public void TC_03_() throws InterruptedException {
        driver.get("https://dehieu.vn/");

        By popup = By.cssSelector("div.modal-content");
        //Hiển thị thì close đi
        if(driver.findElements(popup).size() > 0 && driver.findElements(popup).get(0).isDisplayed()){
            System.out.println("... go to if ...");
            driver.findElement(By.cssSelector("button.close")).click();
            Thread.sleep(3000);

            // verify popup k hiển thị trong popup
            Assert.assertFalse(driver.findElement(popup).isDisplayed());
        }
        // ko hiển thị thì action luôn
        System.out.println("... ko vao if ...");
        driver.findElement(By.cssSelector("input.search-form")).sendKeys("Khoá học đọc bản vẽ cơ điện chuyên nghiệp");
        driver.findElement(By.cssSelector("button.header-search")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.course-item-detail a")).getAttribute("title"),"Khoá học đọc bản vẽ cơ điện chuyên nghiệp");



    }
    @AfterClass
    public void cleanBrowser(){
        driver.quit(); //dong trinh duyet
    }


}