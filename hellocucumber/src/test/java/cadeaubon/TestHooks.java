package cadeaubon;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class TestHooks {
    @Before
    public void startBrowser() {
        DriverManager.startDriver();
    }

    @After
    public void closeBrowser() {
        DriverManager.quitDriver();
    }
}