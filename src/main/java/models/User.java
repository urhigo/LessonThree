package models;

import Interface.Printable;
import services.BaseIdGeneratingEntity;

public class User extends BaseIdGeneratingEntity implements Printable {


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
