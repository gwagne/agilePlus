package opensource.pc.gw.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.InitializationPriority;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

import java.util.UUID;

/**
 * @author olemerdy
 * @since 25/10/12
 */
@Import(library = "randomizer.js")
public class Randomizer {

    @Environmental
    JavaScriptSupport javaScriptSupport;

    @InjectComponent
    ClientElement aLinkToThePast;

    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    String message;

    @Parameter
    @Property
    String value;

    @OnEvent(EventConstants.ACTION)
    void onAction() {
        value = UUID.randomUUID().toString();
    }

    @AfterRender
    void installSimpleAlert() {
        JSONObject spec = new JSONObject()
                .put("elementId", aLinkToThePast.getClientId())
                .put("message", message);
        javaScriptSupport.addInitializerCall(InitializationPriority.EARLY, JsFunctions.installRandomizer, spec);
    }

    interface JsFunctions {
        String installRandomizer = "installRandomizer";
    }
}