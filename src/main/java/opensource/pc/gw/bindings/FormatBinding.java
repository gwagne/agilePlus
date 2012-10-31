package opensource.pc.gw.bindings;

import org.apache.tapestry5.Binding;
import org.apache.tapestry5.internal.bindings.AbstractBinding;
import org.apache.tapestry5.ioc.Location;
import org.apache.tapestry5.ioc.Messages;

import java.util.ArrayList;
import java.util.List;

/**
 * @author olemerdy
 * @since 24/10/12
 */
public class FormatBinding extends AbstractBinding {

    private final Messages messages;

    private final boolean invariant;

    private final List<Binding> keyBindings;

    private final List<Binding> valueBindings;

    public FormatBinding(Location location, Messages messages, boolean invariant, List<Binding> keyBindings, List<Binding> valueBindings) {
        super(location);

        this.messages = messages;
        this.invariant = invariant;
        this.keyBindings = keyBindings;
        this.valueBindings = valueBindings;
    }

    public FormatBinding(Location location, Messages messages, boolean invariant, List<Binding> keyBindings) {
        super(location);

        this.messages = messages;
        this.invariant = invariant;
        this.keyBindings = keyBindings;
        this.valueBindings = null;
    }

    @Override
    public Object get() {
        String key = "";
        for (Binding keyBinding : keyBindings) {
            key += keyBinding.get();
        }

        if (null == valueBindings) return messages.get(key);

        List<Object> values = new ArrayList<Object>(valueBindings.size());
        for (Binding valueBinding : valueBindings) {
            values.add(valueBinding.get());
        }

        return messages.format(key, values.toArray());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class getBindingType() {
        return String.class;
    }

    @Override
    public boolean isInvariant() {
        return this.invariant;
    }
}
