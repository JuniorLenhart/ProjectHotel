package hotel.controller;

import hotel.model.LocacaoConsumivel;
import hotel.model.LocacaoHospede;
import hotel.repository.LocacaoConsumivelRepository;
import hotel.repository.LocacaoHospedeRepository;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class LocacaoConsumivelController extends BaseController<LocacaoConsumivel> {

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
                case 2: {
                    String[] split = pParam.split(";");
                    List<LocacaoConsumivel> listLocacaoConsumivel = LocacaoConsumivelRepository.readLocacaoIdAndPessoa(Integer.parseInt(split[0]), split[1]);
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
                case 3: {
                    LocacaoConsumivel locacaoConsumivel = LocacaoConsumivelRepository.readId(Integer.parseInt(pParam));
                    if (locacaoConsumivel == null) {
                        JOptionPane.showMessageDialog(null, "Consumivel não encontrado pelo código: " + pParam);
                    } else {
                        lTabela = new Object[1][5];
                        LocacaoHospede lh = LocacaoHospedeRepository.readResponsavel(locacaoConsumivel.getLocacao().getCodLocacao());

                        lTabela[lLinha][0] = locacaoConsumivel.getCodLocacaoConsumivel();
                        lTabela[lLinha][1] = lh.getPessoa().getNomPessoa();
                        lTabela[lLinha][2] = locacaoConsumivel.getConsumivel().getNomConsumivel();
                        lTabela[lLinha][3] = locacaoConsumivel.getQtdConsumivel();
                        lTabela[lLinha][4] = locacaoConsumivel.getVlrConsumivel();
                        lLinha++;
                    }

                    break;
                }
                case 4: {
                    lTabelaTitulo = new Object[4];
                    lTabelaTitulo[0] = "Consumível";
                    lTabelaTitulo[1] = "Valor Unitário";
                    lTabelaTitulo[2] = "Quantidade";
                    lTabelaTitulo[3] = "Valor Total";

                    List<LocacaoConsumivel> listLocacaoConsumivel = LocacaoConsumivelRepository.readLocacaoIdWithSUM(Integer.parseInt(pParam));
                    lTabela = new Object[listLocacaoConsumivel.size()][4];
                    for (LocacaoConsumivel lc : listLocacaoConsumivel) {
                        lTabela[lLinha][0] = lc.getConsumivel().getNomConsumivel();
                        lTabela[lLinha][1] = lc.getConsumivel().getVlrConsumivel();
                        lTabela[lLinha][2] = lc.getQtdConsumivel();
                        lTabela[lLinha][3] = lc.getVlrConsumivel();
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
                if (pOption == 4) {
                    switch (i) {
                        case 0:
                            lColumn.setPreferredWidth(100);
                            lColumn.setCellRenderer(lLeft);
                            break;
                        case 1:
                            lColumn.setPreferredWidth(50);
                            lColumn.setCellRenderer(lCenter);
                            break;
                        case 2:
                            lColumn.setPreferredWidth(50);
                            lColumn.setCellRenderer(lCenter);
                            break;
                        case 3:
                            lColumn.setPreferredWidth(50);
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
                } else {
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
            }
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
    }
}
