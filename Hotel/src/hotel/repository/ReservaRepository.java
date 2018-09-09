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

    public static List<Reserva> readPersonNamePayed(String pNomePessoa) {
        Query query = HibernateUtil.getSession().createQuery("FROM Reserva WHERE LOWER(pessoa.nomPessoa) LIKE :nomPessoa AND indSituacao = 'C'");
        query.setParameter("nomPessoa", "%" + pNomePessoa.toLowerCase() + "%");
        return query.list();
    }

    public static List<Reserva> readPersonCPFPayed(String pCPF) {
        Query query = HibernateUtil.getSession().createQuery("FROM Reserva WHERE pessoa.numCpf LIKE :numCpf AND indSituacao = 'C'");
        query.setParameter("numCpf", "%" + pCPF.toLowerCase() + "%");
        return query.list();
    }

    public static List<Reserva> readPersonCPF(String pCPF) {
        Query query = HibernateUtil.getSession().createQuery("FROM Reserva WHERE pessoa.numCpf LIKE :numCpf");
        query.setParameter("numCpf", "%" + pCPF.toLowerCase() + "%");
        return query.list();
    }

    public static Reserva readId(int pCodigo) {
        Query query = HibernateUtil.getSession().createQuery("FROM Reserva WHERE codReserva = " + pCodigo);
        return (Reserva) query.uniqueResult();
    }

    public static List<Reserva> readNumberRoom(String pNumeroQuarto) {
        Query query = HibernateUtil.getSession().createQuery("FROM Reserva WHERE quarto.numQuarto LIKE :numQuarto");
        query.setParameter("numQuarto", "%" + pNumeroQuarto + "%");
        return query.list();
    }
}
