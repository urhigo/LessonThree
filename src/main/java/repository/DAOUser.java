package repository;

import models.Ticket;
import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class DAOUser {

    public void addUser(User user) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
        session.close();
    }

    public User getUserById(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        User userFromDB = session.get(User.class, id);
        return session.get(User.class, id);
    }


    public void updateUser(User user){
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(user);
        transaction.commit();
    }

    public void deleteUser(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User userFromDB = session.get(User.class, id);
        if (userFromDB != null) {
            session.detach(userFromDB);
            transaction.commit();
        } else {
            System.out.println("Not found user with this id");
            transaction.rollback();
        }
    }

    public void updateUserAndAllTickets(User user, List<Ticket> userTickets){
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(user);
        userTickets.forEach(session::merge);
        transaction.commit();
        session.close();
    }
}
