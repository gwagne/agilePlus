package com.financeactive.taptrain.pages.users;

import com.financeactive.taptrain.entities.Honorific;
import com.financeactive.taptrain.entities.User;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.hibernate.HibernateGridDataSource;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.hibernate.Session;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

import static java.lang.String.format;

/**
 * @author olemerdy
 * @since 24/10/12
 */
public class IndexUsers {

    @Inject
    AlertManager alertManager;

    @Inject
    AjaxResponseRenderer renderer;

    @Inject
    Session session;

    @InjectComponent
    Zone userFormZone;

    @InjectComponent
    Zone usersGridZone;

    @PageActivationContext
    @Property
    User user;

    public GridDataSource getUsers() {
        return new HibernateGridDataSource(session, User.class);
    }

    @CommitAfter
    @OnEvent(value = EventConstants.ACTION, component = "delete")
    void onActionFromDelete(User user) {
        session.delete(user);
        alertManager.success(format("Deleted user %d", user.getId()));
        renderer.addRender(Zones.usersGrid, usersGridZone);
    }

    @CommitAfter
    @OnEvent(value = EventConstants.ACTION, component = "edit")
    void onActionFromEdit(User user) {
        this.user = user;
        renderer.addRender(Zones.userForm, userFormZone);
    }

    @CommitAfter
    @OnEvent(value = EventConstants.ACTION, component = "new")
    void onActionFromNew() {
        this.user = new User();
        renderer.addRender(Zones.userForm, userFormZone);
    }

    @CommitAfter
    @OnEvent(value = EventConstants.ACTION, component = "randomUser")
    void createRandomUser() {
        Random random = new Random();
        User user = new User();
        user.setBirthDate(new Date(random.nextLong()));
        user.setEmail(UUID.randomUUID() + "@nasa.gov.us");
        user.setFirstName(UUID.randomUUID().toString());
        user.setHonorific(random.nextBoolean() ? Honorific.MR : Honorific.MISS);
        user.setLastName(UUID.randomUUID().toString());
        session.save(user);
        alertManager.success(format("Created random user %d", user.getId()));
        renderer.addRender(Zones.usersGrid, usersGridZone);
    }

    interface Zones {

        String userForm = "userFormZone";

        String usersGrid = "usersGridZone";
    }
}