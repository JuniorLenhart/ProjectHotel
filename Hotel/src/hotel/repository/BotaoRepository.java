package hotel.repository;

import hotel.config.HibernateUtil;
import hotel.model.Botao;
import java.util.List;
import org.hibernate.Query;

public class BotaoRepository {
    
    public static List<Botao> readAll() {
        Query query = HibernateUtil.getSession().createQuery("FROM Botao");
        return query.list();
    }
    
    public static List<Botao> readAllAplicacaoID(int pCodigo) {
        Query query = HibernateUtil.getSession().createQuery("FROM Botao WHERE codAplicacao.codAplicacao = " + pCodigo);
        return query.list();
    }

    public static List<Botao> read(String pParam) {
        Query query = HibernateUtil.getSession().createQuery("FROM Botao WHERE LOWER(nomBotao) LIKE :nomBotao");
        query.setParameter("nomBotao", "%" + pParam.toLowerCase() + "%");
        return query.list();
    }

    public static Botao readId(int pCodigo) {
        Query query = HibernateUtil.getSession().createQuery("FROM Botao WHERE codBotao = " + pCodigo);
        return (Botao) query.uniqueResult();
    }
}
