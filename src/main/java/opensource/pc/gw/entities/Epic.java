package opensource.pc.gw.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "epic")
public class Epic {

    @GeneratedValue
    @Id
    @Column(name = "epic_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="project_id")
    private Backlog backlog;

    @NotNull
    private String name;

    @NotNull
    private String description;

//    @NotNull
//    @OneToOne()
//    private User owner;

    public Long getId() {
        return id;
    }

    public Backlog getBacklog() {
        return backlog;
    }

    public void setBacklog(Backlog backlog) {
        this.backlog = backlog;
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

//    public User getOwner() {
//        return owner;
//    }

//    public void setOwner(User owner) {
//        this.owner = owner;
//    }
}
