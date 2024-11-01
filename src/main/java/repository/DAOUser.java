package repository;

import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOUser {

    public void addUser(User user) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
    }

    public User getUserById(String id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        return session.get(User.class, id);
    }


    public void updateUser(User user){
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(user);
        transaction.commit();
    }

    public void deleteUser(String id) {
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
}
