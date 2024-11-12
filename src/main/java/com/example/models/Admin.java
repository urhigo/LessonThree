package com.example.models;

import com.example.models.User;

public class Admin extends User {

    private final models.Role role = models.Role.ADMIN;

    public Admin(String name, models.Role role) {
        super(name, role);
    }


    public void checkTicket(){
        System.out.println("Admin: check ticket");
    }


    @Override
    public void getUserRole() {
        System.out.println("Role user: " + role);
    }
}
