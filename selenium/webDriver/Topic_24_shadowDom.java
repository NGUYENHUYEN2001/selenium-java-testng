package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_24_shadowDom {

    WebDriver driver; //khai bao bien

    @BeforeClass
    public void initialBrowser(){
        driver = new ChromeDriver();
    }

    @Test
    public void TC_01(){
        driver.get("https://automationfc.github.io/shadow-dom/");

        // Thuoc DOM cha ben ngoai
        driver.findElement(By.xpath("//a[text()='scroll.html']"));
        // lay ra element cha chua shadow hoost thu 1
        WebElement shadowHostParent = driver.findElement(By.cssSelector("div#shadow_host"));
        // lay ra element shadow root
        SearchContext firstShadow = shadowHostParent.getShadowRoot();

        Assert.assertEquals(firstShadow.findElement(By.cssSelector("span[class='info']")).getText(),"some text");

        Assert.assertFalse(firstShadow.findElement(By.cssSelector("input[type='checkbox']")).isSelected());

        // lay ra element cha chua shadow root thu 2
        WebElement shadowHostParentSecond = firstShadow.findElement(By.cssSelector("div#nested_shadow_host"));
        // lay ra element shadow root
        SearchContext secondShadow = shadowHostParentSecond.getShadowRoot();
        Assert.assertEquals(secondShadow.findElement(By.cssSelector("div#nested_shadow_content>div")).getText(),"nested text");




    }
    @Test
    public void TC_02() throws InterruptedException {
        driver.get("https://books-pwakit.appspot.com/");

        WebElement Element =driver.findElement(By.xpath("//book-app"));
        SearchContext firstShadowRoot=Element.getShadowRoot();

        firstShadowRoot.findElement(By.cssSelector("input#input")).sendKeys("Harry Potter");
        Thread.sleep(2000);

        WebElement Element2= firstShadowRoot.findElement(By.cssSelector("book-input-decorator"));
        SearchContext secondShadowRoot=Element2.getShadowRoot();
        secondShadowRoot.findElement(By.cssSelector("div.icon")).click();
        Thread.sleep(2000);

        WebElement Element3=firstShadowRoot.findElement(By.cssSelector("book-explore"));
        SearchContext thirdShadowRoot=Element3.getShadowRoot();
        Thread.sleep(2000);


        WebElement Element4 = thirdShadowRoot.findElement(By.cssSelector("ul>li:nth-of-type(1)>book-item"));
        SearchContext fourthShadowRoot=Element4.getShadowRoot();

        Assert.assertEquals(fourthShadowRoot.findElement(By.cssSelector("h2.title")).getText(),"Harry Potter");


    }


    //3 - Clean: Delete data test/ account/ close browser...
    @AfterClass
    public void cleanBrowser(){
        driver.quit(); //dong trinh duyet
    }


}