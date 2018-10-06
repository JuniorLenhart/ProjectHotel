package hotel.repository;

import hotel.config.HibernateUtil;
import hotel.model.Auditoria;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.jdbc.Work;

public class AuditoriaRepository {

    public static Map readAll(int page) {
        Map<List<Auditoria>, Integer> map = new HashMap();
        Query query = HibernateUtil.getSession().createQuery("FROM Auditoria");

        int totalSize = query.list().size();
        query.setFirstResult(25 * page);
        query.setMaxResults(25);
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

    public static void backup(String pDtaInicial, String pDtaFinal) {
        HibernateUtil.getSession().doWork(new Work() {
            public void execute(Connection connection) throws SQLException {
                CallableStatement call = connection.prepareCall("{call backup_audit(?, ?)}");
                call.setString(1, pDtaInicial);
                call.setString(2, pDtaFinal);
                call.execute();
            }
        });
    }
}
