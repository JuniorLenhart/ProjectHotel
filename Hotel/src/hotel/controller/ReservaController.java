package hotel.controller;

import hotel.config.HibernateUtil;
import hotel.model.Reserva;
import hotel.repository.ReservaRepository;
import hotel.support.Formatacao;
import java.util.List;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.hibernate.Transaction;

public class ReservaController extends BaseController<Reserva> {

    public String cancelReserve(int pCodigo) {
        try {
            Reserva reserva = ReservaRepository.readId(pCodigo);
            reserva.setIndSituacao("I");
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

    public void refreshReserve() {
        try {
            Transaction transaction = HibernateUtil.getBeginTransaction();

            ReservaRepository.refreshReserve();

            transaction.commit();
        } catch (Exception ex) {
            HibernateUtil.closeSession();
            LoggerController.log(this.getClass(), ex);
        }
    }

    public void popularTabela(JTable pTabela, int pOption, String pParam) {
        try {
            Object[][] lTabela = null;

            Object[] lTabelaTitulo = new Object[9];
            lTabelaTitulo[0] = "Código";
            lTabelaTitulo[1] = "CPF";
            lTabelaTitulo[2] = "Nº Quarto";
            lTabelaTitulo[3] = "Data Reserva";
            lTabelaTitulo[4] = "Data Entrada";
            lTabelaTitulo[5] = "Data Saída";
            lTabelaTitulo[6] = "Lugares";
            lTabelaTitulo[7] = "Valor";
            lTabelaTitulo[8] = "Situação";

            List<Reserva> listReserva = null;
            Reserva reserva = null;

            switch (pOption) {
                case 0:
                    listReserva = ReservaRepository.readAll();
                    break;
                case 1:
                    listReserva = ReservaRepository.readPersonNamePayed(pParam);
                    break;
                case 2:
                    listReserva = ReservaRepository.readPersonCPFPayed(pParam);
                    break;
                case 3:
                    listReserva = ReservaRepository.readAllPayed();
                    break;
                case 4:
                    listReserva = ReservaRepository.readPersonCPF(pParam);
                    break;
                case 5:
                    reserva = ReservaRepository.readId(Integer.parseInt(pParam));
                    break;
                case 6:
                    listReserva = ReservaRepository.readNumberRoom(pParam);
                    break;
            }

            int lLinha = 0;
            switch (pOption) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 6: {
                    lTabela = new Object[listReserva.size()][9];
                    for (Reserva r : listReserva) {
                        String situacao = (r.getIndSituacao().equals("E") ? "Efetuada" : (r.getIndSituacao().equals("C") ? "Confirmada" : "Cancelada"));

                        lTabela[lLinha][0] = r.getCodReserva();
                        lTabela[lLinha][1] = Formatacao.formatarCPF(r.getPessoa().getNumCpf());
                        lTabela[lLinha][2] = r.getQuarto().getNumQuarto();
                        lTabela[lLinha][3] = Formatacao.ajustaDataDMAHS(r.getDtaReserva().toString());
                        lTabela[lLinha][4] = Formatacao.ajustaDataDMA(r.getDtaEntrada().toString());
                        lTabela[lLinha][5] = Formatacao.ajustaDataDMA(r.getDtaSaida().toString());
                        lTabela[lLinha][6] = r.getQtdLugar();
                        lTabela[lLinha][7] = r.getVlrReserva();
                        lTabela[lLinha][8] = situacao;
                        lLinha++;
                    }
                    break;
                }
                case 5: {
                    if (reserva != null) {
                        String situacao = (reserva.getIndSituacao().equals("E") ? "Efetuada" : (reserva.getIndSituacao().equals("C") ? "Confirmada" : "Cancelada"));

                        lTabela = new Object[1][9];
                        lTabela[lLinha][0] = reserva.getCodReserva();
                        lTabela[lLinha][1] = Formatacao.formatarCPF(reserva.getPessoa().getNumCpf());
                        lTabela[lLinha][2] = reserva.getQuarto().getNumQuarto();
                        lTabela[lLinha][3] = Formatacao.ajustaDataDMAHS(reserva.getDtaReserva().toString());
                        lTabela[lLinha][4] = Formatacao.ajustaDataDMA(reserva.getDtaEntrada().toString());
                        lTabela[lLinha][5] = Formatacao.ajustaDataDMA(reserva.getDtaSaida().toString());
                        lTabela[lLinha][6] = reserva.getQtdLugar();
                        lTabela[lLinha][7] = reserva.getVlrReserva();
                        lTabela[lLinha][8] = situacao;
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
                        lColumn.setPreferredWidth(75);
                        lColumn.setCellRenderer(lLeft);
                        break;
                    case 2:
                        lColumn.setPreferredWidth(40);
                        lColumn.setCellRenderer(lLeft);
                        break;
                    case 3:
                        lColumn.setPreferredWidth(90);
                        lColumn.setCellRenderer(lCenter);
                        break;
                    case 4:
                        lColumn.setPreferredWidth(50);
                        lColumn.setCellRenderer(lCenter);
                        break;
                    case 5:
                        lColumn.setPreferredWidth(50);
                        lColumn.setCellRenderer(lCenter);
                        break;
                    case 6:
                        lColumn.setPreferredWidth(30);
                        lColumn.setCellRenderer(lCenter);
                        break;
                    case 7:
                        lColumn.setPreferredWidth(30);
                        lColumn.setCellRenderer(lRight);
                        break;
                    case 8:
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
