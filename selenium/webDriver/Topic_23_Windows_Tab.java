package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;

public class Topic_23_Windows_Tab {

    WebDriver driver; //khai bao bien

    @BeforeClass
    public void initialBrowser(){
        driver = new ChromeDriver();//khoi tao browser

    }

    @Test
    public void TC_01() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        // Lưu lại handle của tab hiện tại
        String githubWindowID = driver.getWindowHandle();

        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        // Chờ tab mới mở (1-2 giây để chắc chắn)
        Thread.sleep(2000);
        // switch qua tab Google
        switchToWindowById(githubWindowID);
        // Lay ra title cua tab moi
        String titleGoogle = driver.getTitle();
        System.out.println("The title is (Google) " + titleGoogle);

        String googleWindowId =driver.getWindowHandle();
        // switch ve tab Github (parent window)
        switchToWindowById(googleWindowId);
        Thread.sleep(2000);
        System.out.println("The title is (Selenium Webdriver)" + driver.getTitle());

        //Click vào facebooklink => nó sẽ bật lên 1 tab mới và tự nhảy qua
        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        Thread.sleep(2000);

        switchToWindowByTitle("Facebook - log in or sign up");
        System.out.println("The title is (Facebook) " + driver.getTitle());

        // quay ve github
        switchToWindowByTitle("Selenium WebDriver");
        Thread.sleep(4000);
        System.out.println("The title (Selenium Webdriver) is " + driver.getTitle());
        //Click vào tiki link => nó sẽ bật lên 1 tab mới và tự nhảy qua
        driver.findElement(By.xpath("//a[text()='TIKI']")).click();
        Thread.sleep(2000);
        // switch qua tiki
        switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
        Thread.sleep(2000);
        System.out.println("The title tiki is " + driver.getTitle());

        closeAllWindows(githubWindowID);
        System.out.println("The title (Selenium Webdriver) is " + driver.getTitle());


    }


    @Test
    public void TC_02_TechPanda() throws InterruptedException {

        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        //click add to compare xperia
        driver.findElement(By.xpath("//a[text()='Sony Xperia']" +
                "/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
        driver.findElement(By.cssSelector("li.success-msg span")).getText();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
                "The product Sony Xperia has been added to comparison list.");
        // click add to compare galaxy
        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']" +
                "/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
        driver.findElement(By.cssSelector("li.success-msg span")).getText();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
                "The product Samsung Galaxy has been added to comparison list.");

        driver.findElement(By.xpath("//button[@title='Compare']")).click();
        switchToWindowByTitle("Products Comparison List - Magento Commerce");
        Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
        driver.findElement(By.xpath("//button[@title='Close Window']")).click();
        Thread.sleep(2000);
        switchToWindowByTitle("Mobile");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//a[text()='Clear All']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.switchTo().alert().getText(),
                "Are you sure you would like to remove all products from your comparison?");
        driver.switchTo().alert().accept();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
                "The comparison list was cleared.");
    }
    private void closeAllWindows(String githubWindowID) throws InterruptedException {
        // lấy hết toàn bộ các ID của window/ tab
        Set<String> allWindows = driver.getWindowHandles();
        // Dùng vòng lặp để duyệt qua từng ID một
        for (String id : allWindows) {
            if(!id.equals(githubWindowID)){
                driver.switchTo().window(id);
                Thread.sleep(2000);
                driver.close();
            }
        }
        // switch vào tab duy nhất còn lại
        driver.switchTo().window(githubWindowID);
    }

    private void switchToWindowByTitle(String expectedPageTitle) {
        Set<String> allWindows = driver.getWindowHandles();

        // Dùng vòng lặp để duyệt qua từng ID một
        for (String id : allWindows) {
            // mỗi lần duyệt sẽ switch vào trước
            driver.switchTo().window(id);
            // get ra title của window/ tab hiện tại
            String pageTitle = driver.getTitle();
            // kiểm tra title
            if(pageTitle.equals(expectedPageTitle)){
                break;
            }
        }
    }

    // chỉ đúng với 2 tab
    private void switchToWindowById(String windowID) {
        // Lấy ra hết tất cả các ID của window/ tab hiện tại
        Set<String> allWindows = driver.getWindowHandles();
        // Dùng vòng lặp để duyệt qua từng ID một
        for (String id : allWindows) {
            // kiểm tra điều kiện : nếu ID nào mà khác với ID mong đợi thì switch qua
            if(!id.equals(windowID)){
                driver.switchTo().window(id); // Chuyển qua tab mới
                break;
            }
        }
    }

    @AfterClass
    public void cleanBrowser(){

        driver.quit();
    }


}