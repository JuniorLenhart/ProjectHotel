package hotel.repository;

import hotel.config.HibernateUtil;
import hotel.model.Usuario;
import java.util.List;
import org.hibernate.Query;

public class UsuarioRepository {

    public static List<Usuario> readAll() {
        Query query = HibernateUtil.getSession().createQuery("FROM Usuario");
        return query.list();
    }

    public static List<Usuario> read(String pParam) {
        Query query = HibernateUtil.getSession().createQuery("FROM Usuario u WHERE LOWER(u.pessoa.nomPessoa) LIKE :nomPessoa");
        query.setParameter("nomPessoa", "%" + pParam.toLowerCase() + "%");
        return query.list();
    }

    public static Usuario readId(int pCodigo) {
        Query query = HibernateUtil.getSession().createQuery("FROM Usuario WHERE codPessoa = " + pCodigo);
        return (Usuario) query.uniqueResult();
    }
}
