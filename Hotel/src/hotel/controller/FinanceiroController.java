package hotel.controller;

import hotel.model.Financeiro;
import hotel.model.LocacaoHospede;
import hotel.model.Pessoa;
import hotel.repository.FinanceiroRepository;
import hotel.repository.LocacaoHospedeRepository;
import java.util.List;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class FinanceiroController extends BaseController<Financeiro> {

    public Financeiro getReadId(int pCodigo) {
        try {
            return FinanceiroRepository.readId(pCodigo);
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }
    
    public List<Financeiro> getReadAllByDayMonthYear(int day, int month, int year) {
        try {
            return FinanceiroRepository.readAllDayMonthYear(day, month, year);
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }
    
    public List<Financeiro> getReadAllByMonthYear(int month, int year) {
        try {
            return FinanceiroRepository.readAllMonthYear(month, year);
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }

    public void popularTabela(JTable pTabela, int pOption, String pParam) {
        try {
            Object[][] lTabela = null;

            Object[] lTabelaTitulo = new Object[8];
            lTabelaTitulo[0] = "Código";
            lTabelaTitulo[1] = "Cliente";
            lTabelaTitulo[2] = "Quarto";
            lTabelaTitulo[3] = "Parcela";
            lTabelaTitulo[4] = "Data de vencimento";
            lTabelaTitulo[5] = "Data de pagamento";
            lTabelaTitulo[6] = "Valor pago";
            lTabelaTitulo[7] = "Forma de pagamento";

            int lLinha = 0;
            switch (pOption) {
                case 0: {
                    List<Financeiro> listFinanceiro = FinanceiroRepository.readAll();
                    lTabela = new Object[listFinanceiro.size()][8];
                    for (Financeiro f : listFinanceiro) {
                        Pessoa pessoaTitular = new Pessoa();
                        for (LocacaoHospede locacaoHospede : LocacaoHospedeRepository.readLocacaoId(f.getLocacao().getCodLocacao())) {
                            if (locacaoHospede.getIndResponsavel().equals("S")) {
                                pessoaTitular = locacaoHospede.getPessoa();
                            }
                        }

                        lTabela[lLinha][0] = f.getCodFinanceiro();
                        lTabela[lLinha][1] = pessoaTitular.getNomPessoa();
                        lTabela[lLinha][2] = f.getLocacao().getQuarto().getNumQuarto();
                        lTabela[lLinha][3] = f.getParcela();
                        lTabela[lLinha][4] = f.getDtaVencimento();
                        lTabela[lLinha][5] = f.getDtaPgto();
                        lTabela[lLinha][6] = f.getVlrPago();
                        lTabela[lLinha][7] = f.getFormaPgto().getDesFormaPgto();
                        lLinha++;
                    }
                    break;
                }
                case 1: {
                    String[] split = pParam.split(";");
                    List<Financeiro> listFinanceiro = FinanceiroRepository.readAllMonthYear(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                    lTabela = new Object[listFinanceiro.size()][8];
                    for (Financeiro f : listFinanceiro) {
                        Pessoa pessoaTitular = new Pessoa();
                        for (LocacaoHospede locacaoHospede : LocacaoHospedeRepository.readLocacaoId(f.getLocacao().getCodLocacao())) {
                            if (locacaoHospede.getIndResponsavel().equals("S")) {
                                pessoaTitular = locacaoHospede.getPessoa();
                            }
                        }

                        lTabela[lLinha][0] = f.getCodFinanceiro();
                        lTabela[lLinha][1] = pessoaTitular.getNomPessoa();
                        lTabela[lLinha][2] = f.getLocacao().getQuarto().getNumQuarto();
                        lTabela[lLinha][3] = f.getParcela();
                        lTabela[lLinha][4] = f.getDtaVencimento();
                        lTabela[lLinha][5] = f.getDtaPgto();
                        lTabela[lLinha][6] = f.getVlrPago();
                        lTabela[lLinha][7] = f.getFormaPgto().getDesFormaPgto();
                        lLinha++;
                    }
                    break;
                }
                default:
                    List<Financeiro> listFinanceiro = FinanceiroRepository.readAll();
                    lTabela = new Object[listFinanceiro.size()][8];
                    for (Financeiro f : listFinanceiro) {
                        Pessoa pessoaTitular = new Pessoa();
                        for (LocacaoHospede locacaoHospede : LocacaoHospedeRepository.readLocacaoId(f.getLocacao().getCodLocacao())) {
                            if (locacaoHospede.getIndResponsavel().equals("S")) {
                                pessoaTitular = locacaoHospede.getPessoa();
                            }
                        }

                        lTabela[lLinha][0] = f.getCodFinanceiro();
                        lTabela[lLinha][1] = pessoaTitular.getNomPessoa();
                        lTabela[lLinha][2] = f.getLocacao().getQuarto().getNumQuarto();
                        lTabela[lLinha][3] = f.getParcela();
                        lTabela[lLinha][4] = f.getDtaVencimento();
                        lTabela[lLinha][5] = f.getDtaPgto();
                        lTabela[lLinha][6] = f.getVlrPago();
                        lTabela[lLinha][7] = f.getFormaPgto().getDesFormaPgto();
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
