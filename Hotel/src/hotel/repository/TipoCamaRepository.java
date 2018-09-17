package hotel.repository;

import hotel.config.HibernateUtil;
import hotel.model.TipoCama;
import java.util.List;
import org.hibernate.Query;

public class TipoCamaRepository {

    public static List<TipoCama> readAll() {
        Query query = HibernateUtil.getSession().createQuery("FROM TipoCama");
        return query.list();
    }

    public static List<TipoCama> readAllAtivos() {
        Query query = HibernateUtil.getSession().createQuery("FROM TipoCama WHERE indSituacao = 'A'");
        return query.list();
    }

    public static List<TipoCama> read(String pParam) {
        Query query = HibernateUtil.getSession().createQuery("FROM TipoCama WHERE LOWER(desTipoCama) LIKE :desTipoCama");
        query.setParameter("desTipoCama", "%" + pParam.toLowerCase() + "%");
        return query.list();
    }

    public static TipoCama readId(int pCodigo) {
        Query query = HibernateUtil.getSession().createQuery("FROM TipoCama WHERE codTipoCama = " + pCodigo);
        return (TipoCama) query.uniqueResult();
    }
}
