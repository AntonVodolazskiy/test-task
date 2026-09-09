package e2e.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StorefrontPage {
    private static final Duration TIMEOUT = Duration.ofSeconds(10);
    private static final By COOKIE_ACCEPT_BUTTON = By.id("CookieConsentIOAccept");
    private static final By SEARCH_INPUT = By.xpath("//input[@type='search' or "
            + "contains(translate(@placeholder, 'ZOEKEN', 'zoeken'), 'zoek') or "
            + "contains(translate(@aria-label, 'ZOEKEN', 'zoeken'), 'zoek')]");
    private static final By SEARCH_RESULTS = By.xpath("//a[.//img and "
            + "(contains(@href, 'cadeau') or contains(@href, 'product'))]");
    private static final By DIGITAL_FORMAT = By.cssSelector("label[for='digital']");
    private static final By ADD_TO_CART = By.xpath("//*[self::button or @role='button']"
            + "[contains(normalize-space(), 'winkelmandje') or contains(normalize-space(), 'Toevoegen')]");
    private static final By NAME_INPUT = By.cssSelector("input[name='name']");
    private static final By EMAIL_INPUT = By.cssSelector("input[name='email']");
    private static final By CONFIRM_EMAIL_INPUT = By.cssSelector("input[name='confirmEmail']");
    private static final By CART_BUTTON = By.xpath("//*[normalize-space()='In m’n winkelmandje']");
    private static final By CHECKOUT_BUTTON = By.xpath("//*[normalize-space()='Veilig afrekenen']");
    private static final By GUEST_CHECKOUT_BUTTON = By.xpath("//*[normalize-space()='Bestellen als gast']");
    private static final By FIRST_NAME_INPUT = By.cssSelector("input[name='firstName']");
    private static final By LAST_NAME_INPUT = By.cssSelector("input[name='lastName']");
    private static final By PHONE_INPUT = By.cssSelector("input[name='phoneNumber']");
    private static final By CONTINUE_BUTTON = By.xpath("//*[normalize-space()='Ga verder']");
    private static final By PAYMENT_OPTIONS = By.cssSelector("input[type='radio'], [role='radio']");

    private final WebDriver driver;
    private final WebDriverWait wait;

    public StorefrontPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIMEOUT);
    }

    public void open(String url) {
        driver.get(url);
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"));
        Assertions.assertFalse(String.valueOf(driver.getTitle()).contains("Attention Required"),
                "The storefront blocked the automated browser through Cloudflare.");
    }

    public void acceptCookiesIfPresent() {
        List<WebElement> buttons = driver.findElements(COOKIE_ACCEPT_BUTTON);
        if (!buttons.isEmpty() && buttons.getFirst().isDisplayed()) {
            click(COOKIE_ACCEPT_BUTTON);
        }
    }

    public void searchFor(String query) {
        WebElement input = visible(SEARCH_INPUT);
        input.clear();
        input.sendKeys(query);
    }

    public void assertSuggestionsAreVisible() {
        Assertions.assertFalse(visibleElements(SEARCH_RESULTS).isEmpty(),
                "Expected at least one search suggestion for the entered query.");
    }

    public void selectFirstSearchResult() {
        visibleElements(SEARCH_RESULTS).getFirst().click();
    }

    public void addDigitalProductToCart() {
        click(DIGITAL_FORMAT);
        click(ADD_TO_CART);
    }

    public void completeCartDetails(String name, String email) {
        type(NAME_INPUT, name);
        type(EMAIL_INPUT, email);
        type(CONFIRM_EMAIL_INPUT, email);
        click(CART_BUTTON);
    }

    public void assertCartIsOpen() {
        wait.until(ExpectedConditions.urlContains("winkelmandje"));
        Assertions.assertTrue(String.valueOf(driver.getCurrentUrl()).contains("winkelmandje"),
                "Expected the shopping-cart page to be open.");
    }

    public void continueAsGuest(String firstName, String lastName, String email, String phone) {
        click(CHECKOUT_BUTTON);
        click(GUEST_CHECKOUT_BUTTON);
        type(FIRST_NAME_INPUT, firstName);
        type(LAST_NAME_INPUT, lastName);
        type(EMAIL_INPUT, email);
        type(PHONE_INPUT, phone);
        click(CONTINUE_BUTTON);
    }

    public void assertPaymentOptionsAreVisible() {
        Assertions.assertFalse(visibleElements(PAYMENT_OPTIONS).isEmpty(),
                "Expected payment options to be displayed at checkout.");
    }

    private void type(By locator, String value) {
        WebElement input = visible(locator);
        input.clear();
        input.sendKeys(value);
    }

    private void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    private WebElement visible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private List<WebElement> visibleElements(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
}