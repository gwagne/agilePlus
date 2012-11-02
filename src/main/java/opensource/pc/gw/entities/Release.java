package opensource.pc.gw.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "release")
public class Release {

    @GeneratedValue
    @Id
    @Column(name = "release_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;

    @OneToMany(mappedBy = "release")
    private Set<Sprint> sprints;

    @NotNull
    private String name;

    @NotNull
    private String description;

//    @NotNull
//    @OneToOne()
//    private User owner;

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

//    public User getOwner() {
//        return owner;
    //   }

//    public void setOwner(User owner) {
//        this.owner = owner;
//    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}

