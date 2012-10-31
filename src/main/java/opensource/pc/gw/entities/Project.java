package opensource.pc.gw.entities;


import org.apache.tapestry5.beaneditor.NonVisual;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Project {

    @GeneratedValue
    @Id
    private Long id;

    @NotNull
    private String name;

    //TODO NOTION DE USER EN SESSION ET OWNER
//    @NotNull
//    @OneToOne()
//    private User owner;

    @NotNull
    @OneToOne()
    private Backlog backlog;

    @OneToMany()
    private List<Release> releases;

    @NonVisual
    public boolean isNew(){
        return (id == null);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   // public User getOwner() {
   //     return owner;
   // }

   // public void setOwner(User owner) {
   //     this.owner = owner;
   // }

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

    public interface Properties {
        String name = "name";
        String id = "id";
    }
}
