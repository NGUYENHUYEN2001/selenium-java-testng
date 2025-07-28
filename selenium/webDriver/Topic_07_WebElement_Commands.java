package webDriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.security.Key;

public class Topic_07_WebElement_Commands {
    //chua cac ham de tuong tac voi Browser
    WebDriver driver;
    WebElement element;

    @BeforeClass
    public void initialBrowser(){
        driver = new ChromeDriver();//khoi tao browser
    }
    @Test
    public void TC_01_WebbElement(){
        //Dung 1 lan
        driver.findElement(By.xpath("")).click(); //**
        element = driver.findElement(By.xpath(""));
        // click vao cac element dang: button/ checkbox/ radio/ link/ image/icon..
        element.click();
        //Nhap lieu vao cac element dang: textbox/ textarea/ dropdown (edit)
        element.clear(); // xoa du lieu truoc khi sendkey //*
        element.sendKeys("dam@gmail.com"); //**
        element.sendKeys(Keys.COMMAND);

        driver.findElement(By.cssSelector(""))
                .findElement(By.cssSelector(""))
                .findElement(By.id(""));
        driver.findElement(By.cssSelector(""));//**
        //tac dung voi form signUp/ login/search
        //phaia co the form
        element.submit();
        //Ap dung cho tat ca cac loai element
        //kiem tra 1 element co hien thi hay khong
        // size >0:width/height>0
        //nhin thay/thao tac duoc
        element.isDisplayed();//**
        Assert.assertTrue(element.isEnabled());
        Assert.assertFalse(element.isEnabled());

        //Ap dung cho duy nhat 3 loai: checkbox/ radio/ dropdown (Default)
        // KIEM tra 1 element da duoc chon roi hay chua chon
        element.isSelected();//**
        Assert.assertTrue(element.isSelected());
        Assert.assertFalse(element.isSelected());

        //ap dung cho tat ca cac loai
        //kiem tra 1 element co bi disable hay ko
        element.isEnabled();

        //lay ra gia tri background-color
        element.getCssValue("background-color"); //*
        //#f825773
        //GUI: font / color/  size
        element.getCssValue("font-size");
        //14px

        // Ap dung cho element chua text: link/ button/ header/ label
        element.getText();//**

        element.getAttribute("placeholder");// Search store

        Dimension dimension = driver.manage().window().getSize();

        //chieu rong/ chieu cao cua element
        Dimension dimension1 = element.getSize();

        Point pointBrowser = driver.manage().window().getPosition();
        //vi tri cua element so voi viewport
        Point point = element.getLocation();

        Rectangle rectangle=element.getRect();
        //size
        rectangle.getWidth();
        rectangle.getHeight();
        rectangle.getDimension();
        //Location
        rectangle.getX();
        rectangle.getY();
        rectangle.getPoint();

        //lay ra html cua element
        //element A
        String tagname = driver.findElement(By.cssSelector("")).getTagName();

        //element B
        driver.findElement(By.xpath("" + tagname + ""));

        //
        element.getAccessibleName();

        element.getAriaRole();

        element.getDomAttribute("");

        element.getDomProperty("");//*
        //popup
        element.getShadowRoot(); //**
        //Framework: HTML report
        element.getScreenshotAs(OutputType.FILE); //*


        //hay dung
        //element.click,sendKey... 9-10 ham
        // 10 ham web element, 5 ham web browser, 15 ham selenium


    }


    }





