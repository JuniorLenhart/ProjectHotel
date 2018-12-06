package hotel.repository;

import hotel.config.HibernateUtil;
import hotel.model.Novidade;
import java.util.List;
import org.hibernate.Query;

public class NovidadeRepository {

    public static List<Novidade> readAll() {
        Query query = HibernateUtil.getSession().createQuery("FROM Novidade");
        return query.list();
    }

    public static Novidade readId(int pCodigo) {
        Query query = HibernateUtil.getSession().createQuery("FROM Novidade WHERE codNovidade = " + pCodigo);
        return (Novidade) query.uniqueResult();
    }

    public static Novidade getLastNew() {
        Query query = HibernateUtil.getSession().createQuery("FROM Novidade ORDER BY dtaNovidade DESC");
        query.setMaxResults(1);
        return (Novidade) query.uniqueResult();
    }
}
