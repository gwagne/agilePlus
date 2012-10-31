package opensource.pc.gw.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Release {

    @GeneratedValue
    @Id
    private Long id;

    @NotNull
    @ManyToOne()
    private Project project;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    @OneToOne()
    private User owner;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
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

