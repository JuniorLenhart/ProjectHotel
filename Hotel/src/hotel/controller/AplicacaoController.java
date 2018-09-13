package hotel.controller;

import hotel.model.Aplicacao;
import hotel.repository.AplicacaoRepository;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class AplicacaoController extends BaseController<Aplicacao>{
    
    public String changeSituation(int pCodigo) {
        try {
            Aplicacao aplicacao = AplicacaoRepository.readId(pCodigo);
            aplicacao.setIndSituacao("E");
            save(aplicacao);
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }

    public Aplicacao getReadId(int pCodigo) {
        try {
            return AplicacaoRepository.readId(pCodigo);
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }
    
    public List<Aplicacao> getReadAllAtivos() {
        try {
            return AplicacaoRepository.readAllAtivos();
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
            lTabelaTitulo[1] = "Nome";
            lTabelaTitulo[2] = "Nome arquivo";
            lTabelaTitulo[3] = "Situação";
            int lLinha = 0;
            switch (pOption) {
                case 0: {
                    List<Aplicacao> listAplicacao = AplicacaoRepository.readAll();
                    lTabela = new Object[listAplicacao.size()][4];
                    for (Aplicacao a : listAplicacao) {
                        String situacao = (a.getIndSituacao().equals("A") ? "Ativo" : "Excluído");

                        lTabela[lLinha][0] = a.getCodAplicacao();
                        lTabela[lLinha][1] = a.getNomAplicacao();
                        lTabela[lLinha][2] = a.getNomArquivoJava();
                        lTabela[lLinha][3] = situacao;
                        lLinha++;
                    }
                    break;
                }
                case 1: {
                    List<Aplicacao> listAplicaco = AplicacaoRepository.read(pParam);
                    lTabela = new Object[listAplicaco.size()][4];
                    for (Aplicacao a : listAplicaco) {
                        String situacao = (a.getIndSituacao().equals("A") ? "Ativo" : "Excluído");

                        lTabela[lLinha][0] = a.getCodAplicacao();
                        lTabela[lLinha][1] = a.getNomAplicacao();
                        lTabela[lLinha][2] = a.getNomArquivoJava();
                        lTabela[lLinha][3] = situacao;
                        lLinha++;
                    }
                    break;
                }
                default:
                    Aplicacao aplicacao = AplicacaoRepository.readId(Integer.parseInt(pParam));
                    if (aplicacao == null) {
                        JOptionPane.showMessageDialog(null, "Consumível não encontrado pelo cóodigo: " + pParam);
                    } else {
                        String situacao = (aplicacao.getIndSituacao().equals("A") ? "Ativo" : "Excluído");

                        lTabela = new Object[1][4];
                        
                        lTabela[lLinha][0] = aplicacao.getCodAplicacao();
                        lTabela[lLinha][1] = aplicacao.getNomAplicacao();
                        lTabela[lLinha][2] = aplicacao.getNomArquivoJava();
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
                        lColumn.setPreferredWidth(200);
                        lColumn.setCellRenderer(lLeft);
                        break;
                    case 3:
                        lColumn.setPreferredWidth(45);
                        lColumn.setCellRenderer(lCenter);
                        break;
                }
            }
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
    }
}
