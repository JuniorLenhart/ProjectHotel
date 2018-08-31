package hotel.controller;

import hotel.model.FormaPagamento;
import hotel.repository.FormaPagamentoRepository;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class FormaPagamentoController extends BaseController<FormaPagamento> {

    public String changeSituation(int pCodigo) {
        try {
            FormaPagamento formaPgto = FormaPagamentoRepository.readId(pCodigo);
            formaPgto.setIndSituacao("E");
            save(formaPgto);
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }

    public FormaPagamento getReadId(int pCodigo) {
        try {
            return FormaPagamentoRepository.readId(pCodigo);
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }

    public void popularTabela(JTable pTabela, int pOption, String pParam) {
        try {
            Object[][] lTabela = null;

            Object[] lTabelaTitulo = new Object[3];
            lTabelaTitulo[0] = "Código";
            lTabelaTitulo[1] = "Nome";
            lTabelaTitulo[2] = "Situação";

            int lLinha = 0;
            switch (pOption) {
                case 0: {
                    List<FormaPagamento> listFormaPagamento = FormaPagamentoRepository.readAll();
                    lTabela = new Object[listFormaPagamento.size()][3];
                    for (FormaPagamento fp : listFormaPagamento) {
                        String situacao = (fp.getIndSituacao().equals("A") ? "Ativo" : "Excluído");

                        lTabela[lLinha][0] = fp.getCodFormaPgto();
                        lTabela[lLinha][1] = fp.getDesFormaPgto();
                        lTabela[lLinha][2] = situacao;
                        lLinha++;
                    }
                    break;
                }
                case 1: {
                    List<FormaPagamento> listFormaPagamento = FormaPagamentoRepository.read(pParam);
                    lTabela = new Object[listFormaPagamento.size()][3];
                    for (FormaPagamento fp : listFormaPagamento) {
                        String situacao = (fp.getIndSituacao().equals("A") ? "Ativo" : "Excluído");

                        lTabela[lLinha][0] = fp.getCodFormaPgto();
                        lTabela[lLinha][1] = fp.getDesFormaPgto();
                        lTabela[lLinha][2] = situacao;
                        lLinha++;
                    }
                    break;
                }
                default:
                    FormaPagamento formaPagamento = FormaPagamentoRepository.readId(Integer.parseInt(pParam));
                    if (formaPagamento == null) {
                        JOptionPane.showMessageDialog(null, "Forma de Pagamento não encontrado pelo código: " + pParam);
                    } else {
                        String situacao = (formaPagamento.getIndSituacao().equals("A") ? "Ativo" : "Excluído");

                        lTabela = new Object[1][3];
                        lTabela[lLinha][0] = formaPagamento.getCodFormaPgto();
                        lTabela[lLinha][1] = formaPagamento.getDesFormaPgto();
                        lTabela[lLinha][2] = situacao;
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
