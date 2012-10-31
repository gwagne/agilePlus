package opensource.pc.gw.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Epic {

    @GeneratedValue
    @Id
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    @OneToOne()
    private User owner;

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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
