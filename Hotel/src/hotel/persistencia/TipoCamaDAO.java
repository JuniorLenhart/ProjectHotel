package hotel.persistencia;

import hotel.config.HibernateUtil;
import hotel.model.TipoCama;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TipoCamaDAO implements DAO<TipoCama> {

    public TipoCamaDAO() {
    }

    @Override
    public String insert(TipoCama pT) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessao.beginTransaction();

        sessao.saveOrUpdate(pT);

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
        TipoCama tipoCama = (TipoCama) readId(pCodigo);
        tipoCama.setIndSituacao("E");
        insert(tipoCama);
        return null;
    }

    @Override
    public ArrayList<TipoCama> readAll() {
        List resultado = null;
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();

        org.hibernate.Query q = sessao.createQuery("FROM TipoCama");
        resultado = q.list();

        return (ArrayList<TipoCama>) resultado;
    }

    @Override
    public ArrayList<TipoCama> read(String pParam) {
        List resultado = null;
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();

        org.hibernate.Query q = sessao.createQuery("FROM TipoCama WHERE desTipoCama LIKE :desTipoCama");
        q.setParameter("desTipoCama", "%" + pParam.toLowerCase() + "%");
        resultado = q.list();

        return (ArrayList<TipoCama>) resultado;
    }

    @Override
    public TipoCama readId(int pCodigo) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();

        org.hibernate.Query q = sessao.createQuery("FROM TipoCama WHERE codTipoCama = " + pCodigo);
        TipoCama tc = (TipoCama) q.uniqueResult();

        return tc;
    }

    public void popularTabela(JTable pTabela, int pOption, String pParam) {
        Object[][] lTabela = null;

        Object[] lTabelaTitulo = new Object[4];
        lTabelaTitulo[0] = "Código";
        lTabelaTitulo[1] = "Descrição";
        lTabelaTitulo[2] = "Lugar";
        lTabelaTitulo[3] = "Situação";

        int lLinha = 0;
        if (pOption == 0) {
            ArrayList<TipoCama> listTipoCama = readAll();

            lTabela = new Object[listTipoCama.size()][6];
            for (TipoCama tipoCama : listTipoCama) {
                String situacao = (tipoCama.getIndSituacao().equals("A") ? "Ativo" : "Excluído");

                lTabela[lLinha][0] = tipoCama.getCodTipoCama();
                lTabela[lLinha][1] = tipoCama.getDesTipoCama();
                lTabela[lLinha][2] = tipoCama.getQtdLugarTipoCama();
                lTabela[lLinha][3] = situacao;
                lLinha++;
            }
        } else if (pOption == 1) {
            ArrayList<TipoCama> listTipoCama = read(pParam);

            lTabela = new Object[listTipoCama.size()][6];
            for (TipoCama tipoCama : listTipoCama) {
                String situacao = (tipoCama.getIndSituacao().equals("A") ? "Ativo" : "Excluído");

                lTabela[lLinha][0] = tipoCama.getCodTipoCama();
                lTabela[lLinha][1] = tipoCama.getDesTipoCama();
                lTabela[lLinha][2] = tipoCama.getQtdLugarTipoCama();
                lTabela[lLinha][3] = situacao;
                lLinha++;
            }
        } else {
            TipoCama tipoCama = readId(Integer.parseInt(pParam));
            if (tipoCama == null) {
                JOptionPane.showMessageDialog(null, "Tipo de Cama não encontrado pelo código " + pParam);
            } else {
                String situacao = (tipoCama.getIndSituacao().equals("A") ? "Ativo" : "Excluído");

                lTabela = new Object[1][4];

                lTabela[lLinha][0] = tipoCama.getCodTipoCama();
                lTabela[lLinha][1] = tipoCama.getDesTipoCama();
                lTabela[lLinha][2] = tipoCama.getQtdLugarTipoCama();
                lTabela[lLinha][3] = situacao;
                lLinha++;
            }
        }

        pTabela.setModel(new DefaultTableModel(lTabela, lTabelaTitulo) {
            @Override
            public boolean isCellEditable(int pRow, int pColumn) {
                return false;
                /*  
                if (column == 3) {  // Apenas a coluna 3 será editável
                    return true;
                } else {
                    return false;
                }
                 */
            }

            // Alteração no método que determina a coluna em que o objeto ImageIcon deverá aparecer
            @Override
            public Class getColumnClass(int pColumn) {

                if (pColumn == 2) {
                    //return ImageIcon.class;
                }
                return Object.class;
            }
        });

        pTabela.setSelectionMode(0);

        // Redimensiona as colunas de uma tabela
        TableColumn lColumn = null;

        DefaultTableCellRenderer lLeft = new DefaultTableCellRenderer();
        DefaultTableCellRenderer lCenter = new DefaultTableCellRenderer();
        DefaultTableCellRenderer lRight = new DefaultTableCellRenderer();

        lLeft.setHorizontalAlignment(SwingConstants.LEFT);
        lCenter.setHorizontalAlignment(SwingConstants.CENTER);
        lRight.setHorizontalAlignment(SwingConstants.RIGHT);

        for (int i = 0; i < pTabela.getColumnCount(); i++) {
            lColumn = pTabela.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    lColumn.setPreferredWidth(30);
                    lColumn.setCellRenderer(lRight);
                    break;
                case 1:
                    lColumn.setPreferredWidth(200);
                    lColumn.setCellRenderer(lLeft);
                    break;
                case 2:
                    lColumn.setPreferredWidth(50);
                    lColumn.setCellRenderer(lCenter);
                    break;
                case 3:
                    lColumn.setPreferredWidth(50);
                    lColumn.setCellRenderer(lCenter);
                    break;
            }
        }

        /*
        // Renderização das linhas da tabela mudar a cor
        pTabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                if (row % 2 == 0) {
                    setBackground(Color.GREEN);
                } else {
                    setBackground(Color.LIGHT_GRAY);
                }
                return this;
            }
        });
         */
    }
}
