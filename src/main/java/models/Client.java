package models;

public class Client extends User{

   private final Role role = Role.USER;

    public Client(long id, String name) {
        super(id, name);
    }

    @Override
    public void uniqueAbility(){
        System.out.println("User: get ticket");
    }
}
