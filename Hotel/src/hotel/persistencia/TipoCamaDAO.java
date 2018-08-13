package hotel.persistencia;

import hotel.config.HibernateUtil;
import hotel.model.TipoCama;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TipoCamaDAO implements DAO<TipoCama> {

    public TipoCamaDAO() {
    }

    @Override
    public String insert(TipoCama pT) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessao.beginTransaction();

        sessao.save(pT);

        t.commit();
        sessao.close();
        return null;
    }

    @Override
    public String update(TipoCama pT) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String delete(int pCodigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<TipoCama> readAll() {
        List resultado = null;
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();

        // busca por código
//            int id = 3;
//            org.hibernate.Query q = sessao.createQuery("from Classe where id = " + id);
        // busca todos os registros
        // observar: a classe Classe no from -> C maiúsculo
        org.hibernate.Query q = sessao.createQuery("from Pessoa");
        resultado = q.list();

        for (Object o : resultado) {
            TipoCama s = (TipoCama) o;
        }
        return (ArrayList<TipoCama>) resultado;
    }

    @Override
    public ArrayList<TipoCama> read(String pParam) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TipoCama readId(int pCodigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
