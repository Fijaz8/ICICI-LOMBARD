package Hooks;

import base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class hook extends BaseTest {

    @Before
    public void setup() {

        initializeDriver();

        launchApplication(
            "https://www.icicilombard.com"
        );
    }

    @After
    public void tearDown() {

        quitBrowser();
    }
}