package com.tbleier.essensplanung.einkaufsliste.acceptanceTests.steps;

import com.tbleier.essensplanung.einkaufsliste.acceptanceTests.AcceptanceITContext;
import io.cucumber.java.AfterAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;


public class SaveCategoryStep extends AcceptanceITContext {

    @Given("the user wants to add a new category")
    public void theUserWantsToAddANewCategory() {
            NavigateToKategorienPage();
    }

    private void NavigateToKategorienPage() {
        driver.get("http://localhost:" + this.randomServerPort + "/kategorie");
        new WebDriverWait(driver, ofSeconds(30), ofSeconds(1))
                .until(titleIs("Kategorien"));
    }

    @When("a category with name {string} is saved")
    public void aCategoryWithNameIsSaved(String categoryName) {
        OpenKategoryForm();
        TypeInNewKategory(categoryName);
        SaveNewCategory();
    }

    private void OpenKategoryForm() {
        var button = driver.findElement(By.xpath("//vaadin-button[contains(.,'Kategorie hinzufügen')]"));
        assertEquals("Kategorie hinzufügen", button.getText());

        button.click();
        WaitUntilKategorieFormIsOpened();
    }

    private void WaitUntilKategorieFormIsOpened() {
        new WebDriverWait(driver, ofSeconds(30), ofSeconds(1))
                .until(visibilityOf(driver.findElement((By.xpath("//vaadin-form-layout")))));
    }

    private void TypeInNewKategory(String categoryName) {
        var nameInputField = driver.findElement(By.id("kategorie-name-input-field"));
        nameInputField.sendKeys(categoryName);
    }

    private void SaveNewCategory() {
        var saveButton = driver.findElement(By.xpath("//vaadin-button[contains(.,'Speichern')]"));
        saveButton.click();
        WaitUntilKategorieFormIsClosed();
    }

    private void WaitUntilKategorieFormIsClosed() {
        new WebDriverWait(driver, ofSeconds(30), ofSeconds(1))
                .until(invisibilityOf(driver.findElement((By.xpath("//vaadin-form-layout")))));
    }

    @Then("{string} should be visible in the category list")
    public void shouldBeVisibleInTheCategoryList(String expectedKategorieName) {

        var actualKategorieCell = driver.findElement(
                By.xpath("//vaadin-grid-cell-content[contains(.,'" + expectedKategorieName +"')]"));

        assertEquals(expectedKategorieName, actualKategorieCell.getText());

        driver.quit();
    }
}
