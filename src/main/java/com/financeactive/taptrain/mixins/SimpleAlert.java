package com.financeactive.taptrain.mixins;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectContainer;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.InitializationPriority;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

/**
 * @author olemerdy
 * @since 25/10/12
 */
@Import(library = "simplealert.js")
public class SimpleAlert {

    @Environmental
    JavaScriptSupport javaScriptSupport;

    @InjectContainer
    ClientElement container;

    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    String message;

    @AfterRender
    void installSimpleAlert() {
        JSONObject spec = new JSONObject()
                .put("elementId", container.getClientId())
                .put("message", message);
        javaScriptSupport.addInitializerCall(InitializationPriority.EARLY, JsFunctions.installSimpleAlert, spec);
    }

    interface JsFunctions {
        String installSimpleAlert = "installSimpleAlert";
    }
}