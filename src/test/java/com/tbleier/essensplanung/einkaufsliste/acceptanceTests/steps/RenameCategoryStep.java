package com.tbleier.essensplanung.einkaufsliste.acceptanceTests.steps;

import com.tbleier.essensplanung.einkaufsliste.acceptanceTests.StepDefinitionContext;
import com.tbleier.essensplanung.einkaufsliste.acceptanceTests.pages.KategoriePage;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

public class RenameCategoryStep extends StepDefinitionContext {

    @Autowired
    private KategoriePage kategoriePage;

    @When("category {string} is renamed to {string}")
    public void categoryIsRenamedTo(String oldCategoryName, String newCategoryName) {
        kategoriePage.openKategoryFormForCategory(oldCategoryName);
        kategoriePage.clearCategoryName();
        kategoriePage.typeCategoryName(newCategoryName);
        kategoriePage.clickSaveButton();
    }
}
