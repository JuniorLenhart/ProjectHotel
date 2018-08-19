package hotel.repository;

import hotel.config.HibernateUtil;
import hotel.model.Consumivel;
import java.util.List;
import org.hibernate.Query;

public class ConsumivelRepository {

    public static List<Consumivel> readAll() {
        Query query = HibernateUtil.getSession().createQuery("FROM Consumivel");
        return query.list();
    }

    public static List<Consumivel> read(String pParam) {
        Query query = HibernateUtil.getSession().createQuery("FROM Consumivel WHERE LOWER(nomConsumivel) ILIKE :nomConsumivel");
        query.setParameter("nomConsumivel", "%" + pParam.toLowerCase() + "%");
        return query.list();
    }

    public static Consumivel readId(int pCodigo) {
        Query query = HibernateUtil.getSession().createQuery("FROM Consumivel WHERE codConsumivel = " + pCodigo);
        return (Consumivel) query.uniqueResult();
    }
}
