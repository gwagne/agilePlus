package opensource.pc.gw.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

public class Release {

    @GeneratedValue
    @Id
    private Long id;

    @NotNull
    @ManyToOne()
    @JoinColumn(name="id")
    private Project project;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    @OneToOne()
    @JoinColumn(name="id")
    private User owner;

    public Release(String description, Long id, String name, User owner, Project project) {
        this.description = description;
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.project = project;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}

