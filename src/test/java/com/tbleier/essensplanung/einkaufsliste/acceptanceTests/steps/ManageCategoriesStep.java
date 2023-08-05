package com.tbleier.essensplanung.einkaufsliste.acceptanceTests.steps;

import com.tbleier.essensplanung.einkaufsliste.acceptanceTests.StepDefinitionContext;
import com.tbleier.essensplanung.einkaufsliste.acceptanceTests.pages.KategoriePage;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

public class ManageCategoriesStep extends StepDefinitionContext {

    @Autowired
    private KategoriePage kategoriePage;

    @Given("the user wants to manage categories")
    public void theUserWantsToManageCategories() {
        kategoriePage.goToPage();
    }
}
