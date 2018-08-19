package hotel.controller;

import hotel.config.HibernateUtil;
import org.hibernate.Transaction;
import java.util.List;

public abstract class BaseController<T> implements IBaseController<T> {

    @Override
    public String save(T pT) {
        Transaction transaction = HibernateUtil.getBeginTransaction();

        HibernateUtil.getSession().saveOrUpdate(pT);

        transaction.commit();
        return null;
    }

    @Override
    public String saveAll(List<T> pT) {
        pT.forEach((object) -> {
            save(object);
        });
        return null;
    }

    @Override
    public String delete(T pT) {
        Transaction transaction = HibernateUtil.getBeginTransaction();

        HibernateUtil.getSession().delete(pT);

        transaction.commit();
        return null;
    }

    @Override
    public String deleteAll(List<T> pT) {
        pT.forEach((object) -> {
            delete(object);
        });
        return null;
    }
}
