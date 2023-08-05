package com.tbleier.essensplanung.einkaufsliste.acceptanceTests.steps;

import com.tbleier.essensplanung.einkaufsliste.acceptanceTests.StepDefinitionContext;
import com.tbleier.essensplanung.einkaufsliste.acceptanceTests.pages.KategoriePage;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

public class DeleteCategoryStep extends StepDefinitionContext {

    @Autowired
    private KategoriePage kategoriePage;

    @When("category {string} is deleted")
    public void categoryIsDeleted(String categoryName) {
        kategoriePage.openKategoryFormForCategory(categoryName);
        kategoriePage.deleteCurrentlyOpenedCategory();
    }
}
