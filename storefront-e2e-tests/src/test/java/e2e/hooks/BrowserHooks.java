package e2e.hooks;

import e2e.support.WebDriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class BrowserHooks {
    @Before
    public void startBrowser() {
        WebDriverManager.startDriver();
    }

    @After
    public void closeBrowser() {
        WebDriverManager.quitDriver();
    }
}