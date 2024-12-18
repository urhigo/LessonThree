package com.example.models;

import com.example.Interface.Printable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "_user")
@NoArgsConstructor
@AllArgsConstructor
public class User implements Printable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;
    @Transient
    private models.Role role;
    @Column(name = "creation_date")
    private LocalDateTime timeCreationUser = LocalDateTime.now();



    public User(String name, models.Role role) {
        this.name = name;
        this.role = role;
    }

    public models.Role getRole() {
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

    public void setRole(models.Role role) {
        this.role = role;
    }

    public void setTimeCreationUser(LocalDateTime timeCreationUser) {
        this.timeCreationUser = timeCreationUser;
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
