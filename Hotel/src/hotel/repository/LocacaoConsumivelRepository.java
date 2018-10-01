package hotel.repository;

import hotel.config.HibernateUtil;
import hotel.model.LocacaoConsumivel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;

public class LocacaoConsumivelRepository {

    public static List<LocacaoConsumivel> readAll() {
        Query query = HibernateUtil.getSession().createQuery("FROM LocacaoConsumivel");
        return query.list();
    }

    public static LocacaoConsumivel readId(int pCodigo) {
        Query query = HibernateUtil.getSession().createQuery("FROM LocacaoConsumivel WHERE codLocacaoConsumivel = " + pCodigo);
        return (LocacaoConsumivel) query.uniqueResult();
    }

    public static List<LocacaoConsumivel> readLocacaoId(int pCodigo) {
        Query query = HibernateUtil.getSession().createQuery("FROM LocacaoConsumivel WHERE locacao.codLocacao = " + pCodigo);
        return query.list();
    }

    public static List<LocacaoConsumivel> readLocacaoIdWithSUM(int pCodigo) {
        Query query = HibernateUtil.getSession().createQuery("FROM LocacaoConsumivel WHERE locacao.codLocacao = " + pCodigo);
        List<LocacaoConsumivel> list = query.list();
        List<LocacaoConsumivel> listNew = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap();
        for (LocacaoConsumivel object : list) {
            boolean isOnMap = false;
            for (int i = 0; i < listNew.size(); i++) {
                if (listNew.get(i).getConsumivel().getCodConsumivel().intValue() == object.getConsumivel().getCodConsumivel().intValue()) {
                    isOnMap = true;
                    listNew.get(i).setQtdConsumivel(listNew.get(i).getQtdConsumivel() + object.getQtdConsumivel());
                    listNew.get(i).setVlrConsumivel(listNew.get(i).getVlrConsumivel().add(object.getVlrConsumivel()));
                }
            }
            if (!isOnMap) {
                listNew.add(object);
            }
        }
        return listNew;
    }

    public static List<LocacaoConsumivel> readLocacaoIdAndPessoa(int pCodigo, String pNome) {
        Query query = HibernateUtil.getSession().createQuery("FROM LocacaoConsumivel WHERE locacao.codLocacao = " + pCodigo + " AND LOWER(consumivel.nomConsumivel) LIKE :nomConsumivel");
        query.setParameter("nomConsumivel", "%" + pNome.toLowerCase() + "%");
        return query.list();
    }
}
