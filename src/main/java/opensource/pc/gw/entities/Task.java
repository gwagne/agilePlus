package opensource.pc.gw.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="task")
public class Task {

    @GeneratedValue
    @Id
    @Column(name="task_id")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "story_id")
    private Story story;

    @NotNull
    private String description;

    @NotNull
    private int hoursEstimate;

//    @NotNull
//    @OneToOne()
//    private User owner;


    public Long getId() {
        return id;
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

//    public User getOwner() {
//        return owner;
//    }

//    public void setOwner(User owner) {
//        this.owner = owner;
//    }
}
