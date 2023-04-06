package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class BlogTest {
    private WebDriver getChromeDriverWithOptions() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("start-maximized");
        options.setPageLoadTimeout(Duration.ofSeconds(20));
        return new ChromeDriver(options);
    }
    private void loginUser(WebDriver driver, String username, String password) {
        WebElement usernameInput = driver.findElement( By.xpath( "//*[@id=\"login\"]/div[1]/label/input" ) );
        WebElement passwordInput = driver.findElement( By.xpath( "//*[@id=\"login\"]/div[2]/label/input" ) );
        WebElement loginButton = driver.findElement( By.xpath( "//*[@id=\"login\"]/div[3]/button/span" ) );

        usernameInput.sendKeys( username );
        passwordInput.sendKeys( password );
        loginButton.click();
    }
    @Test
    public void homePageTest() {
        WebDriver driver = getChromeDriverWithOptions();
        driver.get("https://test-stand.gb.ru/login");
        loginUser(driver, "testTumas", "a77388c148");
        String url = driver.getCurrentUrl();
        Assert.assertTrue( url.equals("https://test-stand.gb.ru/login"));
        driver.quit();
    }
    @Test
    public void pageSourceTest() {
        WebDriver driver = getChromeDriverWithOptions();
        driver.get("https://test-stand.gb.ru/login");
        loginUser(driver, "testTumas", "a77388c148");
        driver.getPageSource().contains( "content" );
        driver.quit();
    }
    @Test
    public void refreshTest() {
        WebDriver driver = getChromeDriverWithOptions();
        driver.get("https://test-stand.gb.ru/login");
        loginUser(driver, "testTumas", "a77388c148");
        driver.navigate().refresh();
        driver.quit();
    }
  

}



