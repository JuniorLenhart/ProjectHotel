package hotel.controller;

import hotel.model.Locacao;
import hotel.model.LocacaoHospede;
import hotel.repository.LocacaoHospedeRepository;
import hotel.repository.LocacaoRepository;
import hotel.support.Formatacao;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class LocacaoController extends BaseController<Locacao> {

    public String changeSituation(int pCodigo) {
        try {
            Locacao locacoa = LocacaoRepository.readId(pCodigo);
            locacoa.setIndSituacao("E");
            save(locacoa);
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }

    public Locacao getReadId(int pCodigo) {
        try {
            return LocacaoRepository.readId(pCodigo);
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
            lTabelaTitulo[1] = "Quarto";
            lTabelaTitulo[2] = "Nome titular";
            lTabelaTitulo[3] = "Data locação";
            lTabelaTitulo[4] = "Valor";
            lTabelaTitulo[5] = "Situação";

            int lLinha = 0;
            switch (pOption) {
                case 0: {
                    List<Locacao> listLocacao = LocacaoRepository.readAll();
                    lTabela = new Object[listLocacao.size()][6];
                    for (Locacao l : listLocacao) {
                        String situacao = (l.getIndSituacao().equals("F") ? "Finalizada" : (l.getIndSituacao().equals("C") ? "Cancelada" : "Em aberto"));

                        lTabela[lLinha][0] = l.getCodLocacao();
                        lTabela[lLinha][1] = l.getCodQuarto().getNumQuarto();
                        for (LocacaoHospede lh : LocacaoHospedeRepository.readLocacaoId(l.getCodLocacao())) {
                            if (lh.getIndResponsavel().equals("S")) {
                                lTabela[lLinha][2] = lh.getCodPessoa().getNomPessoa();
                            }
                        }
                        lTabela[lLinha][3] = Formatacao.ajustaDataDMAHS(l.getDtaLocacao().toString());
                        lTabela[lLinha][4] = l.getVlrLocacao();
                        lTabela[lLinha][5] = situacao;
                        lLinha++;
                    }
                    break;
                }
                case 1: {
                    String dates[] = pParam.split(";");
                    List<Locacao> listLocacao = LocacaoRepository.readBetweenDates(dates[0],dates[1]);
                    lTabela = new Object[listLocacao.size()][7];
                    for (Locacao l : listLocacao) {
                        String situacao = (l.getIndSituacao().equals("F") ? "Finalizada" : (l.getIndSituacao().equals("C") ? "Cancelada" : "Em aberto"));

                        lTabela[lLinha][0] = l.getCodLocacao();
                        lTabela[lLinha][1] = l.getCodQuarto().getNumQuarto();
                        for (LocacaoHospede lh : LocacaoHospedeRepository.readLocacaoId(l.getCodLocacao())) {
                            if (lh.getIndResponsavel().equals("S")) {
                                lTabela[lLinha][2] = lh.getCodPessoa().getNomPessoa();
                            }
                        }
                        lTabela[lLinha][3] = Formatacao.ajustaDataDMAHS(l.getDtaLocacao().toString());
                        lTabela[lLinha][4] = l.getVlrLocacao();
                        lTabela[lLinha][5] = situacao;
                        lLinha++;
                    }
                    break;
                }
                case 2: {
                    Locacao l = LocacaoRepository.readId(Integer.parseInt(pParam));
                    if (l == null) {
                        JOptionPane.showMessageDialog(null, "Pessoa não encontrado pelo código: " + pParam);
                    } else {
                        String situacao = (l.getIndSituacao().equals("F") ? "Finalizada" : (l.getIndSituacao().equals("C") ? "Cancelada" : "Em aberto"));
                        
                        lTabela = new Object[1][6];
                        lTabela[lLinha][0] = l.getCodLocacao();
                        lTabela[lLinha][1] = l.getCodQuarto().getNumQuarto();
                        for (LocacaoHospede lh : LocacaoHospedeRepository.readLocacaoId(l.getCodLocacao())) {
                            if (lh.getIndResponsavel().equals("S")) {
                                lTabela[lLinha][2] = lh.getCodPessoa().getNomPessoa();
                            }
                        }
                        lTabela[lLinha][3] = Formatacao.ajustaDataDMAHS(l.getDtaLocacao().toString());
                        lTabela[lLinha][4] = l.getVlrLocacao();
                        lTabela[lLinha][5] = situacao;
                        lLinha++;
                    }
                    break;
                }
                case 4: {
                    List<Locacao> listLocacao = LocacaoRepository.readSituation(pParam);
                    lTabela = new Object[listLocacao.size()][6];
                    for (Locacao l : listLocacao) {
                        String situacao = (l.getIndSituacao().equals("F") ? "Finalizada" : (l.getIndSituacao().equals("C") ? "Cancelada" : "Em aberto"));

                        lTabela[lLinha][0] = l.getCodLocacao();
                        lTabela[lLinha][1] = l.getCodQuarto().getNumQuarto();
                        for (LocacaoHospede lh : LocacaoHospedeRepository.readLocacaoId(l.getCodLocacao())) {
                            if (lh.getIndResponsavel().equals("S")) {
                                lTabela[lLinha][2] = lh.getCodPessoa().getNomPessoa();
                            }
                        }
                        lTabela[lLinha][3] = Formatacao.ajustaDataDMAHS(l.getDtaLocacao().toString());
                        lTabela[lLinha][4] = l.getVlrLocacao();
                        lTabela[lLinha][5] = situacao;
                        lLinha++;
                    }
                    break;
                }
                default:
                    List<Locacao> listLocacao = LocacaoRepository.readAll();
                    lTabela = new Object[listLocacao.size()][6];
                    for (Locacao l : listLocacao) {
                        String situacao = (l.getIndSituacao().equals("F") ? "Finalizada" : (l.getIndSituacao().equals("C") ? "Cancelada" : "Em aberto"));

                        lTabela[lLinha][0] = l.getCodLocacao();
                        lTabela[lLinha][1] = l.getCodQuarto().getNumQuarto();
                        for (LocacaoHospede lh : LocacaoHospedeRepository.readLocacaoId(l.getCodLocacao())) {
                            if (lh.getIndResponsavel().equals("S")) {
                                lTabela[lLinha][2] = lh.getCodPessoa().getNomPessoa();
                            }
                        }
                        lTabela[lLinha][3] = Formatacao.ajustaDataDMAHS(l.getDtaLocacao().toString());
                        lTabela[lLinha][4] = l.getVlrLocacao();
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

            DefaultTableCellRenderer lLeft = new DefaultTableCellRenderer();
            DefaultTableCellRenderer lCenter = new DefaultTableCellRenderer();
            DefaultTableCellRenderer lRight = new DefaultTableCellRenderer();

            lLeft.setHorizontalAlignment(SwingConstants.LEFT);
            lCenter.setHorizontalAlignment(SwingConstants.CENTER);
            lRight.setHorizontalAlignment(SwingConstants.RIGHT);

            for (int i = 0; i < pTabela.getColumnCount(); i++) {
                lColumn = pTabela.getColumnModel().getColumn(i);
                if (pOption != 3) {
                    switch (i) {
                        case 0:
                            lColumn.setPreferredWidth(20);
                            lColumn.setCellRenderer(lRight);
                            break;
                        case 1:
                            lColumn.setPreferredWidth(20);
                            lColumn.setCellRenderer(lCenter);
                            break;
                        case 2:
                            lColumn.setPreferredWidth(300);
                            lColumn.setCellRenderer(lLeft);
                            break;
                        case 3:
                            lColumn.setPreferredWidth(120);
                            lColumn.setCellRenderer(lCenter);
                            break;
                        case 4:
                            lColumn.setPreferredWidth(60);
                            lColumn.setCellRenderer(lCenter);
                            break;
                        case 5:
                            lColumn.setPreferredWidth(60);
                            lColumn.setCellRenderer(lCenter);
                            break;
                        case 6:
                            lColumn.setPreferredWidth(30);
                            lColumn.setCellRenderer(lCenter);
                            break;
                        case 7:
                            lColumn.setPreferredWidth(50);
                            lColumn.setCellRenderer(lLeft);
                            break;
                        case 8:
                            lColumn.setPreferredWidth(50);
                            lColumn.setCellRenderer(lCenter);
                            break;
                    }
                } else {
                    switch (i) {
                        case 0:
                            lColumn.setPreferredWidth(20);
                            lColumn.setCellRenderer(lRight);
                            break;
                        case 1:
                            lColumn.setPreferredWidth(200);
                            lColumn.setCellRenderer(lLeft);
                            break;
                        case 2:
                            lColumn.setPreferredWidth(75);
                            lColumn.setCellRenderer(lLeft);
                            break;
                        case 3:
                            lColumn.setPreferredWidth(200);
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