package opensource.pc.gw.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Story {

    @GeneratedValue
    @Id
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name="id")
    private Sprint sprint;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private int pointsEstimate;

    @NotNull
    @OneToOne()
    @JoinColumn(name="id")
    private User owner;

    @NotNull
    @OneToMany
    @JoinColumn(name="id")
    private List<Task> tasks;

    public Story(Long id, String name, String description, int pointsEstimate, User owner) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.pointsEstimate = pointsEstimate;
        this.owner = owner;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
