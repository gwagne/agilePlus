package opensource.pc.gw.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Backlog {


    @GeneratedValue
    @Id
    private Long id;

    @NotNull
    @OneToOne()
    private Project projectId;

    @OneToMany
    private List<Epic> epics;

    //TODO ACTIVER USER CONTEXT
  //  @NotNull
  //  @OneToOne()
  //  private User owner;

    public Long getId() {
        return id;
    }

    public Project getProjectId() {
        return projectId;
    }

    public void setProjectId(Project projectId) {
        this.projectId = projectId;
    }

    public List<Epic> getEpics() {
        return epics;
    }

    public void setEpics(List<Epic> epics) {
        this.epics = epics;
    }

//    public User getOwner() {
//        return owner;
//    }

//    public void setOwner(User owner) {
//        this.owner = owner;
//    }
}
