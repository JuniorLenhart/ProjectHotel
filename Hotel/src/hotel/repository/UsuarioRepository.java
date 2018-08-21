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
        Query query = HibernateUtil.getSession().createQuery("FROM Usuario u WHERE LOWER(u.pessoa.nomPessoa) LIKE :nomUsuario");
        query.setParameter("nomUsuario", "%" + pParam.toLowerCase() + "%");
        return query.list();
    }

    public static Usuario readId(int pCodigo) {
        Query query = HibernateUtil.getSession().createQuery("FROM Usuario WHERE codUsuario = " + pCodigo);
        return (Usuario) query.uniqueResult();
    }

    public static Usuario getUserWithLogin(String pLogin) {
        Query query = HibernateUtil.getSession().createQuery("FROM Usuario WHERE desLogin LIKE :desLogin");
        query.setParameter("desLogin", "%" + pLogin.toLowerCase() + "%");
        return (Usuario) query.uniqueResult();
    }

    public static Usuario validaLogin(String pLogin, String pSenha) {
        Query query = HibernateUtil.getSession().createQuery("FROM Usuario WHERE desLogin = '" + pLogin + "' AND desSenha = '" + pSenha + "'");
        Usuario usuario = (Usuario) query.uniqueResult();
        if (usuario != null) {
            return usuario;
        }
        return null;
    }
}
