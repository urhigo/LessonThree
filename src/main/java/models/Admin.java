package models;

public class Admin extends User{

    public Admin(int id, String name, Role role) {
        super(id, name, role);
    }

    @Override
    public void uniqueAbility(){
        System.out.println("Admin: check ticket");
    }
}
