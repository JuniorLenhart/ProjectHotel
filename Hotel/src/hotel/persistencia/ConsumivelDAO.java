/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.persistencia;

import hotel.config.HibernateUtil;
import hotel.model.Consumivel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author George
 */
public class ConsumivelDAO implements DAO<Consumivel>{

    Session sessao = null;
    Transaction t;
    public ConsumivelDAO() {
    }
    
    @Override
    public String insert(Consumivel pT) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        t = sessao.beginTransaction();
        sessao.save(pT);
        t.commit();
        sessao.close();
        return null;
    }

    @Override
    public String update(Consumivel pT) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String delete(int pCodigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Consumivel> readAll() {
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
            Consumivel s = (Consumivel) o;
        }
        return (ArrayList<Consumivel>) resultado;
    }

    @Override
    public ArrayList<Consumivel> read(String pParam) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Consumivel readId(int pCodigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
