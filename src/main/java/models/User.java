package models;

import Interface.Printable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import services.BaseIdGeneratingEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "_user")
public class User extends BaseIdGeneratingEntity implements Printable {


    @Column(name = "name")
    private String name;
    @Transient
    private Role role;
    @Column(name = "creation_date")
    private final LocalDateTime timeCreationUser = LocalDateTime.now();

    public User() {
    }

    public User(String name, Role role) {
        this.name = name;
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getTimeCreationUser() {
        return timeCreationUser;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public void printInformationAboutObject(Object object) {
        System.out.println(object);
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role=" + role +
                '}';
    }


    public void getUserRole() {
        System.out.println("All user have role: Admin, Client"
        + "\nThis user: " + role);
    }
}
