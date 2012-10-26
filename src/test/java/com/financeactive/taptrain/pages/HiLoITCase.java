package com.financeactive.taptrain.pages;

import org.apache.tapestry5.test.SeleniumTestCase;
import org.testng.annotations.Test;

/**
 * @author olemerdy
 * @since 24/10/12
 */
public class HiLoITCase extends SeleniumTestCase {

    @Test
    public void verifyHiLoGame() {
        open("/hilo"); //you must understand = do the magic
        // It's a kind of magic
        // I want it all
        // I want it aaaaaaaaall
        // And I want it nooooooow
        clickAndWait(("link=Guess 1"));
        clickAndWait(("link=Guess 2"));
        clickAndWait(("link=Guess 3"));
        clickAndWait(("link=Guess 4"));
        clickAndWait(("link=Guess 5"));
        clickAndWait(("link=Guess 6"));
        clickAndWait(("link=Guess 7"));
        clickAndWait(("link=Guess 8"));
        clickAndWait(("link=Guess 9"));
        clickAndWait(("link=Guess 10"));
        clickAndWait("link=reset");

    }
}
