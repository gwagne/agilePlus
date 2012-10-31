package opensource.pc.gw.services;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.services.javascript.JavaScriptStack;
import org.apache.tapestry5.services.javascript.StylesheetLink;

import java.util.List;

import static java.util.Collections.emptyList;

/**
 * @author olemerdy
 * @since 25/10/12
 */
public class TapTrainStack implements JavaScriptStack {

    public static final String NAME = "taptrain-stack";

    @Override
    public List<String> getStacks() {
        return emptyList();
    }

    @Override
    public List<Asset> getJavaScriptLibraries() {
        return emptyList();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<StylesheetLink> getStylesheets() {
        return emptyList();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getInitialization() {
        return null;
    }
}
