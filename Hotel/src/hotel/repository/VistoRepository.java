package hotel.repository;

import hotel.config.HibernateUtil;
import hotel.model.Novidade;
import hotel.model.Usuario;
import hotel.model.Visto;
import org.hibernate.Query;

public class VistoRepository {

    public static Visto readByUserAndNew(Usuario user, Novidade news) {
        Query query = HibernateUtil.getSession().createQuery("FROM Visto WHERE usuario.codUsuario = " + user.getCodUsuario() + " AND novidade.codNovidade = " + news.getCodNovidade());
        return (Visto) query.uniqueResult();
    }
}
