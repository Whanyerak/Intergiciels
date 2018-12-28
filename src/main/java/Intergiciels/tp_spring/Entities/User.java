package Intergiciels.tp_spring.Entities;

import Intergiciels.tp_spring.Security.BCryptManagerUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String name;
    private String first_name;
    private String email;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "enabled")
    private boolean enabled;
    @OneToMany(mappedBy="user")
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

    public String getUsername() {
        return username;
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
        if (!password.isEmpty()) {
            this.password = BCryptManagerUtil.passwordencoder().encode(password);
        }
    }
    public String getPassword() {
        return this.password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
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