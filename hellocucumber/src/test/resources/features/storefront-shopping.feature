Feature: Storefront shopping flow

  Scenario: Check Autocomplete Functionality
    Given I am on the cadeaubon.nl website
    When I start typing "product" in the search bar
    Then I should see product suggestions in the result field

  Scenario: Add Product to Cart
    Given I am on the cadeaubon.nl website
    When I start typing "product" in the search bar
    And I select the first product from the search results
    Then I add the selected product to the cart

  Scenario: Proceed to Checkout and Enter Credit Card Data
    Given I am on the cadeaubon.nl website
    When I start typing "product" in the search bar
    And I select the first product from the search results
    And I add the selected product to the cart
    And I proceed to the checkout
    Then I enter my credit card data