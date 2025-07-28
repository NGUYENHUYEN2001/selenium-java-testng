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
import org.openqa.selenium.support.Color;


import java.awt.*;
import java.util.List;

public class Topic_19_Action_P3 {

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
    public void TC_01_Right_click() throws InterruptedException {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        action.contextClick(driver.findElement(By.cssSelector("span.btn-neutral"))).perform();
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Quit']")).isDisplayed());
        //hover mouse
        action.moveToElement(driver.findElement(By.xpath("//span[text()='Quit']"))).perform();
        // verify
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());
        // click quit
        action.click(driver.findElement(By.xpath("//span[text()='Quit']"))).perform();
        driver.switchTo().alert().accept();

        Assert.assertFalse(driver.findElement(By.xpath("//span[text()='Quit']")).isDisplayed());




    }
    @Test
    public void TC_02_Drag_And_Drop() throws InterruptedException {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        WebElement small = driver.findElement(By.cssSelector("div#draggable"));
        WebElement large = driver.findElement(By.cssSelector("div#droptarget"));

        action.dragAndDrop(small, large).perform();

        Assert.assertEquals(large.getText(),"You did great!");
        Assert.assertEquals(Color.fromString(large.getCssValue("background-color")).asHex().toUpperCase(),"#03A9F4");

    }

    @Test
    public void TC_03_ScrollToElement() throws InterruptedException {
        driver.get("https://live.techpanda.org/index.php/about-magento-demo-store");
        Thread.sleep(3000);
        action.scrollToElement(driver.findElement(By.cssSelector("input#newsletter"))).perform();
        Thread.sleep(3000);

    }
    //3 - Clean: Delete data test/ account/ close browser...
    @AfterClass
    public void cleanBrowser(){
        driver.quit(); //dong trinh duyet
    }


}