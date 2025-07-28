package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class Topic_02_Selenium_Locator {


    //khai bao
    WebDriver driver; //khai bao bien

    // bien
    // Access Modifier - kieu du lieu - ten bien - gia tri cua bien
    private String fullName="Automation Testing";


    @BeforeClass
    public void initialBrowser(){
        // khoi tao
        driver = new ChromeDriver();

        //Mo app len den man hinh login
        driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");
    }

    //2 - Action/ Execute: Tuong tac len element nao/ nhap lieu/ verify...

    @Test
    public void TC_01_ID() throws InterruptedException {

        driver.findElement(By.id("small-searchterms"));

        try {// ko loi
            Thread.sleep(3000);
        } catch (InterruptedException e) {//co loi
            throw new RuntimeException(e);
        }
        driver.findElement(By.id("FirstName"));
        Thread.sleep(3000);

    }
    @Test
    public void TC_02_Class() throws InterruptedException {
        //gia tri trong class ko co khoang trang: lay toan bo
        // gia tri trong class co khoang trang: lay phan nao la duy nhat
        driver.findElement(By.className("register-next-step-button"));
        Thread.sleep(3000);
    }
    @Test
    public void TC_03_Name(){

        driver.findElement(By.name("LastName"));
    }
    @Test
    public void TC_04_LinkText(){
    // chi lam viec voi element la link va co text
        // The a va thuoc tinh href
        //Phai lay het toan bo text, khong chua cai nao het (tuyet doi)
        driver.findElement(By.linkText("Register"));
        driver.findElement(By.linkText("Log in"));
        driver.findElement(By.linkText("Wishlist"));

    }
    @Test
    public void TC_05_PartialLinkText(){

        // chi lam viec voi element la Link
        //co the lay het toan bo text hoac 1 phan (hay dung)
        driver.findElement(By.partialLinkText("Digital"));
        driver.findElement(By.partialLinkText("downloads "));

    }
    @Test
    public void TC_06_Tagname(){

        //Ten the (html)
        //khi muon tim tat ca element giống nhau (the của component giong nhau)
        // tất cả các textbox/ checkbox/ radio/ Link/ button/ ...
        driver.findElements(By.tagName("button"));

    }
    @Test
    public void TC_07_Css(){
        //css cover id, cach viet cua css ket hop id: input#Company
        driver.findElement(By.cssSelector("input#Company"));
        // bỏ tagname: #Company
        driver.findElement(By.cssSelector("#Company"));
        // cu phap chuan: the - tagname -
        driver.findElement(By.cssSelector("input[id='Company']"));
        // ket hop class:
        driver.findElements(By.cssSelector("button.register-next-step-button"));
        // cu phap chuan: the - tagname -
        driver.findElement(By.cssSelector("button[class='button-1 register-next-step-button']"));

        //name
        //LinkText
        driver.findElement(By.cssSelector("a[href='register?returnUrl=%2Fregister']"));
        driver.findElement(By.cssSelector("a[href*='a[href='login?returnUrl=%2Fregister']"));

        //partial
        driver.findElement(By.cssSelector("a[href*='register?']"));
        driver.findElement(By.cssSelector("a[href*='a[href*='login?']"));

        //tagname
        driver.findElement(By.cssSelector("a"));
        driver.findElement(By.cssSelector("button"));
    }
    @Test
    public void TC_08_XPath(){
        //id
        driver.findElement(By.xpath("//input[@id='small-searchterms']"));
        //class
        driver.findElement(By.xpath("//button[@class='button-1 register-next-step-button']"));
        driver.findElement(By.xpath("//button[contains(@class,'register-next-step-button')]"));
        //name
        driver.findElement(By.xpath("//select[@name='DateOfBirth']"));
        //LInktext
        driver.findElement(By.xpath("//a[text()='Register']"));
        driver.findElement(By.xpath("//a[contains(text(),'Register')]"));
        //tagname
        driver.findElement(By.xpath("//a"));

    }

    @Test
    public void TC_09_Relative_Locator(){

        // element / By A
        WebElement passwordTextbox = driver.findElement(By.cssSelector("input#Password"));
        By passwordBy = By.cssSelector("input#Password");

        // element / By B

        By checkboxBy = By.cssSelector("input#RememberMe");

        // element / By C
        By forgotPasswordlinkBy = By.cssSelector("a[href='/passwordrecovery']");

        // element / By D
        By loginButtonBy = By.cssSelector("button.login-button");

        // element / By E

        WebElement rememberMeLabelText = driver.findElement(RelativeLocator.with(By.tagName("label"))
                .above(loginButtonBy)// label dang nam tren login button
                .below(passwordBy)//label nam duoi password
                .toLeftOf(forgotPasswordlinkBy)// label nam ben trai so voi forgot Password link
                .toRightOf(checkboxBy));//label nam ben phai so voi checkbox
    }
    //1 - khi ko the dinh danh duoc element cua chinh no (dua vao vi tri ben canh/gan do)
    // 2 - su dung de test GUI (giao dien - position khop vs design ko)

    //<input type="email" autocapitalize="off" autocorrect="off" spellcheck="false" name="login[username]"
    // value="" id="email" class="input-text required-entry validate-email" title="Email Address">

    //input[@type='email']
    //input[@autocapitalize='off']
    // input[@autocorrect='off']
    //input[@spellcheck='false']
    //input[@name='login[username]']
    //input[@value='']
    //input[@id='email']
    //input[@class='input-text required-entry validate-email']
    //input[@title='Email Addres']

    //1 - duy nhat
    // name/ id / title
    //2 - uu tien neu co id/ class/ name thi dung truoc
    // id/name
    //3 - ko co id/ class/ name thi dung bat ky 1 attribute khac
   // 4 - gia tri cua attribute phai co y nghia - lien quan den element do
    // =>toi uu nhat
    // 5- link" ko nen dung link voi attribute la href vi co the thay doi server test




    @Test
    public void TC_01(){

    //<input class="email" autofocus="" type="email" data-val="true"
        // data-val-regex="Wrong email"
        // data-val-regex-pattern="^(([^&lt;&gt;()\[\]\\.,;:\s@&quot;]+(\.[^&lt;&gt;()\[\]\\.,;:\s@&quot;]+)*)|(&quot;.+&quot;))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$
        // " data-val-required="Please enter your email"
        // id="Email" name="Email">

        //HTML source code
        // The - thuoc tinh - gia tri thuoc tinh
        //tagname - Attribute(id, name, class)  - value

        //Css-XPath
        //XPath: //tagname[@attribute='value']
        //Css: tagname[attribute='value']
        //Tuong tac len Email address textbox
        // 8 loai locator de tim amail address


        // Tim 1 element
        driver.findElement(By.id(""));

        // 1- Thao tac len luon (dung 1 lan)
        // neu chi dung 1 lan thi khong can khai bao bien
        driver.findElement(By.id("")).click();

        //2- Luuw du lieu lai (dung nhieu lan)
        // neu  dung nhieu lan thi can khai bao bien

        WebElement emailTextbox= driver.findElement(By.id(""));
        emailTextbox.clear();
        emailTextbox.sendKeys(fullName);
        emailTextbox.sendKeys();
        //Tim nhieu element giong nhau
        List<WebElement> textboxes =driver.findElements(By.cssSelector(""));




    }
    @Test
    public void TC_02(){
    }


    //3 - Clean: Delete data test/ account/ close browser...
    @AfterClass
    public void cleanBrowser(){
        driver.quit(); //dong trinh duyet
    }


}