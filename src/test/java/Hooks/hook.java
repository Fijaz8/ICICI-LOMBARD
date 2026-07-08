package Hooks;

import base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class hook extends BaseTest {

    @Before
    public void setup() {

        initializeDriver();

    }

    @After
    public void tearDown() {

    }
}