package hotel.controller;

import hotel.model.Consumivel;
import hotel.repository.ConsumivelRepository;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class ConsumivelController extends BaseController<Consumivel> {

    public String changeSituation(int pCodigo) {
        try {
            Consumivel consumivel = ConsumivelRepository.readId(pCodigo);
            consumivel.setIndSituacao("E");
            save(consumivel);
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }

    public Consumivel getReadId(int pCodigo) {
        try {
            return ConsumivelRepository.readId(pCodigo);
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }

    public void popularTabela(JTable pTabela, int pOption, String pParam) {
        try {
            Object[][] lTabela = null;

            Object[] lTabelaTitulo = new Object[6];
            lTabelaTitulo[0] = "Código";
            lTabelaTitulo[1] = "Nome";
            lTabelaTitulo[2] = "Descrição";
            lTabelaTitulo[3] = "Valor";
            lTabelaTitulo[4] = "Tipo";
            lTabelaTitulo[5] = "Situação";

            int lLinha = 0;
            switch (pOption) {
                case 0: {
                    List<Consumivel> listConsumivel = ConsumivelRepository.readAll();
                    lTabela = new Object[listConsumivel.size()][6];
                    for (Consumivel c : listConsumivel) {
                        String situacao = (c.getIndSituacao().equals("A") ? "Ativo" : "Excluído");

                        lTabela[lLinha][0] = c.getCodConsumivel();
                        lTabela[lLinha][1] = c.getNomConsumivel();
                        lTabela[lLinha][2] = c.getDesConsumivel();
                        lTabela[lLinha][3] = c.getVlrConsumivel();
                        lTabela[lLinha][4] = c.getTipConsumivel();
                        lTabela[lLinha][5] = situacao;
                        lLinha++;
                    }
                    break;
                }
                case 1: {
                    List<Consumivel> listConsumivel = ConsumivelRepository.read(pParam);
                    lTabela = new Object[listConsumivel.size()][6];
                    for (Consumivel c : listConsumivel) {
                        String situacao = (c.getIndSituacao().equals("A") ? "Ativo" : "Excluído");

                        lTabela[lLinha][0] = c.getCodConsumivel();
                        lTabela[lLinha][1] = c.getNomConsumivel();
                        lTabela[lLinha][2] = c.getDesConsumivel();
                        lTabela[lLinha][3] = c.getVlrConsumivel();
                        lTabela[lLinha][4] = c.getTipConsumivel();
                        lTabela[lLinha][5] = situacao;
                        lLinha++;
                    }
                    break;
                }
                default:
                    Consumivel consumivel = ConsumivelRepository.readId(Integer.parseInt(pParam));
                    if (consumivel == null) {
                        JOptionPane.showMessageDialog(null, "Consumível não encontrado pelo cóodigo: " + pParam);
                    } else {
                        String situacao = (consumivel.getIndSituacao().equals("A") ? "Ativo" : "Excluído");

                        lTabela = new Object[1][6];
                        lTabela[lLinha][0] = consumivel.getCodConsumivel();
                        lTabela[lLinha][1] = consumivel.getNomConsumivel();
                        lTabela[lLinha][2] = consumivel.getDesConsumivel();
                        lTabela[lLinha][3] = consumivel.getVlrConsumivel();
                        lTabela[lLinha][4] = consumivel.getTipConsumivel();
                        lTabela[lLinha][5] = situacao;
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
            for (int i = 0; i < pTabela.getColumnCount(); i++) {
                lColumn = pTabela.getColumnModel().getColumn(i);
                switch (i) {
                    case 0:
                        lColumn.setPreferredWidth(17);
                        break;
                    case 1:
                        lColumn.setPreferredWidth(140);
                        break;
                }
            }
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
    }
}
