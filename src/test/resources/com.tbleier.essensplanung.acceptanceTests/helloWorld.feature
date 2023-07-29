Feature:  HelloWorldFeature
  This feature says a good freaking hello to all of the world

  Scenario: should say hello to the world
    Given the world exists
    When a hello is said
    Then the world should be happy