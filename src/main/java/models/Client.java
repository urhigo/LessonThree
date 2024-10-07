package models;

public class Client extends User{

   private final Role role = Role.USER;

    public Client(String name, Role role) {
        super(name, role);
    }

    @Override
    public void uniqueAbility(){
        System.out.println("User: get ticket");
    }
}
