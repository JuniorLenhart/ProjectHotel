package hotel.repository;

import hotel.config.HibernateUtil;
import hotel.model.Quarto;
import java.util.List;
import org.hibernate.Query;

public class QuartoRepository {

    public static List<Quarto> readAll() {
        Query query = HibernateUtil.getSession().createQuery("FROM Quarto");
        return query.list();
    }

    public static List<Quarto> read(String pParam) {
        Query query = HibernateUtil.getSession().createQuery("FROM Quarto WHERE numQuarto ILIKE :numQuarto");
        query.setParameter("numQuarto", "%" + pParam.toLowerCase() + "%");
        return query.list();
    }

    public static Quarto readId(int pCodigo) {
        Query query = HibernateUtil.getSession().createQuery("FROM Quarto WHERE codQuarto = " + pCodigo);
        return (Quarto) query.uniqueResult();
    }
}
