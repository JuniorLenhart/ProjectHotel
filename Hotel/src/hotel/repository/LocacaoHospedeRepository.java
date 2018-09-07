package hotel.repository;

import hotel.config.HibernateUtil;
import hotel.model.LocacaoHospede;
import java.util.List;
import org.hibernate.Query;

public class LocacaoHospedeRepository {

    public static List<LocacaoHospede> readAll() {
        Query query = HibernateUtil.getSession().createQuery("FROM LocacaoHospede");
        return query.list();
    }

    public static List<LocacaoHospede> readPessoaNome(String pParam) {
        Query query = HibernateUtil.getSession().createQuery("FROM LocacaoHospede WHERE codPessoa.nomPessoa LIKE :pessoaID");
        query.setParameter("pessoaID", "%" + pParam.toLowerCase() + "%");
        return query.list();
    }

    public static List<LocacaoHospede> readLocacaoId(int pCodigo) {
        Query query = HibernateUtil.getSession().createQuery("FROM LocacaoHospede WHERE codLocacao.codLocacao = " + pCodigo);
        return query.list();
    }

    public static LocacaoHospede readResponsavel(int pCodigo) {
        Query query = HibernateUtil.getSession().createQuery("FROM LocacaoHospede WHERE codLocacao.codLocacao = " + pCodigo + " AND indResponsavel = 'S'");
        return (LocacaoHospede) query.uniqueResult();
    }
}
