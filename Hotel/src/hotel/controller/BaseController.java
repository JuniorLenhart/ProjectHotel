package hotel.controller;

import hotel.config.HibernateUtil;
import hotel.model.Parametro;
import org.hibernate.Transaction;
import java.util.List;

public abstract class BaseController<T> implements IBaseController<T> {

    @Override
    public String save(T pT) {
        try {
            Transaction transaction = HibernateUtil.getBeginTransaction();
            new UsuarioController().setUserSession(Parametro.USUARIO);
            
            HibernateUtil.getSession().saveOrUpdate(pT);

            transaction.commit();
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }

    @Override
    public String saveAll(List<T> pT) {
        try {
            pT.forEach((object) -> {
                save(object);
            });
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }

    @Override
    public String delete(T pT) {
        try {
            Transaction transaction = HibernateUtil.getBeginTransaction();
            new UsuarioController().setUserSession(Parametro.USUARIO);

            HibernateUtil.getSession().delete(pT);

            transaction.commit();
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }

    @Override
    public String deleteAll(List<T> pT) {
        try {
            pT.forEach((object) -> {
                delete(object);
            });
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }
}
