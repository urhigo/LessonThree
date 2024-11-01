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
    }

    public Ticket getTicketById(String id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        return session.get(Ticket.class, id);
    }

    public List<Ticket> getTicketByUserId(String id) {
        List<Ticket> tickets = new ArrayList<>();
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        tickets = session.createQuery("FROM Ticket WHERE user.id = :userId", Ticket.class).setParameter("userId", id)
                .getResultList();
        transaction.commit();
        return tickets;
    }

    public void updateTicket(Ticket ticket) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(ticket);
        transaction.commit();
    }

    public void deleteTicket(String id) {
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
    }
}
