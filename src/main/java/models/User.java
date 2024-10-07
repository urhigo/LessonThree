package models;

import Interface.InterfacePrintInformation;
import services.AutoCreateID;

public class User extends AutoCreateID implements InterfacePrintInformation {


    private String name;
    private Role role;

    public User() {
    }

    public User(String name, Role role) {
        this.name = name;
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void uniqueAbility() {
        System.out.println("Unique ability\nAdmin: check ticket\nUser: get ticket");
    }

    public void getUserRole(User user) {
        System.out.println(user.getRole());
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
}
