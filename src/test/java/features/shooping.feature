Feature: Place the order of Products

  Background: 
    Given Launch browser


  Scenario: Remove lowest price item
    Given I add "4" random items to my cart
    When I view my cart
    Then I found total "4" items listed in my cart
    When I search for lowest price item
    And I am able to remove lowest price item from my cart
    Then I am able to verify "3" items in my cart