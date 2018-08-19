package hotel.repository;

import hotel.config.HibernateUtil;
import hotel.model.FormaPagamento;
import java.util.List;
import org.hibernate.Query;

public class FormaPagamentoRepository {

    public static List<FormaPagamento> readAll() {
        Query query = HibernateUtil.getSession().createQuery("FROM FormaPagamento");
        return query.list();
    }

    public static List<FormaPagamento> read(String pParam) {
        Query query = HibernateUtil.getSession().createQuery("FROM FormaPagamento WHERE LOWER(desFormaPgto) ILIKE :desFormaPgto");
        query.setParameter("desFormaPgto", "%" + pParam.toLowerCase() + "%");
        return query.list();
    }

    public static FormaPagamento readId(int pCodigo) {
        Query query = HibernateUtil.getSession().createQuery("FROM FormaPagamento WHERE codFormaPgto = " + pCodigo);
        return (FormaPagamento) query.uniqueResult();
    }
}
