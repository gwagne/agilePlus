package opensource.pc.gw.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="sprint")
public class Sprint {

    @GeneratedValue
    @Id
    @Column(name = "sprint_id")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "release_id")
    private Release release;

    @NotNull
    private int sprintNumber;

    @NotNull
    private String description;

//    @NotNull
//    @OneToOne()
//    private User owner;

    @NotNull
    @OneToMany(mappedBy = "sprint")
    private Set<Story> stories;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

//    public User getOwner() {
//        return owner;
//    }

//    public void setOwner(User owner) {
    //       this.owner = owner;
//    }

    public int getSprintNumber() {
        return sprintNumber;
    }

    public void setSprintNumber(int sprintNumber) {
        this.sprintNumber = sprintNumber;
    }

    public Set<Story> getStories() {
        return stories;
    }

    public void setStories(Set<Story> stories) {
        this.stories = stories;
    }

    public Release getRelease() {
        return release;
    }

    public void setRelease(Release release) {
        this.release = release;
    }
}
