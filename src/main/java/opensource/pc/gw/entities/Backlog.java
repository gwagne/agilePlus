package opensource.pc.gw.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.Parameter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "backlog")
public class Backlog {


    @Id
    @Column(name = "project_id", unique = true, nullable = false)
    @GeneratedValue(generator = "gen")
    @GenericGenerator(name = "gen", strategy = "foreign", parameters =
    @Parameter(name = "property", value = "project"))
    private Long projectId;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Project project;

    @OneToMany(mappedBy = "backlog")
    private Set<Epic> epics;

    //TODO ACTIVER USER CONTEXT
    //  @NotNull
    //  @OneToOne()
    //  private User owner;


    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

//    public List<Epic> getEpics() {
//        return epics;
//    }

//    public void setEpics(List<Epic> epics) {
//        this.epics = epics;
//    }

//    public User getOwner() {
//        return owner;
//    }

//    public void setOwner(User owner) {
//        this.owner = owner;
//    }
}
