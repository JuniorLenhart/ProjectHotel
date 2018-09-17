package hotel.repository;

import hotel.config.HibernateUtil;
import hotel.model.Locacao;
import java.math.BigInteger;
import java.util.List;
import org.hibernate.Query;

public class LocacaoRepository {

    public static List<Locacao> readAll() {
        Query query = HibernateUtil.getSession().createQuery("FROM Locacao");
        return query.list();
    }

    public static List<Locacao> readBetweenDates(String dateBegin, String dateEnd) {
        Query query = HibernateUtil.getSession().createQuery("FROM Locacao WHERE dtaLocacao BETWEEN '" + dateBegin + "' AND '" + dateEnd + "'");
        return query.list();
    }

    public static Locacao readId(int pCodigo) {
        Query query = HibernateUtil.getSession().createQuery("FROM Locacao WHERE codLocacao = " + pCodigo);
        return (Locacao) query.uniqueResult();
    }

    public static List<Locacao> readSituation(String pSituation) {
        Query query = HibernateUtil.getSession().createQuery("FROM Locacao WHERE indSituacao = :indSituacao");
        query.setParameter("indSituacao", pSituation);
        return query.list();
    }

    public static BigInteger getLastCod() {
        Query query = HibernateUtil.getSession().createSQLQuery("SELECT currval(pg_get_serial_sequence('locacao', 'cod_locacao'))");
        return (BigInteger) query.uniqueResult();
    }
}
