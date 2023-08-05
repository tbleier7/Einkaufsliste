package com.tbleier.essensplanung.einkaufsliste.acceptanceTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class WebDriverContext {

    private static WebDriverContext instance;

    public static WebDriverContext getInstanceForPort(int serverPort) {
        if(instance == null)
            instance = new WebDriverContext(serverPort);

        return instance;
    }

    private final int serverPort;

    public ChromeDriver driver;

    private WebDriverContext(int serverPort) {
        this.serverPort = serverPort;
        WebDriverManager.chromedriver().setup();

        var options = new ChromeOptions();
        options.addArguments("--headless");
        //Following argurments are needed to establish a websocket connection to localhost on a random port
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
    }

    public void openPage(String resource) {
        driver.get("http://localhost:" + this.serverPort + resource);
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

    public static void quit() {
        instance.driver.quit();
    }
}
