package opensource.pc.gw.services;

import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.UsesOrderedConfiguration;

import java.util.Iterator;
import java.util.List;

@UsesOrderedConfiguration(Section.class)
public class Sections implements Iterable<Section> {

    @Inject
    Messages messages;

    final List<Section> sections;

    public Sections(List<Section> sections) {
        this.sections = sections;
    }

    @Override
    public Iterator<Section> iterator() {
        return sections.iterator();
    }
}
