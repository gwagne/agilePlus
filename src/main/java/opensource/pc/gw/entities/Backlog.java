package opensource.pc.gw.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Backlog {


    @GeneratedValue
    @Id
    private Long id;

    @NotNull
    @OneToOne()
    @JoinColumn(name="id")
    private Project projectId;

    @NotNull
    @OneToMany
    @JoinColumn(name="id")
    private List<Epic> epics;

    @NotNull
    @OneToOne()
    @JoinColumn(name="id")
    private User owner;

    public Backlog(Long id, Project projectId, List<Epic> epics, User owner) {
        this.id = id;
        this.projectId = projectId;
        this.epics = epics;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
