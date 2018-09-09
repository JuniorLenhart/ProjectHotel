package hotel.repository;

import hotel.config.HibernateUtil;
import hotel.model.Pessoa;
import java.util.List;
import org.hibernate.Query;

public class PessoaRepository {

    public static List<Pessoa> readAll() {
        Query query = HibernateUtil.getSession().createQuery("FROM Pessoa");
        return query.list();
    }

    public static List<Pessoa> read(String pNome) {
        Query query = HibernateUtil.getSession().createQuery("FROM Pessoa WHERE LOWER(nomPessoa) LIKE :nomPessoa");
        query.setParameter("nomPessoa", "%" + pNome.toLowerCase() + "%");
        return query.list();
    }

    public static Pessoa readId(int pCodigo) {
        Query query = HibernateUtil.getSession().createQuery("FROM Pessoa WHERE codPessoa = " + pCodigo);
        return (Pessoa) query.uniqueResult();
    }

    public static List<Pessoa> readSituation(String pSituation) {
        Query query = HibernateUtil.getSession().createQuery("FROM Pessoa WHERE indSituacao = :indSituacao");
        query.setParameter("indSituacao", pSituation);
        return query.list();
    }

    public static List<Pessoa> readProfileNotUser() {
        return HibernateUtil.getSession().createSQLQuery("SELECT * FROM profile_not_user").addEntity(Pessoa.class).list();
    }

    public static Pessoa readCPF(String pCPF) {
        Query query = HibernateUtil.getSession().createQuery("FROM Pessoa WHERE numCpf LIKE :numCpf AND indSituacao = 'A'");
        query.setParameter("numCpf", pCPF);
        return (Pessoa) query.uniqueResult();
    }

    public static List<Pessoa> readListCPF(String pCPF) {
        Query query = HibernateUtil.getSession().createQuery("FROM Pessoa WHERE numCpf LIKE :numCpf AND indSituacao = 'A'");
        query.setParameter("numCpf", "%" + pCPF + "%");
        return query.list();
    }

    public static List<Pessoa> readActive(String pNome) {
        Query query = HibernateUtil.getSession().createQuery("FROM Pessoa WHERE LOWER(nomPessoa) LIKE :nomPessoa AND indSituacao = 'A'");
        query.setParameter("nomPessoa", "%" + pNome.toLowerCase() + "%");
        return query.list();
    }

    public static Pessoa readIdActive(int pCodigo) {
        Query query = HibernateUtil.getSession().createQuery("FROM Pessoa WHERE codPessoa = " + pCodigo + " AND indSituacao = 'A'");
        return (Pessoa) query.uniqueResult();
    }
}
