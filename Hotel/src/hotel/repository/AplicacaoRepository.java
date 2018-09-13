package hotel.repository;

import hotel.model.Aplicacao;
import hotel.config.HibernateUtil;
import java.util.List;
import org.hibernate.Query;

public class AplicacaoRepository {
    
    public static List<Aplicacao> readAll() {
        Query query = HibernateUtil.getSession().createQuery("FROM Aplicacao");
        return query.list();
    }
    
    public static List<Aplicacao> readAllAtivos() {
        Query query = HibernateUtil.getSession().createQuery("FROM Aplicacao WHERE indSituacao = 'A'");
        return query.list();
    }

    public static List<Aplicacao> read(String pParam) {
        Query query = HibernateUtil.getSession().createQuery("FROM Aplicacao WHERE LOWER(nomAplicacao) LIKE :nomAplicacao");
        query.setParameter("nomAplicacao", "%" + pParam.toLowerCase() + "%");
        return query.list();
    }

    public static Aplicacao readId(int pCodigo) {
        Query query = HibernateUtil.getSession().createQuery("FROM Aplicacao WHERE codAplicacao = " + pCodigo);
        return (Aplicacao) query.uniqueResult();
    }
}
