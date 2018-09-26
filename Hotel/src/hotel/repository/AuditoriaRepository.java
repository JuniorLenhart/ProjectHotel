package hotel.repository;

import hotel.config.HibernateUtil;
import hotel.model.Auditoria;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.hibernate.Query;

public class AuditoriaRepository {

    private static Map<List<Auditoria>, Integer> map = new HashMap();

    public static Map readAll(int page) {
        map = new HashMap();
        Query query = HibernateUtil.getSession().createQuery("FROM Auditoria");
        int totalSize = query.list().size();
        query.setFirstResult(50 * page);
        query.setMaxResults(50);
        map.put(query.list(), totalSize);
        return map;
    }

    public static List<Auditoria> read(String pParam) {
        Query query = HibernateUtil.getSession().createQuery("FROM Auditoria WHERE LOWER(desAuditoria) LIKE :descricao");
        query.setParameter("descricao", "%" + pParam.toLowerCase() + "%");
        return query.list();
    }

    public static Auditoria readId(int pCodigo) {
        Query query = HibernateUtil.getSession().createQuery("FROM Auditoria WHERE codAuditoria = " + pCodigo);
        return (Auditoria) query.uniqueResult();
    }

    public static List<Auditoria> readLogin(String pParam) {
        Query query = HibernateUtil.getSession().createQuery("FROM Auditoria WHERE LOWER(usuario.desLogin) = '" + pParam.toLowerCase() + "'");
        return query.list();
    }

    public static List<Auditoria> readPorTipos(String pParam) {
        Query query = HibernateUtil.getSession().createQuery("FROM Auditoria WHERE tipAuditoria = " + pParam);
        return query.list();
    }
}
