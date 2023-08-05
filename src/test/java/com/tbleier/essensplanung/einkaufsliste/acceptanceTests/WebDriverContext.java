package com.tbleier.essensplanung.einkaufsliste.acceptanceTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.stereotype.Component;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

@Component
public class WebDriverContext {

    //How to get a random port at runtime: https://www.baeldung.com/spring-boot-running-port
    @Autowired
    private ServletWebServerApplicationContext webServerAppCtxt;

    public static ChromeDriver driver;

    public static void setupChromeDriver() {
        WebDriverManager.chromedriver().setup();

        var options = new ChromeOptions();
        //Following arguments are needed to run webdriver on a build agent
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        //Following arguments are needed to establish a websocket connection to localhost on a random port
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
    }

    public void openPage(String resource) {
        driver.get("http://localhost:" + webServerAppCtxt.getWebServer().getPort() + resource);
    }

    public void waitForTitle(String title) {
        new WebDriverWait(driver, ofSeconds(30), ofSeconds(1))
                .until(titleIs(title));
    }

    public void waitUntilWebElementIsVisible(WebElement webElement) {
        new WebDriverWait(driver, ofSeconds(30), ofSeconds(1))
                .until(visibilityOf(webElement));
    }

    public void waitUntilWebElementIsInvisible(WebElement webElement) {
        new WebDriverWait(driver, ofSeconds(30), ofSeconds(1))
                .until(invisibilityOf(webElement));
    }

    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    public static void cleanup() {
        driver.quit();
    }
}
