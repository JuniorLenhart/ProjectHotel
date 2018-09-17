package hotel.repository;

import hotel.config.HibernateUtil;
import hotel.model.Auditoria;
import java.util.List;
import org.hibernate.Query;

public class AuditoriaRepository {

    public static List<Auditoria> readAll() {
        Query query = HibernateUtil.getSession().createQuery("FROM Auditoria");
        return query.list();
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
