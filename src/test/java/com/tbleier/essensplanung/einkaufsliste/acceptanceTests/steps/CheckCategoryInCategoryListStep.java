package com.tbleier.essensplanung.einkaufsliste.acceptanceTests.steps;

import com.tbleier.essensplanung.einkaufsliste.acceptanceTests.StepDefinitionContext;
import com.tbleier.essensplanung.einkaufsliste.acceptanceTests.pages.KategoriePage;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

public class CheckCategoryInCategoryListStep extends StepDefinitionContext {

    @Autowired
    private KategoriePage kategoriePage;

    @Then("{string} should not be visible in the category list")
    public void shouldNotBeVisibleInTheCategoryList(String categoryName) {
        kategoriePage.assertThatCategoryListDoesNotContain(categoryName);
    }

    @Then("{string} should be visible in the category list")
    public void shouldBeVisibleInTheCategoryList(String expectedKategorieName) {
        kategoriePage.assertThatCategoryListDoesContain(expectedKategorieName);
    }
}
