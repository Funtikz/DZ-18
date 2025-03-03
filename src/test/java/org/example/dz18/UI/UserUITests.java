package org.example.dz18.UI;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserUITests {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Example\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testUserListPageLoads() {
        driver.get("http://localhost:8080/users");
        assertEquals("Список пользователей", driver.getTitle());
    }
    @Test
    public void testCreateNewUser() {
        driver.get("http://localhost:8080/users");
        driver.findElement(By.linkText("Добавить пользователя")).click();

        driver.findElement(By.name("firstname")).sendKeys("Тест");
        driver.findElement(By.name("lastname")).sendKeys("Пользователь");
        driver.findElement(By.name("age")).sendKeys("25");
        driver.findElement(By.name("address.street")).sendKeys("Тестовая улица");
        driver.findElement(By.name("address.city")).sendKeys("Москва");
        driver.findElement(By.tagName("button")).click();

        List<WebElement> users = driver.findElements(By.xpath("//td[contains(text(), 'Тест')]"));
        assertFalse(users.isEmpty(), "Новый пользователь должен быть в списке");
    }

}
