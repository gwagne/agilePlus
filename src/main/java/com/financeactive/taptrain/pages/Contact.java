package com.financeactive.taptrain.pages;

import com.financeactive.taptrain.entities.User;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.hibernate.Session;

import javax.inject.Inject;
import java.util.List;
import java.util.regex.Pattern;

import static com.financeactive.taptrain.entities.User.Properties.firstName;
import static com.google.common.base.Predicates.contains;
import static com.google.common.collect.FluentIterable.from;
import static org.hibernate.criterion.Projections.distinct;
import static org.hibernate.criterion.Projections.property;

public class Contact {

    @Inject
    Session session;

    @Persist
    @Property
    String name;

    @Property
    List<String> list;

    @OnEvent(EventConstants.ACTIVATE)
    void initList() {
        list = session.createCriteria(User.class)
                .setProjection(distinct(property(firstName)))
                .list();
    }

    @OnEvent(EventConstants.PROVIDE_COMPLETIONS)
    List<String> autoComplete(final String start) {
        return from(list)
                .filter(contains(Pattern.compile('^' + start, Pattern.CASE_INSENSITIVE)))
                .toImmutableList();
    }
}
