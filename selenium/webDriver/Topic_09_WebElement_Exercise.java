package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.security.Key;

public class Topic_09_WebElement_Exercise {


    WebDriver driver;
    @BeforeClass
    public void initialBrowser(){
        driver = new ChromeDriver();
    }

    @Test
    public void TC_01_isDisplayed(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement EmailTextbox = driver.findElement(By.cssSelector("input#mail"));
        if(EmailTextbox.isDisplayed()){
            System.out.println("Email is Displayed");
            EmailTextbox.sendKeys("Automation Testing");
        }else {
            System.out.println("Email is not Displayed");
        }
        WebElement AgeUnder18 = driver.findElement(By.cssSelector("input#under_18"));
        if(EmailTextbox.isDisplayed()){
            System.out.println("AgeUnder18 is Displayed");
            AgeUnder18.click();
        }else {
            System.out.println("AgeUnder18 is not Displayed");
        }
        WebElement eduTextArea = driver.findElement(By.cssSelector("textarea#edu"));
        if(eduTextArea.isDisplayed()){
            System.out.println("eduTextArea is Displayed");
            eduTextArea.sendKeys("Automation Testing");
        }else {
            System.out.println("eduTextArea is not Displayed");
        }
        WebElement User5 = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
        if(User5.isDisplayed()){
            System.out.println("User5 is Displayed");

        }else {
            System.out.println("User5 is not Displayed");
        }
    }
    @Test
    public void TC_02_isEnable(){
        //isDisplayed: element co the nhin thay
        //isEnable: element co the tuong tac len dc
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement EmailTextbox = driver.findElement(By.cssSelector("input#mail"));
        if(EmailTextbox.isEnabled()){
            System.out.println("Email is Enable");
        }else {
            System.out.println("Email is disable");
        }
        WebElement AgeUnder18 = driver.findElement(By.cssSelector("input#under_18"));
        if(EmailTextbox.isEnabled()){
            System.out.println("AgeUnder18 is Enable");
        }else {
            System.out.println("AgeUnder18 is disable");
        }

        WebElement passwordTextbox = driver.findElement(By.cssSelector("input#disable_password"));
        if(passwordTextbox.isEnabled()){
            System.out.println("Password Textbox is Enable");
        }else {
            System.out.println("Password Textbox is disable");
        }

        WebElement biography = driver.findElement(By.cssSelector("textarea#bio"));
        if(biography.isEnabled()){
            System.out.println("Biography Text Area is Enable");
        }else {
            System.out.println("Biography Text Area is disable");
        }

    }
    @Test
    public void TC_03_isSelected(){
        //isSelected: kiem tra 1 element dc chon thanh cong: radio/ checkbox
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement AgeUnder18Radio = driver.findElement(By.cssSelector("input#under_18"));
        if(AgeUnder18Radio.isSelected()){
            System.out.println("AgeUnder18 is selected");
        }else {
            System.out.println("AgeUnder18 is de-selected");
        }

        WebElement Languagues = driver.findElement(By.cssSelector("input#java"));
        if(Languagues.isSelected()){
            System.out.println("Languagues is selected");
        }else {
            System.out.println("Languagues is de-selected");
        }
        AgeUnder18Radio.click();
        Languagues.click();

        if(AgeUnder18Radio.isSelected()){
            System.out.println("AgeUnder18 is selected");
        }else {
            System.out.println("AgeUnder18 is de-selected");
        }
        if(Languagues.isSelected()){
            System.out.println("Languagues is selected");
        }else {
            System.out.println("Languagues is de-selected");
        }
    }
    @Test
    public void TC_04_Mailchimp(){
        driver.get("https://login.mailchimp.com/signup/");
        driver.findElement(By.cssSelector("input#email")).sendKeys("automationfc@gmail.com");
        driver.findElement(By.cssSelector("input#email")).sendKeys(Keys.TAB);

        //Only number
        WebElement password = driver.findElement(By.cssSelector("input#new_password"));
        password.sendKeys("123");
        password.sendKeys(Keys.TAB);

        Assert.assertTrue(driver.findElement(By.cssSelector(".uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".username-check.completed")).isDisplayed());

        //Only uppercase characters
        password.clear();
        password.sendKeys("TESTING");
        password.sendKeys(Keys.TAB);

        Assert.assertTrue(driver.findElement(By.cssSelector(".uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".username-check.completed")).isDisplayed());
        //Only special character
        password.clear();
        password.sendKeys("!@#");
        password.sendKeys(Keys.TAB);

        Assert.assertTrue(driver.findElement(By.cssSelector(".uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".username-check.completed")).isDisplayed());

        //Full
        password.clear();
        password.sendKeys("Testing123!");
        password.sendKeys(Keys.TAB);

        Assert.assertFalse(driver.findElement(By.cssSelector(".uppercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector(".number-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector(".special-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector(".username-check.completed")).isDisplayed());

        // contain username
        password.clear();
        password.sendKeys("Automation");
        password.sendKeys(Keys.TAB);

        Assert.assertTrue(driver.findElement(By.cssSelector(".uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".username-check.not-completed")).isDisplayed());










    }
    @AfterClass
    public void cleanBrowser(){
       driver.quit();
    }


}