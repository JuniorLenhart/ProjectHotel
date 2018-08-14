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
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author George
 */
public class ConsumivelDAO implements DAO<Consumivel>{

    public ConsumivelDAO() {
    }
    
    @Override
    public String insert(Consumivel pT) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessao.beginTransaction();

        sessao.saveOrUpdate(pT);

        t.commit();
        sessao.close();
        return null;
    }

    @Override
    public String update(Consumivel pT) {
        return null;
    }

    @Override
    public String delete(int pCodigo) {
        Consumivel forma = (Consumivel) readId(pCodigo);
        forma.setIndSituacao("E");
        insert(forma);
        return null;
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
        org.hibernate.Query q = sessao.createQuery("from Consumivel");
        resultado = q.list();

        sessao.close();
        return (ArrayList<Consumivel>) resultado;
    }

    @Override
    public ArrayList<Consumivel> read(String pParam) {
        List resultado = null;
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();

            // busca por código
//            int id = 3;
//            org.hibernate.Query q = sessao.createQuery("from Classe where id = " + id);
            // busca todos os registros
            // observar: a classe Classe no from -> C maiúsculo
        org.hibernate.Query q = sessao.createQuery("from Consumivel where lower(nomConsumivel) LIKE :nome ");
        q.setParameter("nome", "%"+pParam.toLowerCase()+"%");
        resultado = q.list();

        sessao.close();
        return (ArrayList<Consumivel>) resultado;
    }

    @Override
    public Consumivel readId(int pCodigo) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        
        org.hibernate.Query q = sessao.createQuery("from Consumivel where codConsumivel = " + pCodigo);
        Consumivel c = (Consumivel) q.uniqueResult();
        
        sessao.close();
        return c;
    }
    
    public void popularTabela(JTable tabela, int escolha, String parametro)
    {                                                                      
                                                                           
        // dados da tabela
        Object[][] dadosTabela = null;
        // cabecalho da tabela
        Object[] cabecalho = new Object[6];
        cabecalho[0] = "Código";
        cabecalho[1] = "Nome";
        cabecalho[2] = "Descrição";
        cabecalho[3] = "Valor";
        cabecalho[4] = "Tipo";
        cabecalho[5] = "Situação";
        // cria matriz de acordo com nº de registros da tabela
        int lin = 0;
        if(escolha==0) {
            dadosTabela = new Object[readAll().size()][6];
        
            for (Consumivel consumivel : readAll()) {
                String situacao = (consumivel.getIndSituacao().equals("A") ? "Ativo" : "Excluído");
                
                dadosTabela[lin][0] = consumivel.getCodConsumivel();
                dadosTabela[lin][1] = consumivel.getNomConsumivel();
                dadosTabela[lin][2] = consumivel.getDesConsumivel();
                dadosTabela[lin][3] = consumivel.getVlrConsumivel();
                dadosTabela[lin][4] = consumivel.getTipConsumivel();
                dadosTabela[lin][5] = situacao;
                lin++;
            }
        } else if(escolha==1) {
            dadosTabela = new Object[read(parametro).size()][6];
        
            for (Consumivel consumivel : read(parametro)) {
                String situacao = (consumivel.getIndSituacao().equals("A") ? "Ativo" : "Excluído");
                
                dadosTabela[lin][0] = consumivel.getCodConsumivel();
                dadosTabela[lin][1] = consumivel.getNomConsumivel();
                dadosTabela[lin][2] = consumivel.getDesConsumivel();
                dadosTabela[lin][3] = consumivel.getVlrConsumivel();
                dadosTabela[lin][4] = consumivel.getTipConsumivel();
                dadosTabela[lin][5] = situacao;
                lin++;
            }
        } else if(escolha==2) {
            Consumivel consumivel = readId(Integer.parseInt(parametro));
            if(consumivel == null) {
                JOptionPane.showMessageDialog(null, "Consumível não encontrado pelo codigo: "+parametro);
            } else {
                dadosTabela = new Object[1][6];
                String situacao = (consumivel.getIndSituacao().equals("A") ? "Ativo" : "Excluído");
                
                dadosTabela[lin][0] = consumivel.getCodConsumivel();
                dadosTabela[lin][1] = consumivel.getNomConsumivel();
                dadosTabela[lin][2] = consumivel.getDesConsumivel();
                dadosTabela[lin][3] = consumivel.getVlrConsumivel();
                dadosTabela[lin][4] = consumivel.getTipConsumivel();
                dadosTabela[lin][5] = situacao;
                lin++;
            }
            
        }
        
        // configuracoes adicionais no componente tabela
        tabela.setModel(new DefaultTableModel(dadosTabela, cabecalho) {
            @Override
            // quando retorno for FALSE, a tabela nao é editavel
            public boolean isCellEditable(int row, int column) {
                 if (column == 3) 
                 {  // apenas a coluna 3 sera editavel
                 return false;
                 } else {
                 return false;
                 }
            }
            
            public Vector getDataVector() 
            {
                return dataVector;
            }
            
            // alteracao no metodo que determina a coluna em que o objeto ImageIcon devera aparecer
            @Override
            public Class getColumnClass(int column) 
            {

                if (column == 2) {
//                    return ImageIcon.class;
                }
                return Object.class;
            }
            
        });

        // permite seleção de apenas uma linha da tabela
        tabela.setSelectionMode(0);
        
        TableColumn column = null;
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            column = tabela.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(17);
                    break;
                case 1:
                    column.setPreferredWidth(140);
                    break;
            }
        }
    }
}
