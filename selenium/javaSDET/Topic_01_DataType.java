package javaSDET;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Topic_01_DataType {
    //2 nhom kieu du lieu

    //Cach khai bao:
    //Access Modifier: Pham vi truy cap (private/ public/ protected/ default)
    //1- Access Modifier - Kieu du lieu - Ten bien - Gia tri cua bien (ngoai/trong ham deu duoc)
    public char cName = 'b';

    //2.1 - Access Modifier - KDL - Ten bien
    private char cAddress;

    //2.2 - Ten bien - Gia tri gan sau(ham)
    public void clickToElement(){
        char cAddress = 'c';
        char cCity = 'c';
    }




    //nhom 1: kieu du lieu nguyen thuy
    //char: ki tu
    //khi gan gia tri (khoi tao) thi nam trong dau nhay don: ''

    //byte/ short/ int/ long: kieu so nguyen
    //khi gan gia tri (khoi tao) thi ko nam trong dau gi
    int iNumber = 35000;

    //float/ double: kieu so thuc
    //khi gan gia tri (khoi tao) thi ko nam trong dau gi
    float fNumber=15.7f;
    double dNumber=15.7;

    //boolean: logic
    //khi gan gia tri (khoi tao) thi ko nam trong dau gi
    boolean gender = false;

    //nhom 2: kieu du lieu tham chieu
    //String: chuoi
    //khi gan gia tri (khoi tao) thi  nam trong dau ""

    String fullName = "John Smith";

    //class
    FirefoxDriver fDriver = new FirefoxDriver();
    Actions actions = new Actions(fDriver);
    Topic_01_DataType topic01 =new Topic_01_DataType();

    //interface
    WebDriver driver;
    JavascriptExecutor js;
    //array
    String[] studentName={"Hien", "Nam", "An"};
    int[] studentPhone = {939493439, 3434, 3434 };
    //list/set
    List<String> studentAddress = new ArrayList<String>();
    //map

    //object
    Object name ="";

    Object phone =19;

    Object isDisplayed=true;





    //Convention: Quy uoc khi lap trinh
    //Teb bien/ ten ham: viet duoi dang camel case
    //chu cai dau tien luon viet thuong
    //name/ address/ city/ phone/ zipCode
    //clickToElement/ getUserName/ getPhoneNumber/ selectItemDropdown
}
