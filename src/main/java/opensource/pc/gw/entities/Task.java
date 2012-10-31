package opensource.pc.gw.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

public class Task {

    @GeneratedValue
    @Id
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name="id")
    private Story story;

    @NotNull
    private String description;

    @NotNull
    private int hoursEstimate;

    @NotNull
    @OneToOne()
    @JoinColumn(name="id")
    private User owner;



    public Task(Long id, Story story, String description, int hoursEstimate, User owner) {
        this.id = id;
        this.story = story;
        this.description = description;
        this.hoursEstimate = hoursEstimate;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHoursEstimate() {
        return hoursEstimate;
    }

    public void setHoursEstimate(int hoursEstimate) {
        this.hoursEstimate = hoursEstimate;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
