package com.tbleier.essensplanung.einkaufsliste.acceptanceTests.steps;

import com.tbleier.essensplanung.einkaufsliste.acceptanceTests.ScenarioContext;
import com.tbleier.essensplanung.einkaufsliste.acceptanceTests.pages.KategoriePage;
import io.cucumber.java.en.When;

public class CreateNewCategoryStep extends ScenarioContext {
    @When("a category with name {string} is saved")
    public void aCategoryWithNameIsSaved(String categoryName) {
        var kategoriePage = new KategoriePage(getWebDriverContext());

        kategoriePage.openKategoryFormForNewCategory();
        kategoriePage.typeCategoryName(categoryName);
        kategoriePage.clickSaveButton();
    }
}
