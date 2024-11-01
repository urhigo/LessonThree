package models;

import Interface.Printable;
import services.BaseIdGeneratingEntity;

import java.time.LocalDateTime;

public class User extends BaseIdGeneratingEntity implements Printable {


    private String name;
    private Role role;
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
