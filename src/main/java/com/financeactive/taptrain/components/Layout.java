package com.financeactive.taptrain.components;

import com.financeactive.taptrain.services.Section;
import com.financeactive.taptrain.services.Sections;
import com.financeactive.taptrain.services.TapTrainStack;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.PersistentLocale;

import java.util.Locale;

/**
 * Layout component for pages of application taptrain.
 */
@Import(
        stack = TapTrainStack.NAME,
        library = "context:bootstrap/js/bootstrap.js",
        stylesheet = {
                "context:bootstrap/css/bootstrap.css",
                "context:layout/layout.css"
        })
public class Layout {

    /**
     * The page title, for the <title> element and the <h1> element.
     */
    @Property
    @Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
    private String title;

    @Property
    private String pageName;

    @Property
    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    private String sidebarTitle;

    @Property
    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    private Block sidebar;

    @Inject
    private ComponentResources resources;

    @Inject
    private PersistentLocale persistentLocale;

    @Inject
    @Property
    private Sections sections;

    @Property
    private Section section;

    @Property
    @Inject
    @Symbol(SymbolConstants.APPLICATION_VERSION)
    private String appVersion;

    @Property
    @Inject
    @Symbol(SymbolConstants.TAPESTRY_VERSION)
    private String tapestryVersion;

    public String getCurrentPage() {
        return resources.getPageName();
    }

    public String getClassForSections() {
        return section.contains(resources.getPageName()) ? "active" : null;
    }

    @OnEvent(Events.toggleLanguage)
    void onToggleLanguage() {
        persistentLocale.set(
                persistentLocale.isSet() && "fr".equals(persistentLocale.get().getLanguage()) ? Locale.ENGLISH : Locale.FRENCH
        );
    }

    interface Events {
        String toggleLanguage = "toggleLanguage";
    }
}
