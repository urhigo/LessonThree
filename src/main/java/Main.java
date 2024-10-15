import models.*;


public class Main {

    public static void main(String[] args) {


        User user1 = new User();
        System.out.println(user1.getId());
        System.out.println(user1);
        Admin user2 = new Admin("Peta", Role.ADMIN);
        System.out.println(user2);
        User user3 = new User();
        System.out.println(user3);
        Client user4 = new Client("Vita", Role.USER);
        System.out.println(user4);

        Ticket ticket1 = new Ticket("club", (short) 182, Sector.B, true);
        Ticket ticket2 = new Ticket("train", (short) 345, Sector.A, false);

        PhoneNumber phoneNumber1 = new PhoneNumber("+375291111111");
        PhoneNumber phoneNumber2 = new PhoneNumber("+375292222222");

        Email email = new Email("qqq@qwert.com");

        System.out.println("-----------------------------");
        ticket1.shared(phoneNumber1.getPhoneNumber());
        System.out.println("-----------------------------");
        ticket2.shared(phoneNumber2.getPhoneNumber(), email.getEmail());


    }
}
