package com.financeactive.taptrain.pages;

import com.financeactive.taptrain.TapTrain;
import org.apache.tapestry5.dom.Document;
import org.apache.tapestry5.test.PageTester;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;

/**
 * @author olemerdy
 * @since 24/10/12
 */
public class HiLoTest {

    private PageTester pageTester;

    @BeforeClass
    public void setupPageTester() {
        pageTester = new PageTester(
                TapTrain.class.getPackage().getName(),
                TapTrain.class.getSimpleName().toLowerCase());
    }

    @Test
    public void verifyHiLoGame() {
        Document document = pageTester.renderPage(
                HiLo.class.getSimpleName());
        assertFalse(document.hasDTD());

        pageTester.shutdown();
    }
}
