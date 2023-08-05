package com.tbleier.essensplanung.einkaufsliste.acceptanceTests.pages;

import com.tbleier.essensplanung.einkaufsliste.acceptanceTests.WebDriverContext;
import com.tbleier.essensplanung.einkaufsliste.adapter.in.views.kategorie.KategorieListView;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.*;

@Component
public class KategoriePage {

    private final WebDriverContext webDriverContext;

    @Autowired
    public KategoriePage(WebDriverContext webDriverContext) {
        this.webDriverContext = webDriverContext;
    }

    public void goToPage() {
        webDriverContext.openPage("/kategorie");
        webDriverContext.waitForTitle(KategorieListView.title);
    }



    public void deleteCurrentlyOpenedCategory() {
        var deleteButton = webDriverContext.findElement(By.xpath("//vaadin-button[contains(.,'Löschen')]"));
        deleteButton.click();
        waitUntilKategorieFormIsClosed();
    }

    private void waitUntilKategorieFormIsClosed() {
        var categoryForm = webDriverContext.findElement(By.xpath("//vaadin-form-layout"));
        webDriverContext.waitUntilWebElementIsInvisible(categoryForm);
    }

    public void openKategoryFormForCategory(String categoryName) {
        var actualKategorieCell = webDriverContext.findElement(
                By.xpath("//vaadin-grid-cell-content[contains(.,'" + categoryName +"')]"));

        actualKategorieCell.click();
        waitUntilKategorieFormIsOpened();
    }

    public void openKategoryFormForNewCategory() {
        var button = webDriverContext.findElement(By.xpath("//vaadin-button[contains(.,'Kategorie hinzufügen')]"));
        assertEquals("Kategorie hinzufügen", button.getText());

        button.click();
        waitUntilKategorieFormIsOpened();
    }

    public void typeCategoryName(String categoryName) {
        var nameInputField = webDriverContext.findElement(By.id("kategorie-name-input-field"));
        nameInputField.sendKeys(categoryName);
    }

    private void waitUntilKategorieFormIsOpened() {
        var categoryForm = webDriverContext.findElement(By.xpath("//vaadin-form-layout"));
        webDriverContext.waitUntilWebElementIsVisible(categoryForm);
    }

    public void clickSaveButton() {
        var saveButton = webDriverContext.findElement(By.xpath("//vaadin-button[contains(.,'Speichern')]"));
        saveButton.click();
        waitUntilKategorieFormIsClosed();
    }

    public void assertThatCategoryListDoesNotContain(String categoryName) {
        try {
            var actualKategorieCell = findKategorieInKategorieGrid(categoryName);
            assertFalse(actualKategorieCell.isDisplayed());
        }
        catch (NoSuchElementException e) {
            // All is good if it isn't there - Pass
        }
    }

    public void assertThatCategoryListDoesContain(String expectedKategorieName) {
        var actualKategorieCell = findKategorieInKategorieGrid(expectedKategorieName);
        assertTrue(actualKategorieCell.isDisplayed());
    }

    private WebElement findKategorieInKategorieGrid(String categoryName) {
        return webDriverContext.findElement(
                By.xpath("//vaadin-grid-cell-content[contains(.,'" + categoryName + "')]"));
    }

    public void clearCategoryName() {
        var nameInputField = webDriverContext.findElement(By.id("kategorie-name-input-field"));
        webDriverContext.waitUntilWebElementIsVisible(nameInputField);
        nameInputField.sendKeys(Keys.chord(Keys.CONTROL, "a"), "");
    }
}
