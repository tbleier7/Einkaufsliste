package com.tbleier.essensplanung.einkaufsliste.acceptanceTests.steps;

import com.tbleier.essensplanung.einkaufsliste.acceptanceTests.StepDefinitionContext;
import com.tbleier.essensplanung.einkaufsliste.acceptanceTests.pages.KategoriePage;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

public class CreateNewCategoryStep extends StepDefinitionContext {

    @Autowired
    private KategoriePage kategoriePage;

    @When("a category with name {string} is saved")
    public void aCategoryWithNameIsSaved(String categoryName) {

        kategoriePage.openKategoryFormForNewCategory();
        kategoriePage.typeCategoryName(categoryName);
        kategoriePage.clickSaveButton();
    }
}
