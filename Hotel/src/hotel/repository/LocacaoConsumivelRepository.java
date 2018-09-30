package hotel.repository;

import hotel.config.HibernateUtil;
import hotel.model.LocacaoConsumivel;
import java.util.List;
import org.hibernate.Query;

public class LocacaoConsumivelRepository {
    
    public static List<LocacaoConsumivel> readAll() {
        Query query = HibernateUtil.getSession().createQuery("FROM LocacaoConsumivel");
        return query.list();
    }
    
    public static LocacaoConsumivel readId(int pCodigo) {
        Query query = HibernateUtil.getSession().createQuery("FROM LocacaoConsumivel WHERE codLocacaoConsumivel = "+pCodigo);
        return (LocacaoConsumivel) query.uniqueResult();
    }

    public static List<LocacaoConsumivel> readLocacaoId(int pCodigo) {
        Query query = HibernateUtil.getSession().createQuery("FROM LocacaoConsumivel WHERE locacao.codLocacao = " + pCodigo);
        return query.list();
    }
}
