package com.dam.tfg.MotoMammiApplicationASG.tasks;

import com.dam.tfg.MotoMammiApplicationASG.Models.Provider;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateMetodos {
    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static List<Provider> activeProviders() {
        List<Provider> providers = List.of();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            Query<Provider> query = session.createQuery("FROM Provider WHERE swiAct = true", Provider.class);
            providers = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.toString());
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }

        return providers;
    }
}
