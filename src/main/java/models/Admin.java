package models;

public class Admin extends User{

    private final Role role = Role.ADMIN;

    public Admin(String name, Role role) {
        super(name, role);
    }

    @Override
    public void uniqueAbility(){
        System.out.println("Admin: check ticket");
    }
}
