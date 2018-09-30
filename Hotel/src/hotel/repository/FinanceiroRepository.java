package hotel.repository;

import hotel.config.HibernateUtil;
import hotel.model.Financeiro;
import java.util.List;
import org.hibernate.Query;

public class FinanceiroRepository {

    public static List<Financeiro> readAll() {
        Query query = HibernateUtil.getSession().createQuery("FROM Financeiro");
        return query.list();
    }

    public static List<Financeiro> readAllMonthYear(int month, int year) {
        Query query = HibernateUtil.getSession().createQuery("FROM Financeiro WHERE MONTH(dtaPgto) = " + month + " AND YEAR(dtaPgto) = " + year);
        return query.list();
    }
    
    public static List<Financeiro> readAllDayMonthYear(int day, int month, int year) {
        Query query = HibernateUtil.getSession().createQuery("FROM Financeiro WHERE DAY(dtaPgto) = " + day + " AND MONTH(dtaPgto) = " + month + " AND YEAR(dtaPgto) = " + year);
        return query.list();
    }

    public static List<Financeiro> read(String pParam) {
        Query query = HibernateUtil.getSession().createQuery("FROM Financeiro WHERE LOWER(nomConsumivel) LIKE :nomConsumivel");
        query.setParameter("nomConsumivel", "%" + pParam.toLowerCase() + "%");
        return query.list();
    }

    public static Financeiro readId(int pCodigo) {
        Query query = HibernateUtil.getSession().createQuery("FROM Financeiro WHERE codFinanceiro = " + pCodigo);
        return (Financeiro) query.uniqueResult();
    }
}
