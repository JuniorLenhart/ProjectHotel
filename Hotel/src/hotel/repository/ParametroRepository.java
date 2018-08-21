package hotel.repository;

import hotel.config.HibernateUtil;
import hotel.model.Parametro;
import org.hibernate.Query;

public class ParametroRepository {

    public static Parametro read() {
        Query query = HibernateUtil.getSession().createQuery("FROM Parametro");
        return (Parametro) query.uniqueResult();
    }
}
