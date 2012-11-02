package opensource.pc.gw.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "story")
public class Story {

    @GeneratedValue
    @Id
    @Column(name = "story_id")
    private Long id;

    @NotNull
    @ManyToOne()
    @JoinColumn(name="sprint_id")
    private Sprint sprint;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private int pointsEstimate;

//    @NotNull
//    @OneToOne
    //   private User owner;

    @NotNull
    @OneToMany(mappedBy = "story")
    private Set<Task> tasks;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPointsEstimate() {
        return pointsEstimate;
    }

    public void setPointsEstimate(int pointsEstimate) {
        this.pointsEstimate = pointsEstimate;
    }

//    public User getOwner() {
//        return owner;
//    }

//    public void setOwner(User owner) {
//        this.owner = owner;
//    }
}
