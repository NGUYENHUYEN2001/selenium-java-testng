package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Authentication_Alert {
    WebDriver driver; //khai bao bien
    String username="admin";
    String password="admin";
    @BeforeClass
    public void initialBrowser(){
        driver = new ChromeDriver();//khoi tao browser
    }

    @Test
    public void TC_01_Authentication_URL(){
        // http / https:// + username + : + password + @Url
        driver.get("http://" + username+":"+password + "@" + "the-internet.herokuapp.com/basic_auth");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.example>p")).getText(),
                "Congratulations! You must have the proper credentials.");


    }
    @Test
    public void TC_02_Authentication_Navigate(){
        driver.get("http://the-internet.herokuapp.com/");
        String basicAuthLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
        driver.get(getURL(basicAuthLink, username, password));
        Assert.assertEquals(driver.findElement(By.cssSelector("div.example>p")).getText(),
                "Congratulations! You must have the proper credentials.");


    }
    public String getURL(String link, String username, String password){
        String[] linkArray = link.split("//");
        link = linkArray[0] + "//" +username + ":" + password + "@" + linkArray[1];
        return link;

    }


    //3 - Clean: Delete data test/ account/ close browser...
    @AfterClass
    public void cleanBrowser(){
        driver.quit(); //dong trinh duyet
    }


}