import models.*;
import services.TicketService;

import java.util.List;

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

    }
}
