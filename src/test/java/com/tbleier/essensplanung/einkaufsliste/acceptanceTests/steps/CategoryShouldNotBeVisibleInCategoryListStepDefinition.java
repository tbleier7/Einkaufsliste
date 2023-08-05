package com.tbleier.essensplanung.einkaufsliste.acceptanceTests.steps;

import com.tbleier.essensplanung.einkaufsliste.acceptanceTests.ScenarioContext;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryShouldNotBeVisibleInCategoryListStepDefinition extends ScenarioContext {
    @Then("{string} should not be visible in the category list")
    public void shouldNotBeVisibleInTheCategoryList(String categoryName) {
        var actualKategorieCell = getWebDriverContext().findElement(
                By.xpath("//vaadin-grid-cell-content[contains(.,'" + categoryName +"')]"));

        assertFalse(actualKategorieCell.isDisplayed());
    }
}
