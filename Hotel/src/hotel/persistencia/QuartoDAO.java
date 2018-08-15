package hotel.persistencia;

import hotel.config.HibernateUtil;
import hotel.model.Quarto;
import hotel.model.TipoCama;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class QuartoDAO implements DAO<Quarto> {

    public QuartoDAO() {
    }

    @Override
    public String insert(Quarto pT) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessao.beginTransaction();

        sessao.saveOrUpdate(pT);

        t.commit();
        sessao.close();
        return null;
    }

    @Override
    public String update(Quarto pT) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String delete(int pCodigo) {
        Quarto quarto = (Quarto) readId(pCodigo);
        quarto.setIndSituacao("E");
        insert(quarto);
        return null;
    }

    @Override
    public ArrayList<Quarto> readAll() {
        List resultado = null;
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();

        org.hibernate.Query q = sessao.createQuery("FROM Quarto");
        resultado = q.list();

        sessao.close();
        return (ArrayList<Quarto>) resultado;
    }

    @Override
    public ArrayList<Quarto> read(String pParam) {
        List resultado = null;
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();

        org.hibernate.Query q = sessao.createQuery("FROM Quarto WHERE numQuarto LIKE :numQuarto");
        q.setParameter("numQuarto", "%" + pParam.toLowerCase() + "%");
        resultado = q.list();

        sessao.close();
        return (ArrayList<Quarto>) resultado;
    }

    @Override
    public Quarto readId(int pCodigo) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();

        org.hibernate.Query q = sessao.createQuery("FROM Quarto WHERE codQuarto = " + pCodigo);
        Quarto quarto = (Quarto) q.uniqueResult();

        sessao.close();
        return quarto;
    }

    public void popularTabela(JTable pTabela, int pOption, String pParam) {
        Object[][] lTabela = null;

        Object[] lTabelaTitulo = new Object[5];
        lTabelaTitulo[0] = "Código";
        lTabelaTitulo[1] = "Número Quarto";
        lTabelaTitulo[2] = "Valor";
        lTabelaTitulo[3] = "Tipo(s) de quarto(s)";
        lTabelaTitulo[4] = "Situação";

        int lLinha = 0;
        if (pOption == 0) {
            ArrayList<Quarto> listQuarto = readAll();

            lTabela = new Object[listQuarto.size()][6];
            for (Quarto quarto : listQuarto) {
                String situacao = (quarto.getIndSituacao().equals("A") ? "Ativo" : "Excluído");

                lTabela[lLinha][0] = quarto.getCodQuarto();
                lTabela[lLinha][1] = quarto.getNumQuarto();
                lTabela[lLinha][2] = quarto.getVlrQuarto();
                String inicio = "<html> ";
                for (TipoCama cama : quarto.getTipoCama()) {
                    lTabela[lLinha][3] += inicio + cama.getDesTipoCama() + " - Lugares: " + cama.getQtdLugarTipoCama() + "<br/> ";
                    inicio = null;
                }
                lTabela[lLinha][3] += " </html>";
                lTabela[lLinha][4] = situacao;

                lLinha++;
            }
        } else if (pOption == 1) {
            ArrayList<Quarto> listQuarto = read(pParam);

            lTabela = new Object[listQuarto.size()][6];
            for (Quarto quarto : listQuarto) {
                String situacao = (quarto.getIndSituacao().equals("A") ? "Ativo" : "Excluído");

                lTabela[lLinha][0] = quarto.getCodQuarto();
                lTabela[lLinha][1] = quarto.getNumQuarto();
                lTabela[lLinha][2] = quarto.getVlrQuarto();
                for (TipoCama cama : quarto.getTipoCama()) {
                    lTabela[lLinha][3] += "<html>" + cama.getDesTipoCama() + " - Lugares: " + cama.getQtdLugarTipoCama() + "<br /> </html>";
                }
                lTabela[lLinha][4] = situacao;

                lLinha++;
            }
        } else {
            Quarto quarto = readId(Integer.parseInt(pParam));
            if (quarto == null) {
                JOptionPane.showMessageDialog(null, "Tipo de Cama não encontrado pelo código " + pParam);
            } else {
                String situacao = (quarto.getIndSituacao().equals("A") ? "Ativo" : "Excluído");

                lTabela = new Object[1][4];

                lTabela[lLinha][0] = quarto.getCodQuarto();
                lTabela[lLinha][1] = quarto.getNumQuarto();
                lTabela[lLinha][2] = quarto.getVlrQuarto();
                for (TipoCama cama : quarto.getTipoCama()) {
                    lTabela[lLinha][3] += "<html>" + cama.getDesTipoCama() + " - Lugares: " + cama.getQtdLugarTipoCama() + "<br> </html>";
                }
                lTabela[lLinha][4] = situacao;

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
                    lColumn.setPreferredWidth(50);
                    lColumn.setCellRenderer(lLeft);
                    break;
                case 2:
                    lColumn.setPreferredWidth(50);
                    lColumn.setCellRenderer(lCenter);
                    break;
                case 3:
                    lColumn.setPreferredWidth(200);
                    lColumn.setCellRenderer(lCenter);
                    break;
                case 4:
                    lColumn.setPreferredWidth(50);
                    lColumn.setCellRenderer(lCenter);
                    break;
            }
        }
        /*// Renderização das linhas da tabela mudar a cor
        pTabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                int fontHeight = this.getFontMetrics(this.getFont()).getHeight();
                int textLength = this.getText().length();
                int lines = textLength / table.getColumnCount() +1;//+1, cause we need at least 1 row.           
                int height = fontHeight * lines;            
                table.setRowHeight(row, height);
                if (row % 2 == 0) {
                    setBackground(Color.GREEN);
                } else {
                    setBackground(Color.LIGHT_GRAY);
                }
                return this;
            }
            
        });*/
    }
}
