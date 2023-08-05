package com.tbleier.essensplanung.einkaufsliste.acceptanceTests.steps;

import com.tbleier.essensplanung.einkaufsliste.acceptanceTests.ScenarioContext;
import com.tbleier.essensplanung.einkaufsliste.acceptanceTests.pages.KategoriePage;
import io.cucumber.java.en.Given;

public class ManageCategoriesStep extends ScenarioContext {

    @Given("the user wants to manage categories")
    public void theUserWantsToManageCategories() {
        KategoriePage kategoriePage = new KategoriePage(getWebDriverContext());
        kategoriePage.goToPage();
    }
}
