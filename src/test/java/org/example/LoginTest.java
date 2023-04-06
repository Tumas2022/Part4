package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginTest {
    private WebDriver getChromeDriverWithOptions() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("start-maximized");
        return new ChromeDriver(options);

    }
    private void loginUser(WebDriver driver, String username, String password) {
        WebElement usernameInput = driver.findElement(By.xpath("//*[@id=\"login\"]/div[1]/label/input"));
        WebElement passwordInput = driver.findElement(By.xpath("//*[@id=\"login\"]/div[2]/label/input"));
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"login\"]/div[3]/button/span"));

        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
    }
       @Test
    public void validCredentialsLoginTest() {
        WebDriver driver = getChromeDriverWithOptions();
        driver.get("https://test-stand.gb.ru/login");
        loginUser(driver, "testTumas", "a77388c148");
        String url = driver.getCurrentUrl();
        Assert.assertTrue( url.equals("https://test-stand.gb.ru/login"));
        driver.quit();
    }
    @Test
    public void invalidUsernameLoginTest() {
        WebDriver driver = getChromeDriverWithOptions();
        driver.get("https://test-stand.gb.ru/login");
        loginUser(driver, "test24test", "a77388c148");
        String url = driver.getCurrentUrl();
        Assert.assertTrue( url.equals("https://test-stand.gb.ru/login"));
        driver.quit();
    }
    @Test
    public void invalidPasswordLoginTest() {
        WebDriver driver = getChromeDriverWithOptions();
        driver.get("https://test-stand.gb.ru/login");
        loginUser(driver, "testTumas", "a7738454577868585688");
        String url = driver.getCurrentUrl();
        Assert.assertTrue( url.equals("https://test-stand.gb.ru/login"));
        driver.quit();
   }
}
