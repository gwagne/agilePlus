package opensource.pc.gw.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Project {

    @GeneratedValue
    @Id
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @OneToOne()
    @JoinColumn(name="id")
    private User owner;

    @NotNull
    @OneToOne()
    @JoinColumn(name="id")
    private Backlog backlog;

    @NotNull
    @OneToMany()
    @JoinColumn(name="id")
    private List<Release> releases;

    public Project(Backlog backlog, Long id, String name, User owner, List<Release> releases) {
        this.backlog = backlog;
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.releases = releases;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Backlog getBacklog() {
        return backlog;
    }

    public void setBacklog(Backlog backlog) {
        this.backlog = backlog;
    }

    public List<Release> getReleases() {
        return releases;
    }

    public void setReleases(List<Release> releases) {
        this.releases = releases;
    }
}
