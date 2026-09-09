package e2e.steps;

import e2e.pages.StorefrontPage;
import e2e.support.WebDriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ShoppingFlowSteps {
    private static final String BASE_URL = "https://www.cadeaubon.nl/";
    private StorefrontPage storefrontPage;

    @Given("I am on the cadeaubon.nl website")
    public void openStorefront() {
        storefrontPage = new StorefrontPage(WebDriverManager.getDriver());
        storefrontPage.open(BASE_URL);
        storefrontPage.acceptCookiesIfPresent();
    }

    @When("I start typing {string} in the search bar")
    public void startTypingInSearchBar(String query) {
        storefrontPage.searchFor(query);
    }

    @Then("I should see product suggestions in the result field")
    public void verifyProductSuggestions() {
        storefrontPage.assertSuggestionsAreVisible();
    }

    @And("I select the first product from the search results")
    public void selectFirstProduct() {
        storefrontPage.selectFirstSearchResult();
    }

    @Then("I add the selected product to the cart")
    public void addSelectedProductToCart() {
        storefrontPage.addDigitalProductToCart();
        storefrontPage.completeCartDetails("Test Customer", "test.automation@example.com");
        storefrontPage.assertCartIsOpen();
    }

    @And("I proceed to the checkout")
    public void proceedToCheckout() {
        storefrontPage.continueAsGuest("Test", "Customer", "test.automation@example.com", "0612345678");
    }

    @Then("I enter my credit card data")
    public void verifyPaymentStep() {
        storefrontPage.assertPaymentOptionsAreVisible();
    }
}