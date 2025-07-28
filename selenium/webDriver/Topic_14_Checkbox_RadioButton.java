package webDriver;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class Topic_14_Checkbox_RadioButton {

    WebDriver driver; //khai bao bien
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void initialBrowser(){
        driver = new ChromeDriver();

        jsExecutor = (JavascriptExecutor) driver;
    }


    @Test
    public void TC_01_telerik() throws InterruptedException {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        //Verify checkbox/radio is enable/ disable
        Thread.sleep(7000);
       // driver.findElement(By.xpath("//button[text()='Accept Cookies']")).click();
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Leather trim']/preceding-sibling::span/input")).isEnabled());
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Towbar preparation']/preceding-sibling::span/input")).isEnabled());

        //Verify checkbox/ radio is selected/ deselected
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Leather trim']/preceding-sibling::span/input")).isSelected());
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Towbar preparation']/preceding-sibling::span/input")).isSelected());

        //click to "Dual-zone air conditioning"
        By dual = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");
        //scroll xuong them 1 doan
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)");
       // neu nhu chua chon thi moi click
        if(!driver.findElement(dual).isSelected()){
            driver.findElement(dual).click();
        }
        Assert.assertTrue(driver.findElement(dual).isSelected());
        //de- click to "Dual-zone air conditioning" (bo chon)
        if(driver.findElement(dual).isSelected()){
            driver.findElement(dual).click();
        }
        Assert.assertFalse(driver.findElement(dual).isSelected());

        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)");

        By petro = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input");
        // neu nhu chua chon thi moi click
        if(!driver.findElement(petro).isSelected()){
            driver.findElement(petro).click();
        }
        Assert.assertTrue(driver.findElement(petro).isSelected());

    }
    @Test
    public void TC_02_Multiple() throws InterruptedException {
        driver.get("https://automationfc.github.io/multiple-fields/");
        // select all checkboxes
        List<WebElement> checkboxes =driver.findElements(By.cssSelector("span.form-checkbox-item>input"));
        //click all checkboxes
        for (WebElement checkbox : checkboxes) {
            if(!checkbox.isSelected()){
                checkbox.click();
            }
        }
        //verfify all checkboxes selected
        for (WebElement checkbox : checkboxes) {
            Assert.assertTrue(checkbox.isSelected());
        }
        //Deselect all checkboxed
        for (WebElement checkbox : checkboxes) {
            if(checkbox.isSelected()){
                checkbox.click();
            }
        }
        //verfify all checkboxes de-selected
        for (WebElement checkbox : checkboxes) {
            Assert.assertFalse(checkbox.isSelected());
        }
        // select 1 in all checkboxes + verify
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)");
        driver.findElement(By.cssSelector("input[value='Gout']")).click(); ;
        driver.findElement(By.cssSelector("input[value='Diabetes']")).click(); ;

        Assert.assertTrue(driver.findElement(By.cssSelector("input[value='Gout']")).isSelected());
        Assert.assertTrue(driver.findElement(By.cssSelector("input[value='Diabetes']")).isSelected());

        // select 1 in all checkboxes + verify
        for (WebElement checkbox : checkboxes) {
            if(!checkbox.isSelected() && checkbox.getAttribute("value").equals("Gout")){
                checkbox.click();
            }
            Assert.assertTrue(driver.findElement(By.cssSelector("input[value='Gout']")).isSelected());

            for (int i = 0; i < checkboxes.size(); i++) {
                int number = new Random().nextInt(checkboxes.size());
                checkboxes.get(number).click();
            }

        }
    }
    @Test
    public void TC_03_angular() throws InterruptedException {
        driver.get("https://material.angular.dev/components/radio/examples");
        if(!driver.findElement(By.cssSelector("input[value='Summer']")).isSelected()){
            driver.findElement(By.cssSelector("input[value='Summer']")).click();
        }
        Assert.assertTrue(driver.findElement(By.cssSelector("input[value='Summer']")).isSelected());

        driver.get("https://material.angular.dev/components/checkbox/examples");
        Thread.sleep(5000);
        //chon
        if(!driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div/input")).isSelected()){
            driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div/input")).click();
        }
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div/input")).isSelected());

        if(!driver.findElement(By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input")).isSelected()){
            driver.findElement(By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input")).click();
        }
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input")).isSelected());
        //bo chon
        if(driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div/input")).isSelected()){
            driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div/input")).click();
        }
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div/input")).isSelected());

        if(driver.findElement(By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input")).isSelected()){
            driver.findElement(By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input")).click();
        }
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input")).isSelected());
    }

    @Test
    public void TC_04_ubuntu(){
        driver.get("https://login.ubuntu.com/");
        // The input: dung de click
        // dung de verify: isSelected()

       // By newUserRadio = By.cssSelector("input#id_new_user");
        //1 - dung the input de click => loi
        // dung the input de verify => pass
        //driver.findElement(By.cssSelector("input#id_new_user")).click();
        //Assert.assertFalse(driver.findElement(By.cssSelector("input#id_new_user")).isSelected());

       // newUserRadio = By.cssSelector("label.new-user");
        //2  - Dung the khac input de click => pass
        // dung the verify -> fail
        // isSelected() -> dung cho the input/option

        //driver.findElement(newUserRadio).click();
      //  Assert.assertTrue(driver.findElement(newUserRadio).isSelected());
        //
        //3 - Dung 1 the khac de click => pass
        // dung the input nay de verify -> pass
       //By newUserRadioLabel = By.cssSelector("label.new-user");
     // By newUserRadioInput = By.cssSelector("input#id_new_user");
      // driver.findElement(newUserRadioLabel).click();
      // Assert.assertTrue(driver.findElement(newUserRadioInput).isSelected());

       // 4- dung duy nhat the input de click/verify dung js executor
        By newUserRadioInput = By.cssSelector("input#id_new_user");
        jsExecutor.executeScript("arguments[0].click()", driver.findElement(newUserRadioInput));
        Assert.assertTrue(driver.findElement(newUserRadioInput).isSelected());

        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)");
        By termCheckbox = By.cssSelector("input#id_accept_tos");
        jsExecutor.executeScript("arguments[0].click()", driver.findElement(termCheckbox));
        Assert.assertTrue(driver.findElement(termCheckbox).isSelected());







    }

    @Test
    public void TC_05_Doc() throws InterruptedException {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        Thread.sleep(5000);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,200)");

        By hcmRadio = By.xpath("//div[@aria-label='Hồ Chí Minh']");

        driver.findElement(hcmRadio).click();
        //verify
        Assert.assertEquals(driver.findElement(hcmRadio).getAttribute("aria-checked"),"true");

        //check
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)");
        By noodleCheckbox = By.xpath("//div[@aria-label='Mì Quảng']");
        if(driver.findElement(noodleCheckbox).getAttribute("aria-checked").equals("false")){
            driver.findElement(noodleCheckbox).click();
        }
        Assert.assertEquals(driver.findElement(noodleCheckbox).getAttribute("aria-checked"),"true");

        //uncheck
        if(driver.findElement(noodleCheckbox).getAttribute("aria-checked").equals("true")){
            driver.findElement(noodleCheckbox).click();

        }
        Assert.assertEquals(driver.findElement(noodleCheckbox).getAttribute("aria-checked"),"false");

    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit(); //dong trinh duyet
    }


}