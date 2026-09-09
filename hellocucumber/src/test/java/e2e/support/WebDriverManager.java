package e2e.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public final class WebDriverManager {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private WebDriverManager() {
    }

    public static void startDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--disable-popup-blocking");

        if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
            options.addArguments("--headless=new", "--window-size=1440,1200");
        }

        DRIVER.set(new ChromeDriver(options));
    }

    public static WebDriver getDriver() {
        WebDriver driver = DRIVER.get();
        if (driver == null) {
            throw new IllegalStateException("WebDriver has not been started for this scenario.");
        }
        return driver;
    }

    public static void quitDriver() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }
}