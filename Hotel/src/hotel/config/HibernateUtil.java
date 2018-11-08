package hotel.config;

import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private static Session session;

    static {
        try {
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getSession() {
        if (session == null || !session.isConnected()) {
            session = getSessionFactory().openSession();
        }
        return session;
    }

    public static void closeSession() {
        if (session != null && session.isConnected()) {
            session.close();
        }
    }

    public static Transaction getBeginTransaction() {
        return getSession().beginTransaction();
    }
}
