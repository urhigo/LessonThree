package models;

public class Client extends User{



    public Client(long id, String name, Role role) {
        super(id, name, role);
    }

    @Override
    public void uniqueAbility(){
        System.out.println("User: get ticket");
    }
}
