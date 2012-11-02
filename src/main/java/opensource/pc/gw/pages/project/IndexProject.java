package opensource.pc.gw.pages.project;

import opensource.pc.gw.entities.Project;
import org.apache.log4j.Logger;
import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.slf4j.LoggerFactory;

public class IndexProject {

    private Logger log = Logger.getLogger(IndexProject.class);

    @Property
    Project project;

    @Inject
    Session session;

    void onActivate(Long projectId){
         project = (Project)session.get(Project.class,projectId);
    }

    Long onPassivate(){
        return project.getId();
    }


}