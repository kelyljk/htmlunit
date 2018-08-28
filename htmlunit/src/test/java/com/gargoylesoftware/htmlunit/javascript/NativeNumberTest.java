/*
 * Copyright (c) 2002-2017 Gargoyle Software Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gargoylesoftware.htmlunit.javascript;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.gargoylesoftware.htmlunit.BrowserRunner;
import com.gargoylesoftware.htmlunit.BrowserRunner.Alerts;
import com.gargoylesoftware.htmlunit.WebDriverTestCase;

/**
 * Number is a native JavaScript object.
 *
 * @author Marc Guillemot
 * @author Ahmed Ashour
 * @author Ronald Brill
 */
@RunWith(BrowserRunner.class)
public class NativeNumberTest extends WebDriverTestCase {

    /**
     * Test for the methods with the same expectations for all browsers.
     * @throws Exception if the test fails
     */
    @Test
    @Alerts({"toFixed: function", "toExponential: function", "toLocaleString: function", "toPrecision: function",
        "toString: function", "valueOf: function"})
    public void methods_common() throws Exception {
        final String[] methods = {"toFixed", "toExponential", "toLocaleString", "toPrecision", "toString", "valueOf"};
        final String html = NativeDateTest.createHTMLTestMethods("new Number()", methods);
        loadPageWithAlerts2(html);
    }

    /**
     * Test for the methods with the different expectations depending on the browsers.
     * @throws Exception if the test fails
     */
    @Test
    @Alerts(DEFAULT = "toSource: undefined",
            FF = "toSource: function")
    public void methods_different() throws Exception {
        final String html = NativeDateTest.createHTMLTestMethods("new Number()", "toSource");
        loadPageWithAlerts2(html);
    }

    /**
     * Test for Rhino bug 538172.
     * @throws Exception if the test fails
     */
    @Test
    @Alerts("2.274341322658976e-309")
    public void toStringRhinoBug538172() throws Exception {
        final String html = "<html><head><title>foo</title><script>\n"
            + "alert(2.274341322658976E-309);\n"
            + "</script></head><body>\n"
            + "</body></html>";
        loadPageWithAlerts2(html);
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Alerts("12,345")
    public void toLocaleString() throws Exception {
        final String html = "<html><head><script>\n"
            + "  alert((12345).toLocaleString('en'));\n"
            + "</script></head><body>\n"
            + "</body></html>";
        loadPageWithAlerts2(html);
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Alerts("12.345")
    public void toLocaleStringDe() throws Exception {
        final String html = "<html><head><script>\n"
            + "  alert((12345).toLocaleString('de'));\n"
            + "</script></head><body>\n"
            + "</body></html>";
        loadPageWithAlerts2(html);
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Alerts("12,345")
    public void toLocaleStringNoParam() throws Exception {
        final String html = "<html><head><script>\n"
            + "  try {\n"
            + "    alert((12345).toLocaleString());\n"
            + "  } catch(e) { alert(e); }\n"
            + "</script></head><body>\n"
            + "</body></html>";
        loadPageWithAlerts2(html);
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Alerts("exception")
    public void toLocaleStringHintertupfingen() throws Exception {
        final String html = "<html><head><script>\n"
            + "  try {\n"
            + "    alert((12345).toLocaleString('Hintertupfingen'));\n"
            + "  } catch(e) { alert('exception'); }\n"
            + "</script></head><body>\n"
            + "</body></html>";
        loadPageWithAlerts2(html);
    }
}