package Intergiciels.tp_spring.Entities;

import javax.persistence.*;

@Entity
@Table(name="personnages")
public class Personnage {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    private String name;
    private Integer points;

    public Personnage() {}

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
