package models;

import Interface.InterfacePrintInformation;

public class User implements InterfacePrintInformation {


    public User(long id, String name) {
    }

    public User(long id, String name, Role role) {
        this.id = id;
        this.name = name;

    }

    private long id;
    private String name;
    private Role role;

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
