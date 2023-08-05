package com.tbleier.essensplanung.einkaufsliste.acceptanceTests.steps;

import com.tbleier.essensplanung.einkaufsliste.acceptanceTests.ScenarioContext;
import com.tbleier.essensplanung.einkaufsliste.acceptanceTests.pages.KategoriePage;
import io.cucumber.java.en.Then;

public class CategoryShouldBeVisibleInCategoryListStepDefinition extends ScenarioContext {

    @Then("{string} should not be visible in the category list")
    public void shouldNotBeVisibleInTheCategoryList(String categoryName) {
        var kategoriePage = new KategoriePage(getWebDriverContext());
        kategoriePage.assertThatCategoryListDoesNotContain(categoryName);
    }

    @Then("{string} should be visible in the category list")
    public void shouldBeVisibleInTheCategoryList(String expectedKategorieName) {

        var kategoriePage = new KategoriePage(getWebDriverContext());
        kategoriePage.assertThatCategoryListDoesContain(expectedKategorieName);
    }
}
