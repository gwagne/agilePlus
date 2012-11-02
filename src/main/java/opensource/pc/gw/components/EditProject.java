package opensource.pc.gw.components;


import opensource.pc.gw.entities.Backlog;
import opensource.pc.gw.entities.Project;
import opensource.pc.gw.pages.Index;
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

public class EditProject {

    @Inject
    AlertManager alertManager;

    @Inject
    Messages messages;

    @Inject
    Session session;

    @InjectComponent
    BeanEditForm projectForm;

    @Parameter
    @Property
    Project project;

    @OnEvent(value = EventConstants.VALIDATE, component = "projectForm")
    void onValidate() {

        Criteria criteriaName = session.createCriteria(Project.class)
                .add(Restrictions.eq(Project.Properties.name, project.getName()));
        if (!project.isNew())
            criteriaName.add(Restrictions.ne(Project.Properties.id, project.getId()));

        Number count = (Number) criteriaName.setProjection(Projections.count(Project.Properties.id)).uniqueResult();
        if (count.longValue() > 0)
            projectForm.recordError(String.format("Le projet %s existe déjà",project.getName()));
    }

    @CommitAfter
    @OnEvent(EventConstants.SUCCESS)
    Class onSuccess() {
        if(project.isNew()){
            Backlog backlog = new Backlog();
            project.setBacklog(backlog);
            backlog.setProject(project);
        }
        session.save(project);
        alertManager.success(format("Created/Updated project %d", project.getId()));
        return Index.class;
    }

}