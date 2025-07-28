package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_12_Custom_Dropdown {

    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void initialBrowser(){
        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));


    }

    @Test
    public void TC_01_JQuerry() throws InterruptedException {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        selectItemCustomDropdown("span#speed-button","ul#speed-menu>li>div","Faster");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Faster");

    }
    @Test
    public void TC_02_UI() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectItemCustomDropdown("div.dropdown","div.item>span","Jenny Hess");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Jenny Hess");

    }
    @Test
    public void TC_03_Vujs() throws InterruptedException {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        selectItemCustomDropdown("li.dropdown-toggle","ul.dropdown-menu>li>a","Third Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");

    }

    @Test
    public void TC_04_Editable() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        selectItemCustomDropdown(" input.search","div.item>span","Albania");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Albania");

    }

    private void selectItemCustomDropdown(String parentCss, String childCSS, String textItem) throws InterruptedException {
        // Hanh vi de thao tac len dropdown
        // 1 - cho cho dropdown co the thao tac len duoc clickable
        // 2 - click vao element nao de no xo ra cac dropdown ra
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(parentCss))).click();
        Thread.sleep(2000);
        // implicit: wait cho viec tim element- ap dung cho 2 ham findElement, findElements
        // WebDriverWait: explicit wait: cho element vs 1 dieu kien ro rang (xuat hien trong HTML ko care hien thi hay khong, clickable, selected)
        // FluentWait: co the sua thoi gian polling lai
        //Thread.sleep
        // 3- cho cho tat ca item dc load ra presence
        List<WebElement> allItems= explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCSS))); //ul#speed-menu>li>div
        // 4 - tim cai item nao dung voi mong doi
        // 5 items
        for (WebElement item : allItems) {
            if (item.getText().equals(textItem)) {
                // 5 - click len item do
                item.click();
                break;
            }
        }

        //wait
        //if
        //for
        //List
        //Break


    }

    private void enterItemCustomDropdown(String parentCss, String childCSS, String textItem) throws InterruptedException {
        // Hanh vi de thao tac len dropdown
        // 1 - cho cho dropdown co the nhap dc
        // 2 - SendKEy vao element nao de no xo ra cac dropdown ra
        WebElement dropdownTextbox=explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(parentCss)));
        dropdownTextbox.clear();
        dropdownTextbox.sendKeys(textItem);
        Thread.sleep(2000);
        // implicit: wait cho viec tim element- ap dung cho 2 ham findElement, findElements
        // WebDriverWait: explicit wait: cho element vs 1 dieu kien ro rang (xuat hien trong HTML ko care hien thi hay khong, clickable, selected)
        // FluentWait: co the sua thoi gian polling lai
        //Thread.sleep
        // 3- cho cho tat ca item dc load ra presence
        List<WebElement> allItems= explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCSS))); //ul#speed-menu>li>div
        // 4 - tim cai item nao dung voi mong doi
        // 5 items
        for (WebElement item : allItems) {
            if (item.getText().equals(textItem)) {
                // 5 - click len item do
                item.click();
                break;
            }
        }

        //wait
        //if
        //for
        //List
        //Break


    }

    //3 - Clean: Delete data test/ account/ close browser...
    @AfterClass
    public void cleanBrowser(){
        driver.quit(); //dong trinh duyet
    }


}