package com.financeactive.taptrain.services;

import org.apache.tapestry5.services.ComponentClassResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * @author olemerdy
 * @since 26/10/12
 */
public class Section {

    final ComponentClassResolver componentClassResolver;

    final List<String> pages = new ArrayList<String>();

    final String name;

    final Class index;


    public Section(ComponentClassResolver componentClassResolver, String name, Class... pages) {
        assert pages.length > 0;

        this.componentClassResolver = componentClassResolver;
        this.name = name;
        this.index = pages[0];

        for (Class page : pages) {
            this.pages.add(componentClassResolver.resolvePageClassNameToPageName(page.getName()));
        }
    }

    public String getIndex() {
        return componentClassResolver.resolvePageClassNameToPageName(index.getName());
    }

    public String getName() {
        return name;
    }

    public boolean contains(String page) {
        return pages.contains(page);
    }
}
