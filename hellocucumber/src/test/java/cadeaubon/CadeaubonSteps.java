package cadeaubon;

import cadeaubon.pages.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CadeaubonSteps {
    private static final String BASE_URL = "https://www.cadeaubon.nl/";
    private HomePage homePage;

    @Given("I am on the cadeaubon.nl website")
    public void openCadeaubonWebsite() {
        homePage = new HomePage(DriverManager.getDriver());
        homePage.open(BASE_URL);
        homePage.acceptCookiesIfPresent();
    }

    @When("I start typing {string} in the search bar")
    public void startTypingInSearchBar(String query) {
        homePage.searchFor(query);
    }

    @Then("I should see product suggestions in the result field")
    public void verifyProductSuggestions() {
        homePage.assertSuggestionsAreVisible();
    }

    @And("I select the first product from the search results")
    public void selectFirstProduct() {
        homePage.selectFirstSearchResult();
    }

    @Then("I add the selected product to the cart")
    public void addSelectedProductToCart() {
        homePage.addDigitalProductToCart();
        homePage.completeCartDetails("Test Customer", "test.automation@example.com");
        homePage.assertCartIsOpen();
    }

    @And("I proceed to the checkout")
    public void proceedToCheckout() {
        homePage.continueAsGuest("Test", "Customer", "test.automation@example.com", "0612345678");
    }

    @Then("I enter my credit card data")
    public void verifyPaymentStep() {
        homePage.assertPaymentOptionsAreVisible();
    }
}