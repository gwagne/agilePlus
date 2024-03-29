package opensource.pc.gw.pages.users;

import opensource.pc.gw.entities.User;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import static java.lang.String.format;



public class CreateUsers {

    @Inject
    AlertManager alertManager;

    @Inject
    Session session;

    @Property
    User user;

    @CommitAfter
    @OnEvent(EventConstants.SUCCESS)
    Class onSuccess() {
        session.save(user);
        alertManager.info(format("Created user %s %s", user.getFirstName(), user.getLastName()));
        return IndexUsers.class;
    }
}