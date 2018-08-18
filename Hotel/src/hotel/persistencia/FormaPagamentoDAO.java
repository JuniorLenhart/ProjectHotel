/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.persistencia;

import hotel.config.HibernateUtil;
import hotel.model.FormaPagamento;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author George
 */
public class FormaPagamentoDAO implements DAO<FormaPagamento>{

    @Override
    public String insert(FormaPagamento pT) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessao.beginTransaction();

        sessao.saveOrUpdate(pT);

        t.commit();
        sessao.close();
        return null;
    }

    @Override
    public String update(FormaPagamento pT) {
        return null;
    }

    @Override
    public String delete(int pCodigo) {
        FormaPagamento forma = (FormaPagamento) readId(pCodigo);
        forma.setIndSituacao("E");
        insert(forma);
        return null;
    }

    @Override
    public ArrayList<FormaPagamento> readAll() {
        List resultado = null;
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        
        org.hibernate.Query q = sessao.createQuery("from FormaPagamento");
        resultado = q.list();
        
        sessao.close();
        return (ArrayList<FormaPagamento>) resultado;
    }

    @Override
    public ArrayList<FormaPagamento> read(String pParam) {
        List resultado = null;
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        
        org.hibernate.Query q = sessao.createQuery("from FormaPagamento where lower(desFormaPgto) LIKE :nome ");
        q.setParameter("nome", "%"+pParam.toLowerCase()+"%");
        resultado = q.list();
        
        sessao.close();
        return (ArrayList<FormaPagamento>) resultado;
    }

    @Override
    public FormaPagamento readId(int pCodigo) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();

        org.hibernate.Query q = sessao.createQuery("FROM FormaPagamento WHERE codFormaPgto = " + pCodigo);
        FormaPagamento tc = (FormaPagamento) q.uniqueResult();

        sessao.close();
        return tc;
    }
    
    public void popularTabela(JTable tabela, int escolha, String parametro)
    {                                                                      
                                                                           
        // dados da tabela
        Object[][] dadosTabela = null;
        // cabecalho da tabela
        Object[] cabecalho = new Object[3];
        cabecalho[0] = "Código";
        cabecalho[1] = "Nome";
        cabecalho[2] = "Situação";
        // cria matriz de acordo com nº de registros da tabela
        int lin = 0;
        if(escolha==0) {
            dadosTabela = new Object[readAll().size()][3];
        
            for (FormaPagamento formaPagamento : readAll()) {
                String situacao = (formaPagamento.getIndSituacao().equals("A") ? "Ativo" : "Excluído");
                
                dadosTabela[lin][0] = formaPagamento.getCodFormaPgto();
                dadosTabela[lin][1] = formaPagamento.getDesFormaPgto();
                dadosTabela[lin][2] = situacao;
                lin++;
            }
        } else if(escolha==1) {
            dadosTabela = new Object[read(parametro).size()][3];
        
            for (FormaPagamento formaPagamento : read(parametro)) {
                String situacao = (formaPagamento.getIndSituacao().equals("A") ? "Ativo" : "Excluído");
                
                dadosTabela[lin][0] = formaPagamento.getCodFormaPgto();
                dadosTabela[lin][1] = formaPagamento.getDesFormaPgto();
                dadosTabela[lin][2] = situacao;
                lin++;
            }
        } else if(escolha==2) {
            FormaPagamento formaPagamento = readId(Integer.parseInt(parametro));
            if(formaPagamento == null) {
                JOptionPane.showMessageDialog(null, "Forma de Pagamento não encontrado pelo codigo: "+parametro);
            } else {
                dadosTabela = new Object[1][3];
                String situacao = (formaPagamento.getIndSituacao().equals("A") ? "Ativo" : "Excluído");
        
                dadosTabela[lin][0] = formaPagamento.getCodFormaPgto();
                dadosTabela[lin][1] = formaPagamento.getDesFormaPgto();
                dadosTabela[lin][2] = situacao;
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