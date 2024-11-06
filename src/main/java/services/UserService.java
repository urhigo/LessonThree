package services;

import models.Ticket;
import models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.DAOTicket;
import repository.DAOUser;

@Service
@Transactional
public class UserService {

    DAOUser daoUser;
    DAOTicket daoTicket;
    private final boolean updateAndCreateEnabled;


    public UserService(@Value("${enableTicketCreation}") boolean updateAndCreateEnabled, DAOTicket daoTicket, DAOUser daoUser) {
        this.updateAndCreateEnabled = updateAndCreateEnabled;
        this.daoTicket = daoTicket;
        this.daoUser = daoUser;
    }

    public void addUserInDB(User user){
        if(updateAndCreateEnabled) {
            daoUser.addUser(user);
        } else {
            throw new UnsupportedOperationException("Feature update and create ticket is disabled");
        }
    }

    public User getUserById(String id){
        return daoUser.getUserById(id);
    }

    public void updateUser(User user){
        daoUser.updateUser(user);
    }

    public void deleteUserInDB(String id){
        daoUser.deleteUser(id);
    }

    public void updateUserAndAddTicket(User user, Ticket ticket){
        daoUser.updateUser(user);
        ticket.setUserId(user.getId());
        daoTicket.addTicket(ticket);
    }
}
