package opensource.pc.gw.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Sprint {

    @GeneratedValue
    @Id
    private Long id;

    @NotNull
    @ManyToOne()
    private Release version;

    @NotNull
    private int sprintNumber;

    @NotNull
    private String description;

    @NotNull
    @OneToOne()
    private User owner;

    @NotNull
    @OneToMany
    private List<Story> stories;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public int getSprintNumber() {
        return sprintNumber;
    }

    public void setSprintNumber(int sprintNumber) {
        this.sprintNumber = sprintNumber;
    }

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    public Release getVersion() {
        return version;
    }

    public void setVersion(Release version) {
        this.version = version;
    }
}
