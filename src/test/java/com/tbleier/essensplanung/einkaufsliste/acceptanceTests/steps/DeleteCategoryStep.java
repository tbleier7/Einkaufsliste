package com.tbleier.essensplanung.einkaufsliste.acceptanceTests.steps;

import com.tbleier.essensplanung.einkaufsliste.acceptanceTests.ScenarioContext;
import com.tbleier.essensplanung.einkaufsliste.acceptanceTests.pages.KategoriePage;
import io.cucumber.java.en.When;

public class DeleteCategoryStep extends ScenarioContext {

    @When("category {string} is deleted")
    public void categoryIsDeleted(String categoryName) {

        var kategoriePage = new KategoriePage(getWebDriverContext());
        kategoriePage.openKategoryFormForCategory(categoryName);
        kategoriePage.deleteCurrentlyOpenedCategory();
    }
}
