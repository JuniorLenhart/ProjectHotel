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

    public static List<Reserva> read(String pParam) {
        Query query = HibernateUtil.getSession().createQuery("FROM Reserva WHERE codPessoa.nomPessoa LIKE :pessoaID");
        query.setParameter("pessoaID", "%" + pParam.toLowerCase() + "%");
        return query.list();
    }

    public static Reserva readId(int pCodigo) {
        Query query = HibernateUtil.getSession().createQuery("FROM Reserva WHERE codReserva = " + pCodigo);
        return (Reserva) query.uniqueResult();
    }
}
