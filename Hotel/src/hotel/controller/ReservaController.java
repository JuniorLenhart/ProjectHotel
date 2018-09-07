package hotel.controller;

import hotel.model.Reserva;
import hotel.repository.ReservaRepository;
import hotel.support.Formatacao;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class ReservaController extends BaseController<Reserva> {

    public String changeSituation(int pCodigo) {
        try {
            Reserva reserva = ReservaRepository.readId(pCodigo);
            reserva.setIndSituacao("E");
            save(reserva);
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }

    public Reserva getReadId(int pCodigo) {
        try {
            return ReservaRepository.readId(pCodigo);
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }

    public void popularTabela(JTable pTabela, int pOption, String pParam) {
        try {
            Object[][] lTabela = null;

            Object[] lTabelaTitulo = new Object[9];
            lTabelaTitulo[0] = "Código";
            lTabelaTitulo[1] = "Nome";
            lTabelaTitulo[2] = "Quarto";
            lTabelaTitulo[3] = "Data Reserva";
            lTabelaTitulo[4] = "Data Entrada";
            lTabelaTitulo[5] = "Data Saída";
            lTabelaTitulo[6] = "Lugares";
            lTabelaTitulo[7] = "Valor";
            lTabelaTitulo[8] = "Situação";

            int lLinha = 0;
            switch (pOption) {
                case 0: {
                    List<Reserva> listConsumivel = ReservaRepository.readAll();
                    lTabela = new Object[listConsumivel.size()][9];
                    for (Reserva r : listConsumivel) {
                        String situacao = (r.getIndSituacao().equals("E") ? "Efetuada" : (r.getIndSituacao().equals("C") ? "Confirmada" : "Cancelada"));
 
                        lTabela[lLinha][0] = r.getCodReserva();
                        lTabela[lLinha][1] = r.getCodPessoa().getNomPessoa();
                        lTabela[lLinha][2] = r.getCodQuarto().getNumQuarto();
                        lTabela[lLinha][3] = Formatacao.ajustaDataDMA(r.getDtaReserva().toString());
                        lTabela[lLinha][4] = Formatacao.ajustaDataDMA(r.getDtaEntrada().toString());
                        lTabela[lLinha][5] = Formatacao.ajustaDataDMA(r.getDtaSaida().toString());
                        lTabela[lLinha][6] = r.getQtdLugar();
                        lTabela[lLinha][7] = r.getVlrReserva();
                        lTabela[lLinha][8] = situacao;
                        lLinha++;
                    }
                    break;
                }
                case 1: {
                    List<Reserva> listConsumivel = ReservaRepository.readPerPersonNamePayed(pParam);
                    lTabela = new Object[listConsumivel.size()][6];
                    for (Reserva r : listConsumivel) {
                        String situacao = (r.getIndSituacao().equals("E") ? "Efetuada" : (r.getIndSituacao().equals("C") ? "Confirmada" : "Cancelada"));

                        lTabela[lLinha][0] = r.getCodReserva();
                        lTabela[lLinha][1] = r.getCodPessoa().getNomPessoa();
                        lTabela[lLinha][2] = r.getCodQuarto().getNumQuarto();
                        lTabela[lLinha][3] = Formatacao.ajustaDataDMA(r.getDtaReserva().toString());
                        lTabela[lLinha][4] = Formatacao.ajustaDataDMA(r.getDtaEntrada().toString());
                        lTabela[lLinha][5] = Formatacao.ajustaDataDMA(r.getDtaSaida().toString());
                        lTabela[lLinha][6] = r.getQtdLugar();
                        lTabela[lLinha][7] = r.getVlrReserva();
                        lTabela[lLinha][8] = situacao;
                        lLinha++;
                    }
                    break;
                }
                case 2: {
                    Reserva reserva = ReservaRepository.readPerPersonCPFPayed(pParam);
                    if (reserva == null) {
                        JOptionPane.showMessageDialog(null, "Reserva não encontrada pelo CPF: " + pParam);
                    } else {
                        String situacao = (reserva.getIndSituacao().equals("E") ? "Efetuada" : (reserva.getIndSituacao().equals("C") ? "Confirmada" : "Cancelada"));

                        lTabela = new Object[1][9];
                        lTabela[lLinha][0] = reserva.getCodReserva();
                        lTabela[lLinha][1] = reserva.getCodPessoa().getNomPessoa();
                        lTabela[lLinha][2] = reserva.getCodQuarto().getNumQuarto();
                        lTabela[lLinha][3] = Formatacao.ajustaDataDMA(reserva.getDtaReserva().toString());
                        lTabela[lLinha][4] = Formatacao.ajustaDataDMA(reserva.getDtaEntrada().toString());
                        lTabela[lLinha][5] = Formatacao.ajustaDataDMA(reserva.getDtaSaida().toString());
                        lTabela[lLinha][6] = reserva.getQtdLugar();
                        lTabela[lLinha][7] = reserva.getVlrReserva();
                        lTabela[lLinha][8] = situacao;
                        lLinha++;
                    }
                    break;
                }
                case 3: {
                    List<Reserva> listConsumivel = ReservaRepository.readAllPayed();
                    lTabela = new Object[listConsumivel.size()][9];
                    for (Reserva r : listConsumivel) {
                        String situacao = (r.getIndSituacao().equals("E") ? "Efetuada" : (r.getIndSituacao().equals("C") ? "Confirmada" : "Cancelada"));

                        lTabela[lLinha][0] = r.getCodReserva();
                        lTabela[lLinha][1] = r.getCodPessoa().getNomPessoa();
                        lTabela[lLinha][2] = r.getCodQuarto().getNumQuarto();
                        lTabela[lLinha][3] = Formatacao.ajustaDataDMA(r.getDtaReserva().toString());
                        lTabela[lLinha][4] = Formatacao.ajustaDataDMA(r.getDtaEntrada().toString());
                        lTabela[lLinha][5] = Formatacao.ajustaDataDMA(r.getDtaSaida().toString());
                        lTabela[lLinha][6] = r.getQtdLugar();
                        lTabela[lLinha][7] = r.getVlrReserva();
                        lTabela[lLinha][8] = situacao;
                        lLinha++;
                    }
                    break;
                }
                default:
                    List<Reserva> listConsumivel = ReservaRepository.readAll();
                    lTabela = new Object[listConsumivel.size()][9];
                    for (Reserva r : listConsumivel) {
                        String situacao = (r.getIndSituacao().equals("E") ? "Efetuada" : (r.getIndSituacao().equals("C") ? "Confirmada" : "Cancelada"));

                        lTabela[lLinha][0] = r.getCodReserva();
                        lTabela[lLinha][1] = r.getCodPessoa().getNomPessoa();
                        lTabela[lLinha][2] = r.getCodQuarto().getNumQuarto();
                        lTabela[lLinha][3] = Formatacao.ajustaDataDMA(r.getDtaReserva().toString());
                        lTabela[lLinha][4] = Formatacao.ajustaDataDMA(r.getDtaEntrada().toString());
                        lTabela[lLinha][5] = Formatacao.ajustaDataDMA(r.getDtaSaida().toString());
                        lTabela[lLinha][6] = r.getQtdLugar();
                        lTabela[lLinha][7] = r.getVlrReserva();
                        lTabela[lLinha][8] = situacao;
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
