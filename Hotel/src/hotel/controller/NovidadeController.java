package hotel.controller;

import hotel.model.Novidade;
import hotel.repository.NovidadeRepository;
import hotel.support.Formatacao;
import java.sql.Timestamp;
import java.util.List;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class NovidadeController extends BaseController<Novidade> {

    public Novidade getReadId(int pCodigo) {
        try {
            return NovidadeRepository.readId(pCodigo);
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }

    public Novidade getLastNew() {
        try {
            return NovidadeRepository.getLastNew();
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }

    public void popularTabela(JTable pTabela, int pOption, String pParam) {
        try {
            Object[][] lTabela = null;

            Object[] lTabelaTitulo = new Object[4];
            lTabelaTitulo[0] = "Código";
            lTabelaTitulo[1] = "Data";
            lTabelaTitulo[2] = "Versão";
            lTabelaTitulo[3] = "Descrição";

            int lLinha = 0;
            switch (pOption) {
                case 0: {
                    List<Novidade> listNovidade = NovidadeRepository.readAll();

                    lTabela = new Object[listNovidade.size()][4];
                    for (Novidade n : listNovidade) {
                        lTabela[lLinha][0] = n.getCodNovidade();
                        lTabela[lLinha][1] = Formatacao.ajustaDataDMA(Timestamp.valueOf(n.getDtaNovidade().toString()).toString());
                        lTabela[lLinha][2] = n.getVerNovidade();
                        lTabela[lLinha][3] = n.getDesNovidade();
                        lLinha++;
                    }
                    break;
                }
            }

            pTabela.setModel(new DefaultTableModel(lTabela, lTabelaTitulo) {
                @Override
                public boolean isCellEditable(int pRow, int pColumn) {
                    return false;
                }

                @Override
                public Class getColumnClass(int pColumn) {
                    if (pColumn == 2) {
                    }
                    return Object.class;
                }
            });

            pTabela.setSelectionMode(0);

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
                        lColumn.setPreferredWidth(20);
                        lColumn.setCellRenderer(lRight);
                        break;
                    case 1:
                        lColumn.setPreferredWidth(60);
                        lColumn.setCellRenderer(lCenter);
                        break;
                    case 2:
                        lColumn.setPreferredWidth(60);
                        lColumn.setCellRenderer(lLeft);
                        break;
                    case 3:
                        lColumn.setPreferredWidth(200);
                        lColumn.setCellRenderer(lLeft);
                        break;
                }
            }
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
    }
}
