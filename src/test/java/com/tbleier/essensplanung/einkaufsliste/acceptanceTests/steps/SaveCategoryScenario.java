package com.tbleier.essensplanung.einkaufsliste.acceptanceTests.steps;

import com.tbleier.essensplanung.einkaufsliste.acceptanceTests.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SaveCategoryScenario extends ScenarioContext {

    @When("a category with name {string} is saved")
    public void aCategoryWithNameIsSaved(String categoryName) {
        OpenKategoryForm();
        TypeInNewKategory(categoryName);
        SaveNewCategory();
    }

    private void OpenKategoryForm() {
        var button = getWebDriverContext().findElement(By.xpath("//vaadin-button[contains(.,'Kategorie hinzufügen')]"));
        assertEquals("Kategorie hinzufügen", button.getText());

        button.click();
        WaitUntilKategorieFormIsOpened();
    }

    private void WaitUntilKategorieFormIsOpened() {
        var categoryForm = getWebDriverContext().findElement(By.xpath("//vaadin-form-layout"));
        getWebDriverContext().waitUntilWebElementIsVisible(categoryForm);
    }

    private void TypeInNewKategory(String categoryName) {
        var nameInputField = getWebDriverContext().findElement(By.id("kategorie-name-input-field"));
        nameInputField.sendKeys(categoryName);
    }

    private void SaveNewCategory() {
        var saveButton = getWebDriverContext().findElement(By.xpath("//vaadin-button[contains(.,'Speichern')]"));
        saveButton.click();
        WaitUntilKategorieFormIsClosed();
    }

    private void WaitUntilKategorieFormIsClosed() {
        var categoryForm = getWebDriverContext().findElement(By.xpath("//vaadin-form-layout"));
        getWebDriverContext().waitUntilWebElementIsInvisible(categoryForm);
    }

    @Then("{string} should be visible in the category list")
    public void shouldBeVisibleInTheCategoryList(String expectedKategorieName) {

        var actualKategorieCell = getWebDriverContext().findElement(
                By.xpath("//vaadin-grid-cell-content[contains(.,'" + expectedKategorieName +"')]"));
        assertEquals(expectedKategorieName, actualKategorieCell.getText());
    }
}
