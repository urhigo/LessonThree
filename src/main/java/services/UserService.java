package services;

import models.Ticket;
import models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import repository.DAOTicket;
import repository.DAOUser;

public class UserService {

    private  final DAOUser daoUser;
    private final DAOTicket daoTicket;
    private final boolean updateAndCreateEnabled;

    public UserService(@Value("${enableTicketCreation}") boolean updateAndCreateEnabled, DAOTicket daoTicket, DAOUser daoUser) {
        this.updateAndCreateEnabled = updateAndCreateEnabled;
        this.daoTicket = daoTicket;
        this.daoUser = daoUser;
    }

    @Transactional
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

    @Transactional
    public void updateUser(User user){
        daoUser.updateUser(user);
    }

    @Transactional
    public void deleteUserInDB(String id){
        daoUser.deleteUser(id);
    }

    @Transactional
    public void updateUserAndAddTicket(User user, Ticket ticket){
        daoUser.updateUser(user);
        ticket.setUserId(user.getId());
        daoTicket.addTicket(ticket);
    }
}
