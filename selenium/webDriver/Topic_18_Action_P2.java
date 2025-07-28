package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class Topic_18_Action_P2 {

    WebDriver driver;
    Actions action;
    String osName= System.getProperty("os.name");
    Keys keys;

    @BeforeClass
    public void initialBrowser(){
        driver = new ChromeDriver();//khoi tao browser
        action = new Actions(driver);
        if (osName.startsWith("Windows")){
            keys = Keys.CONTROL;
        } else{
            keys=Keys.COMMAND;
        }
    }

    @Test
    public void TC_01_Click_and_hold_block() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));

        action.clickAndHold(allNumbers.get(0))// click vao so 1 va giu chuot
                .moveToElement(allNumbers.get(3))// di chuot toi so 4
                .release()// nha chuot trai ra (ket thuc cho su kien click and hold()
                .perform();// Thuc thi cac cau lenh tren (neu khong co thi khong thuc thi)
        Thread.sleep(2000);
        List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        Assert.assertEquals(allNumberSelected.size(), 4);
    }
    @Test
    public void TC_02_Click_and_hold_block_Random(){
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
        Assert.assertEquals(allNumbers.size(), 20);

        // nhấn phím giữ xuống chưa nhả ra
        action.keyDown(keys).perform();
        action.click(allNumbers.get(0))
                .click(allNumbers.get(3))
                .click(allNumbers.get(2))
                .click(allNumbers.get(1))
                .perform();

        // nhả phím Ctrl ra
        action.keyUp(keys).perform();
        List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        Assert.assertEquals(allNumberSelected.size(), 4);
    }

    @Test
    public void TC_03_DoubleClick(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();

        Assert.assertEquals(driver.findElement(By.cssSelector("s")).getText(),
                "Hello Automation Guys!");

    }
    //3 - Clean: Delete data test/ account/ close browser...
    @AfterClass
    public void cleanBrowser(){
        driver.quit(); //dong trinh duyet
    }


}