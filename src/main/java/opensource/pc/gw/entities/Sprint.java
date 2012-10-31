package opensource.pc.gw.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Sprint {


    @GeneratedValue
    @Id
    private Long id;

    @NotNull
    @ManyToOne()
    @JoinColumn(name="id")
    private Release version;

    @NotNull
    private int sprintNumber;

    @NotNull
    private String description;

    @NotNull
    @OneToOne()
    @JoinColumn(name="id")
    private User owner;

    @NotNull
    @OneToMany
    @JoinColumn(name="id")
    private List<Story> stories;

    public Sprint(String description, Long id, User owner, int sprintNumber, List<Story> stories, Release version) {
        this.description = description;
        this.id = id;
        this.owner = owner;
        this.sprintNumber = sprintNumber;
        this.stories = stories;
        this.version = version;
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
