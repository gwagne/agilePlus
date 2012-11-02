package opensource.pc.gw.entities;


import org.apache.tapestry5.beaneditor.NonVisual;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.CascadeType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Parameter;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "project")
public class Project {

    @GeneratedValue
    @Id
    @Column(name = "project_id")
    private Long id;

    @NotNull
    private String name;

    //TODO NOTION DE USER EN SESSION ET OWNER
//    @NotNull
//    @OneToOne()
//    private User owner;

    @OneToOne(mappedBy = "project", cascade = CascadeType.ALL)
    private Backlog backlog;

    @OneToMany(mappedBy = "project")
    private Set<Release> releases;

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

    public void setBacklog(Backlog projectBacklog) {
        this.backlog = projectBacklog;
    }

//    public List<Release> getReleases() {
//        return releases;
//    }

//    public void setReleases(List<Release> releases) {
//        this.releases = releases;
//    }

    public interface Properties {
        String name = "name";
        String id = "id";
    }
}
