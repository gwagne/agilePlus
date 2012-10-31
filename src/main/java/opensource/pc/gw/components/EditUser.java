package opensource.pc.gw.components;

import opensource.pc.gw.entities.User;
import opensource.pc.gw.pages.users.IndexUsers;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import static java.lang.String.format;

/**
 * @author olemerdy
 * @since 25/10/12
 */
public class EditUser {

    @Inject
    AlertManager alertManager;

    @Inject
    Messages messages;

    @Inject
    Session session;

    @InjectComponent
    BeanEditForm userForm;

    @Parameter
    @Property
    User user;

    @OnEvent(value = EventConstants.VALIDATE, component = "userForm")
    void onValidate() {

        Criteria criteriaMail = session.createCriteria(User.class)
                .add(Restrictions.eq(User.Properties.email, user.getEmail()));
        Criteria criteriaLogin = session.createCriteria(User.class)
                .add(Restrictions.eq(User.Properties.login, user.getLogin()));
        if (!user.isNew())
            criteriaMail.add(Restrictions.ne(User.Properties.id, user.getId()));
        if (!user.isNew())
            criteriaLogin.add(Restrictions.ne(User.Properties.id, user.getId()));

        Number count = (Number) criteriaMail.setProjection(Projections.count(User.Properties.id)).uniqueResult();
        if (count.longValue() > 0)
            userForm.recordError(messages.format("already-existing.email", user.getEmail()));
        count = (Number) criteriaLogin.setProjection(Projections.count(User.Properties.id)).uniqueResult();
        if (count.longValue() > 0)
            userForm.recordError(messages.format("already-existing.login", user.getLogin()));
    }

    @CommitAfter
    @OnEvent(EventConstants.SUCCESS)
    Class onSuccess() {
        session.save(user);
        alertManager.success(format("Created/Updated user %d", user.getId()));
        return IndexUsers.class;
    }
}
