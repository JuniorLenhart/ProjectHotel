package hotel.repository;

import hotel.config.HibernateUtil;
import hotel.model.Reserva;
import java.util.List;
import org.hibernate.Query;

public class ReservaRepository {

    public static List<Reserva> readAll() {
        Query query = HibernateUtil.getSession().createQuery("FROM Reserva");
        return query.list();
    }
    
    public static List<Reserva> readAllPayed() {
        Query query = HibernateUtil.getSession().createQuery("FROM Reserva WHERE indSituacao = 'C'");
        return query.list();
    }
    
    public static List<Reserva> readAllNotPayedYet() {
        Query query = HibernateUtil.getSession().createQuery("FROM Reserva WHERE indSituacao = 'E'");
        return query.list();
    }
    
    public static List<Reserva> readAllCanceled() {
        Query query = HibernateUtil.getSession().createQuery("FROM Reserva WHERE indSituacao = 'I'");
        return query.list();
    }

    public static List<Reserva> readPerPersonNamePayed(String pParam) {
        Query query = HibernateUtil.getSession().createQuery("FROM Reserva WHERE LOWER(codPessoa.nomPessoa) LIKE :nomPessoa AND indSituacao = 'C'");
        query.setParameter("nomPessoa", "%" + pParam.toLowerCase() + "%");
        return query.list();
    }
    
    public static Reserva readPerPersonCPFPayed(String pParam) {
        Query query = HibernateUtil.getSession().createQuery("FROM Reserva WHERE codPessoa.numCpf LIKE :numCpf AND indSituacao = 'C'");
        query.setParameter("numCpf", "%" + pParam.toLowerCase() + "%");
        return (Reserva) query.uniqueResult();
    }

    public static Reserva readId(int pCodigo) {
        Query query = HibernateUtil.getSession().createQuery("FROM Reserva WHERE codReserva = " + pCodigo);
        return (Reserva) query.uniqueResult();
    }
}
