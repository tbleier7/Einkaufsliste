package com.tbleier.essensplanung.einkaufsliste.acceptanceTests;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("com.tbleier.essensplanung.einkaufsliste.acceptanceTests")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.tbleier.essensplanung.einkaufsliste.acceptanceTests")
public class EssensplanungAcceptanceIT {

    @BeforeAll
    public static void setup() {
        WebDriverContext.setupChromeDriver();
    }

    @AfterAll
    public static void cleanup() {
        WebDriverContext.cleanup();
    }
}
