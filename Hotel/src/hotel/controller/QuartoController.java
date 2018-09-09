package hotel.controller;

import hotel.model.Quarto;
import hotel.model.TipoCama;
import hotel.repository.QuartoRepository;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class QuartoController extends BaseController<Quarto> {

    public String changeSituacao(int pCodigo) {
        try {
            Quarto quarto = QuartoRepository.readId(pCodigo);
            quarto.setIndSituacao("E");
            save(quarto);
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }

    public Quarto getReadId(int pCodigo) {
        try {
            return QuartoRepository.readId(pCodigo);
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
            lTabelaTitulo[1] = "Número Quarto";
            lTabelaTitulo[2] = "Valor";
            lTabelaTitulo[3] = "Tipo(s) de Cama(s)";
            lTabelaTitulo[4] = "Situação";

            int lLinha = 0;
            switch (pOption) {
                case 0: {
                    List<Quarto> listQuarto = QuartoRepository.readAll();
                    lTabela = new Object[listQuarto.size()][6];
                    for (Quarto q : listQuarto) {
                        String situacao = (q.getIndSituacao().equals("A") ? "Ativo" : "Excluído");

                        lTabela[lLinha][0] = q.getCodQuarto();
                        lTabela[lLinha][1] = q.getNumQuarto();
                        lTabela[lLinha][2] = q.getVlrQuarto();
                        for (TipoCama tc : q.getTipoCama()) {
                            lTabela[lLinha][3] += tc.getDesTipoCama() + " - Lugares: " + tc.getQtdLugarTipoCama() + " / ";
                        }
                        lTabela[lLinha][4] = situacao;
                        lLinha++;
                    }
                    break;
                }
                case 1: {
                    List<Quarto> listQuarto = QuartoRepository.read(pParam);
                    lTabela = new Object[listQuarto.size()][6];
                    for (Quarto q : listQuarto) {
                        String situacao = (q.getIndSituacao().equals("A") ? "Ativo" : "Excluído");

                        lTabela[lLinha][0] = q.getCodQuarto();
                        lTabela[lLinha][1] = q.getNumQuarto();
                        lTabela[lLinha][2] = q.getVlrQuarto();
                        for (TipoCama tc : q.getTipoCama()) {
                            lTabela[lLinha][3] += tc.getDesTipoCama() + " - Lugares: " + tc.getQtdLugarTipoCama() + " / ";
                        }
                        lTabela[lLinha][4] = situacao;
                        lLinha++;
                    }
                    break;
                }
                default:
                    Quarto quarto = QuartoRepository.readId(Integer.parseInt(pParam));
                    if (quarto == null) {
                        JOptionPane.showMessageDialog(null, "Quarto não encontrado pelo código " + pParam);
                    } else {
                        String situacao = (quarto.getIndSituacao().equals("A") ? "Ativo" : "Excluído");

                        lTabela = new Object[1][4];
                        lTabela[lLinha][0] = quarto.getCodQuarto();
                        lTabela[lLinha][1] = quarto.getNumQuarto();
                        lTabela[lLinha][2] = quarto.getVlrQuarto();
                        for (TipoCama tc : quarto.getTipoCama()) {
                            lTabela[lLinha][3] += tc.getDesTipoCama() + " - Lugares: " + tc.getQtdLugarTipoCama() + " / ";
                        }
                        lTabela[lLinha][4] = situacao;
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
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
    }

    public void popularPesquisaQuarto(JTable pTabela, String pDtaEntrada, String pDtaSaida, int pQtdLugar, int pOption, String pParam) {
        try {
            List<Quarto> listQuarto = QuartoRepository.selectQuartos(pDtaEntrada, pDtaSaida, pQtdLugar);

            Object[][] lTabela = null;

            Object[] lTabelaTitulo = new Object[5];
            lTabelaTitulo[0] = "Código";
            lTabelaTitulo[1] = "Número Quarto";
            lTabelaTitulo[2] = "Valor";
            lTabelaTitulo[3] = "Tipo(s) de Cama(s)";
            lTabelaTitulo[4] = "Situação";

            List<Quarto> listAuxQuarto = new ArrayList();
            switch (pOption) {
                case 0: {
                    listAuxQuarto.addAll(listQuarto);
                    break;
                }
                case 1: {
                    for (Quarto q : listQuarto) {
                        if (q.getNumQuarto().contains(pParam)) {
                            listAuxQuarto.add(q);
                        }
                    }
                    break;
                }
                default: {
                    for (Quarto q : listQuarto) {
                        if (q.getCodQuarto() == Integer.parseInt(pParam)) {
                            listAuxQuarto.add(q);
                        }
                    }
                    break;
                }
            }

            int lLinha = 0;
            lTabela = new Object[listAuxQuarto.size()][6];
            for (Quarto q : listAuxQuarto) {
                String situacao = (q.getIndSituacao().equals("A") ? "Ativo" : "Excluído");

                lTabela[lLinha][0] = q.getCodQuarto();
                lTabela[lLinha][1] = q.getNumQuarto();
                lTabela[lLinha][2] = q.getVlrQuarto();
                for (TipoCama tc : q.getTipoCama()) {
                    lTabela[lLinha][3] += tc.getDesTipoCama() + " - Lugares: " + tc.getQtdLugarTipoCama() + " / ";
                }
                lTabela[lLinha][4] = situacao;
                lLinha++;
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
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
    }
}
