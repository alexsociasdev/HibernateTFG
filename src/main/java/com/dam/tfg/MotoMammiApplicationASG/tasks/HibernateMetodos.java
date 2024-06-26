package com.dam.tfg.MotoMammiApplicationASG.tasks;

import com.dam.tfg.MotoMammiApplicationASG.Models.ProviderDTO;
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
            System.err.println("Creación fallida: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static List<ProviderDTO> activeProviders() {
        List<ProviderDTO> providers = List.of();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            Query<ProviderDTO> query = session.createQuery("FROM Provider WHERE swiAct = true", ProviderDTO.class);
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
