package testng;

import org.testng.Assert;

public class Topic_01_Assertion {
    public static void main(String[] args) {
        // 3 ham chinh de kiem tra tinh dung dan cua du lieu
        boolean gender=3<5;

        // kiem tra du lieu no phai DUNG
        Assert.assertTrue(gender);
        // kiem tra du lieu no phai SAI
        Assert.assertFalse(3>5);

        // kiem tra du lieu no bang mong doi (Actual - expected)
        // Kieu du lieu giong nhau
        // Gia tri cua du lieu bang  nhau
        Assert.assertEquals(5,6);
        Assert.assertEquals("Name","Name");


    }
}
