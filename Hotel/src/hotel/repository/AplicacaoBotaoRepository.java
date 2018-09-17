package hotel.repository;

import hotel.config.HibernateUtil;
import hotel.model.AplicacaoBotao;
import java.util.List;
import org.hibernate.Query;

public class AplicacaoBotaoRepository {
    
    public static List<AplicacaoBotao> readAll() {
        Query query = HibernateUtil.getSession().createQuery("FROM AplicacaoBotao");
        return query.list();
    }
    
    public static List<AplicacaoBotao> readAllAplicacaoID(int pCodigo) {
        Query query = HibernateUtil.getSession().createQuery("FROM AplicacaoBotao WHERE codAplicacao.codAplicacao = " + pCodigo);
        return query.list();
    }

    public static List<AplicacaoBotao> read(String pParam) {
        Query query = HibernateUtil.getSession().createQuery("FROM AplicacaoBotao WHERE LOWER(nomBotao) LIKE :nomBotao");
        query.setParameter("nomBotao", "%" + pParam.toLowerCase() + "%");
        return query.list();
    }

    public static AplicacaoBotao readId(int pCodigo) {
        Query query = HibernateUtil.getSession().createQuery("FROM AplicacaoBotao WHERE codAplicacaoBotao = " + pCodigo);
        return (AplicacaoBotao) query.uniqueResult();
    }
}
