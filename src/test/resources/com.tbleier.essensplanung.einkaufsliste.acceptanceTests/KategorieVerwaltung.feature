Feature:  Kategorie Verwaltung
  Dieses Feature ermöglicht das Erstellen, Löschen und Umbenennen von Artikel-Kategorien

  Background:
    Given the user wants to manage categories

  Scenario: should create an article category
    When a category with name "Obst" is saved
    Then "Obst" should be visible in the category list

  Scenario: should delete an article category
    Given a category with name "Gemüse" is saved
    When category "Gemüse" is deleted
    Then "Gemüse" should not be visible in the category list

  Scenario: should rename a article category
    Given a category with name "Fisch" is saved
    When category "Fisch" is renamed to "Fleisch"
    Then "Fleisch" should be visible in the category list
    And "Fisch" should not be visible in the category list