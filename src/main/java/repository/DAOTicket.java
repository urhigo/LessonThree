package repository;

import models.Ticket;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class DAOTicket {

    public void addTicket(Ticket ticket) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(ticket);
        transaction.commit();
        session.close();
    }

    public Ticket getTicketById(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Ticket ticketFromDB = session.get(Ticket.class, id);
        session.close();
        return ticketFromDB;
    }

    public List<Ticket> getTicketByUserId(int id) {
        List<Ticket> tickets = new ArrayList<>();
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        tickets = session.createQuery("FROM Ticket WHERE userId.id = :userId", Ticket.class)
                .setParameter("userId", id) .getResultList();
        transaction.commit();
        session.close();
        return tickets;
    }

    public void updateTicket(Ticket ticket) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(ticket);
        transaction.commit();
        session.close();
    }

    public void deleteTicket(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Ticket ticketFromDB = session.get(Ticket.class, id);
        if (ticketFromDB != null) {
            session.detach(ticketFromDB);
            transaction.commit();
        } else {
            System.out.println("Not ticket with this id");
            transaction.rollback();
        }
        session.close();
    }
}
