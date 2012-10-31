package opensource.pc.gw.pages;

import opensource.pc.gw.entities.Project;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.hibernate.HibernateGridDataSource;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.hibernate.Session;

public class Index
{
    @Property
    @Inject
    @Symbol(SymbolConstants.TAPESTRY_VERSION)
    private String tapestryVersion;

    @Inject
    AlertManager alertManager;

    @Inject
    AjaxResponseRenderer renderer;

    @Inject
    Session session;

    @InjectComponent
    Zone projectFormZone;

    @Property
    Project project;

    public GridDataSource getProjects() {
        return new HibernateGridDataSource(session, Project.class);
    }

    @OnEvent(value = EventConstants.ACTION, component = "createProject")
    void onActionFromNew() {
        this.project = new Project();
        renderer.addRender(Zones.projectFormZone, projectFormZone);
    }

    interface Zones{
        String projectFormZone = "projectFormZone";
        String projectsGridZone = "projectGridZone";
        String indexLinks = "indexLinks";

    }
}
