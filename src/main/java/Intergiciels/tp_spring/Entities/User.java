package Intergiciels.tp_spring.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String name;
    private String first_name;
    private String email;
    @Column(name = "username", unique = true, nullable = false, length = 45)
    private String username;
    @Column(name = "password", nullable = false, length = 60)
    private String password;
    @Column(name = "enabled", nullable = false)
    private boolean enabled;
    @OneToMany
    private List<Personnage> personnages;


    public User() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Personnage> getPersonnages() {
        return personnages;
    }

    public void setPersonnages() {
        this.personnages = new ArrayList<>();
    }

    public void addPersonnages(Personnage pe) {
        this.personnages.add(pe);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return null;
    }

    public String getUsername() {
        return username;
    }

    //End of Spring Security methods

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return String.format("User: %s %s %s", id, name, email);
    }
}