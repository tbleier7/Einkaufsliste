package com.tbleier.essensplanung.einkaufsliste.acceptanceTests;

import io.cucumber.java.AfterAll;
import io.cucumber.spring.CucumberContextConfiguration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ScenarioContext {

    @LocalServerPort
    private int randomServerPort;

    public WebDriverContext getWebDriverContext() {
        return WebDriverContext.getInstanceForPort(randomServerPort);
    }
}
