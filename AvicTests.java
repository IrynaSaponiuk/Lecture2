import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;


import static org.openqa.selenium.By.xpath;
import static org.testng.Assert.assertEquals;

public class AvicTests {

    private WebDriver driver;

    @BeforeTest
    public void profileSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    }

    @BeforeMethod
    public void testsSetUp() {
        driver = new ChromeDriver();//создаем экзаемпляр хром драйвера
        driver.manage().window().maximize();//открыли браузер на весь экран
        driver.get("https://avic.ua/");//открыли сайт
    }

    @Test(priority = 1)
    public void checkThatUrlDoesNotContainNameOfAnotherCategory() {
        driver.findElement(xpath("//span[@class='sidebar-item']")).click();//каталог товаров
        driver.findElement(xpath("//span[contains(text(),'Ноутбуки и планшеты')]")).click();//Noutbuki i planshety
        driver.findElement(xpath("//div[contains(@class,'brand-box__title')]//a[contains(@href,'https://avic.ua/noutbuki')]")).click();//noutbuki
        Assert.assertFalse(driver.getCurrentUrl().contains("Iphone"));
    }

    @Test(priority = 2)
    public void isSearchBoxDisplayed() {
        driver.findElement(xpath("//span[@class='sidebar-item']")).click();//каталог товаров
        driver.findElement(xpath("//span[contains(text(),'Ноутбуки и планшеты')]")).click();//Noutbuki i planshety
        driver.findElement(xpath("//div[contains(@class,'brand-box__title')]//a[contains(@href,'https://avic.ua/noutbuki')]")).click();//noutbuki
        Assert.assertTrue(driver.findElement(By.xpath("//input[contains(@id,'input_search')]")).isDisplayed());

}

    @Test(priority = 3)
    public void checkTheNumberOfSubcategoriesOnThePage() {
        driver.findElement(xpath("//span[@class='sidebar-item']")).click();//каталог товаров
        driver.findElement(xpath("//span[contains(text(),'Ноутбуки и планшеты')]")).click();
        List<WebElement> elementsList = driver.findElements(xpath("//div[contains(@class,'height brand-box')]"));
        int actualElementsSize = elementsList.size();//узнали количество элементов в листе
        assertEquals(actualElementsSize, 7);

    }


    @AfterMethod
    public void tearDown() {
        driver.close();//закрытие драйвера
    }
}