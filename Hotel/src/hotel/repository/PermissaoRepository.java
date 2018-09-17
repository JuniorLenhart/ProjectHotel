package hotel.repository;

import hotel.config.HibernateUtil;
import hotel.model.Permissao;
import java.util.List;
import org.hibernate.Query;

public class PermissaoRepository {
    
    public static List<Permissao> readAll() {
        Query query = HibernateUtil.getSession().createQuery("FROM Permissao");
        return query.list();
    }

    public static List<Permissao> read(String pParam) {
        Query query = HibernateUtil.getSession().createQuery("FROM Permissao WHERE LOWER(codUsuario.desLogin) LIKE :desLogin");
        query.setParameter("desLogin", "%" + pParam.toLowerCase() + "%");
        return query.list();
    }

    public static Permissao readId(int pCodigo) {
        Query query = HibernateUtil.getSession().createQuery("FROM Permissao WHERE codPermissao = " + pCodigo);
        return (Permissao) query.uniqueResult();
    }
    
    public static List<Permissao> readAllByTela(int pCodigoTela) {
        Query query = HibernateUtil.getSession().createQuery("FROM Permissao WHERE codAplicacaoBotao.codAplicacao.codAplicacao = " + pCodigoTela);
        return query.list();
    }
}
