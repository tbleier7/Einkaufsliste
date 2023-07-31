Feature:  Kategorie Verwaltung
  Dieses Feature ermöglicht das Erstellen, Löschen und umbennen von Artikel-Kategorien

  Scenario: should create an article category
    Given the user wants to add a new category
    When a category with name "Obst" is saved
    Then "Obst" should be visible in the category list