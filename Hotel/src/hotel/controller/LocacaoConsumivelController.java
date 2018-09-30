package hotel.controller;

import hotel.model.LocacaoConsumivel;
import hotel.model.LocacaoHospede;
import hotel.repository.LocacaoConsumivelRepository;
import hotel.repository.LocacaoHospedeRepository;
import java.util.List;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class LocacaoConsumivelController extends BaseController<LocacaoConsumivel>{

    public LocacaoConsumivel getReadId(int pCodigo) {
        try {
            return LocacaoConsumivelRepository.readId(pCodigo);
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }

    public void popularTabela(JTable pTabela, int pOption, String pParam) {
        try {
            Object[][] lTabela = null;

            Object[] lTabelaTitulo = new Object[5];
            lTabelaTitulo[0] = "Código";
            lTabelaTitulo[1] = "Cliente";
            lTabelaTitulo[2] = "Consumível";
            lTabelaTitulo[3] = "Quantidade";
            lTabelaTitulo[4] = "Valor";

            int lLinha = 0;
            switch (pOption) {
                case 0: {
                    List<LocacaoConsumivel> listLocacaoConsumivel = LocacaoConsumivelRepository.readAll();
                    lTabela = new Object[listLocacaoConsumivel.size()][5];
                    for (LocacaoConsumivel lc : listLocacaoConsumivel) {
                        
                        LocacaoHospede lh = LocacaoHospedeRepository.readResponsavel(lc.getLocacao().getCodLocacao());
                        lTabela[lLinha][0] = lc.getCodLocacaoConsumivel();
                        lTabela[lLinha][1] = lh.getPessoa().getNomPessoa();
                        lTabela[lLinha][2] = lc.getConsumivel().getNomConsumivel();
                        lTabela[lLinha][3] = lc.getQtdConsumivel();
                        lTabela[lLinha][4] = lc.getVlrConsumivel();
                        lLinha++;
                    }
                    break;
                }
                case 1: {
                    List<LocacaoConsumivel> listLocacaoConsumivel = LocacaoConsumivelRepository.readLocacaoId(Integer.parseInt(pParam));
                    lTabela = new Object[listLocacaoConsumivel.size()][5];
                    for (LocacaoConsumivel lc : listLocacaoConsumivel) {
                        
                        LocacaoHospede lh = LocacaoHospedeRepository.readResponsavel(lc.getLocacao().getCodLocacao());
                        lTabela[lLinha][0] = lc.getCodLocacaoConsumivel();
                        lTabela[lLinha][1] = lh.getPessoa().getNomPessoa();
                        lTabela[lLinha][2] = lc.getConsumivel().getNomConsumivel();
                        lTabela[lLinha][3] = lc.getQtdConsumivel();
                        lTabela[lLinha][4] = lc.getVlrConsumivel();
                        lLinha++;
                    }
                    break;
                }
                default:
                    List<LocacaoConsumivel> listLocacaoConsumivel = LocacaoConsumivelRepository.readAll();
                    lTabela = new Object[listLocacaoConsumivel.size()][5];
                    for (LocacaoConsumivel lc : listLocacaoConsumivel) {
                        
                        LocacaoHospede lh = LocacaoHospedeRepository.readResponsavel(lc.getLocacao().getCodLocacao());
                        lTabela[lLinha][0] = lc.getCodLocacaoConsumivel();
                        lTabela[lLinha][1] = lh.getPessoa().getNomPessoa();
                        lTabela[lLinha][2] = lc.getConsumivel().getNomConsumivel();
                        lTabela[lLinha][3] = lc.getQtdConsumivel();
                        lTabela[lLinha][4] = lc.getVlrConsumivel();
                        lLinha++;
                    }
                    break;
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
                        lColumn.setPreferredWidth(30);
                        lColumn.setCellRenderer(lRight);
                        break;
                    case 1:
                        lColumn.setPreferredWidth(120);
                        lColumn.setCellRenderer(lLeft);
                        break;
                    case 2:
                        lColumn.setPreferredWidth(230);
                        lColumn.setCellRenderer(lLeft);
                        break;
                    case 3:
                        lColumn.setPreferredWidth(45);
                        lColumn.setCellRenderer(lCenter);
                        break;
                    case 4:
                        lColumn.setPreferredWidth(90);
                        lColumn.setCellRenderer(lCenter);
                        break;
                    case 5:
                        lColumn.setPreferredWidth(60);
                        lColumn.setCellRenderer(lCenter);
                        break;
                }
            }
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
    }
}
