package models;

public class Admin extends User{

    private final Role role = Role.ADMIN;

    public Admin(String name, Role role) {
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
