package com.tbleier.essensplanung.einkaufsliste.acceptanceTests;

import io.cucumber.spring.CucumberContextConfiguration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ScenarioContext {

    @LocalServerPort
    protected int randomServerPort;

    protected static ChromeDriver driver;

    public ScenarioContext() {
        WebDriverManager.chromedriver().setup();

        //This is needed to establisch a websocket connection to localhost on a random port
        var options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
    }



}
