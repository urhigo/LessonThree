package com.example.models;

public class Client extends User{

   private final models.Role role = models.Role.USER;

    public Client(String name, models.Role role) {
        super(name, role);
    }

    public void getTicket(){
        System.out.println("User: get ticket");
    }

    @Override
    public void getUserRole() {
        System.out.println("Role user: " + role);
    }
}
