package models;

public class Client extends User{

   private final Role role = Role.USER;

    public Client(String name, Role role) {
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
