package com.financeactive.taptrain.components;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.ioc.internal.util.InternalUtils;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Session;
import org.got5.tapestry5.jquery.ImportJQueryUI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@ImportJQueryUI({
        "jquery.ui.core",
        "jquery.ui.mouse",
        "jquery.ui.position",
        "jquery.ui.widget",
        "jquery.ui.draggable",
        "jquery.ui.resizable",
        "jquery.effects.explode",
        "jquery.ui.dialog"
})
public class Debug {

    private static final String PATH_SEPARATOR_PROPERTY = "path.separator";

    // Match anything ending in .(something?)path.

    private static final Pattern PATH_RECOGNIZER = Pattern.compile("\\..*path$");

    @Inject
    @Property
    private Request request;

    @Inject
    @Symbol(SymbolConstants.PRODUCTION_MODE)
    @Property(write = false)
    private boolean productionMode;

    @Inject
    @Symbol(SymbolConstants.TAPESTRY_VERSION)
    @Property(write = false)
    private String tapestryVersion;

    @Inject
    @Symbol(SymbolConstants.APPLICATION_VERSION)
    @Property(write = false)
    private String applicationVersion;

    @Property
    private String attributeName;

    @Property
    private Map<String, String> dialogParameters;

    @Property
    private String propertyName;

    @InjectComponent
    private Zone debugDialog;

    @InjectComponent
    private Zone debugZone;

    @SetupRender
    void onSetupRender() {
        dialogParameters = new HashMap<String, String>();
        dialogParameters.put("width", "800");
        dialogParameters.put("opener", "openDebug");
    }

    public Zone onActionFromUpdateDebugZone() {
        return debugZone;
    }

    private final String pathSeparator = System.getProperty(PATH_SEPARATOR_PROPERTY);


    public boolean getHasSession() {
        return request.getSession(false) != null;
    }

    public Session getSession() {
        return request.getSession(false);
    }

    public Object getAttributeValue() {
        return getSession().getAttribute(attributeName);
    }

    public boolean getDevelopmentMode() {
        return !productionMode;
    }

    /**
     * Returns a <em>sorted</em> list of system property names.
     */
    public List<String> getSystemProperties() {
        return InternalUtils.sortedKeys(System.getProperties());
    }

    public String getPropertyValue() {
        return System.getProperty(propertyName);
    }

    public boolean isComplexProperty() {
        return PATH_RECOGNIZER.matcher(propertyName).find() && getPropertyValue().contains(pathSeparator);
    }

    public String[] getComplexPropertyValue() {
        // Neither : nor ; is a regexp character.

        return getPropertyValue().split(pathSeparator);
    }
}