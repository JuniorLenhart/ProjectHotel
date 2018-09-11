package hotel.controller;

import hotel.model.TipoCama;
import hotel.repository.TipoCamaRepository;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class TipoCamaController extends BaseController<TipoCama> {

    public String changeSituation(int pCodigo) {
        try {
            TipoCama tipoCama = TipoCamaRepository.readId(pCodigo);
            tipoCama.setIndSituacao("E");
            save(tipoCama);
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }

    public List<TipoCama> getReadAll() {
        try {
            return TipoCamaRepository.readAll();
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }
    
    public List<TipoCama> getReadAllAtivos() {
        try {
            return TipoCamaRepository.readAllAtivos();
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }

    public TipoCama getReadId(int pCodigo) {
        try {
            return TipoCamaRepository.readId(pCodigo);
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
            lTabelaTitulo[1] = "Descrição";
            lTabelaTitulo[2] = "Lugar";
            lTabelaTitulo[3] = "Situação";

            int lLinha = 0;
            switch (pOption) {
                case 0: {
                    List<TipoCama> listTipoCama = TipoCamaRepository.readAll();
                    lTabela = new Object[listTipoCama.size()][4];
                    for (TipoCama tc : listTipoCama) {
                        String situacao = (tc.getIndSituacao().equals("A") ? "Ativo" : "Excluído");

                        lTabela[lLinha][0] = tc.getCodTipoCama();
                        lTabela[lLinha][1] = tc.getDesTipoCama();
                        lTabela[lLinha][2] = tc.getQtdLugarTipoCama();
                        lTabela[lLinha][3] = situacao;
                        lLinha++;
                    }
                    break;
                }
                case 1: {
                    List<TipoCama> listTipoCama = TipoCamaRepository.read(pParam);
                    lTabela = new Object[listTipoCama.size()][4];
                    for (TipoCama tc : listTipoCama) {
                        String situacao = (tc.getIndSituacao().equals("A") ? "Ativo" : "Excluído");

                        lTabela[lLinha][0] = tc.getCodTipoCama();
                        lTabela[lLinha][1] = tc.getDesTipoCama();
                        lTabela[lLinha][2] = tc.getQtdLugarTipoCama();
                        lTabela[lLinha][3] = situacao;
                        lLinha++;
                    }
                    break;
                }
                default:
                    TipoCama tipoCama = TipoCamaRepository.readId(Integer.parseInt(pParam));
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
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
    }
}
