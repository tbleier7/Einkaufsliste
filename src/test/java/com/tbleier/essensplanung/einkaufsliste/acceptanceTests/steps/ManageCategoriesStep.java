package com.tbleier.essensplanung.einkaufsliste.acceptanceTests.steps;

import com.tbleier.essensplanung.einkaufsliste.acceptanceTests.ScenarioContext;
import com.tbleier.essensplanung.einkaufsliste.adapter.in.views.kategorie.KategorieListView;
import io.cucumber.java.en.Given;

public class ManageCategoriesStep extends ScenarioContext {

    @Given("the user wants to manage categories")
    public void theUserWantsToManageCategories() {
        NavigateToKategorienPage();
    }

    private void NavigateToKategorienPage() {
        getWebDriverContext().openPage("/kategorie");
        getWebDriverContext().waitForTitle(KategorieListView.title);
    }
}
