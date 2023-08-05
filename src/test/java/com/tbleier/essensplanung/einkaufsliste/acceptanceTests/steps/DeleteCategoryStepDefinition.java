package com.tbleier.essensplanung.einkaufsliste.acceptanceTests.steps;

import com.tbleier.essensplanung.einkaufsliste.acceptanceTests.ScenarioContext;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCategoryStepDefinition extends ScenarioContext {

    @When("category {string} is deleted")
    public void categoryIsDeleted(String categoryName) {

        OpenKategoryFormForCategory(categoryName);
        DeleteCategory();
    }

    private void DeleteCategory() {
        var deleteButton = getWebDriverContext().findElement(By.xpath("//vaadin-button[contains(.,'Löschen')]"));
        deleteButton.click();
        WaitUntilKategorieFormIsClosed();
    }

    private void OpenKategoryFormForCategory(String categoryName) {
        var actualKategorieCell = getWebDriverContext().findElement(
                By.xpath("//vaadin-grid-cell-content[contains(.,'" + categoryName +"')]"));

        actualKategorieCell.click();
        WaitUntilKategorieFormIsOpened();
    }

    private void WaitUntilKategorieFormIsOpened() {
        var categoryForm = getWebDriverContext().findElement(By.xpath("//vaadin-form-layout"));
        getWebDriverContext().waitUntilWebElementIsVisible(categoryForm);
    }

    private void WaitUntilKategorieFormIsClosed() {
        var categoryForm = getWebDriverContext().findElement(By.xpath("//vaadin-form-layout"));
        getWebDriverContext().waitUntilWebElementIsInvisible(categoryForm);
    }
}
