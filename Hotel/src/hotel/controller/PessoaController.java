package hotel.controller;

import hotel.model.Pessoa;
import hotel.repository.PessoaRepository;
import hotel.support.Formatacao;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class PessoaController extends BaseController<Pessoa> {

    public String changeSituation(int pCodigo) {
        try {
            Pessoa pessoa = PessoaRepository.readId(pCodigo);
            pessoa.setIndSituacao("E");
            save(pessoa);
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }

    public Pessoa getReadId(int pCodigo) {
        try {
            return PessoaRepository.readId(pCodigo);
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }

    public void popularTabela(JTable pTabela, int pOption, String pParam) {
        try {
            Object[][] lTabela = null;

            Object[] lTabelaTitulo = new Object[7];
            lTabelaTitulo[0] = "Código";
            lTabelaTitulo[1] = "Nome";
            lTabelaTitulo[2] = "CPF";
            lTabelaTitulo[3] = "Data de nascimento";
            lTabelaTitulo[4] = "E-mail";
            lTabelaTitulo[5] = "Celular";
            lTabelaTitulo[6] = "Situação";

            int lLinha = 0;
            switch (pOption) {
                case 0: {
                    List<Pessoa> listPessoa = PessoaRepository.readAll();
                    lTabela = new Object[listPessoa.size()][7];
                    for (Pessoa p : listPessoa) {
                        String situacao = (p.getIndSituacao().equals("A") ? "Ativo" : "Excluído");

                        lTabela[lLinha][0] = p.getCodPessoa();
                        lTabela[lLinha][1] = p.getNomPessoa();
                        lTabela[lLinha][2] = Formatacao.formatarCPF(p.getNumCpf());
                        if (p.getDtaNasc() != null) {
                            lTabela[lLinha][3] = Formatacao.ajustaDataDMA(p.getDtaNasc().toString());
                        }
                        lTabela[lLinha][4] = p.getDesEmail();
                        lTabela[lLinha][5] = p.getNumCelular();
                        lTabela[lLinha][6] = situacao;
                        lLinha++;
                    }
                    break;
                }
                case 1: {
                    List<Pessoa> listPessoa = PessoaRepository.read(pParam);
                    lTabela = new Object[listPessoa.size()][7];
                    for (Pessoa p : listPessoa) {
                        String situacao = (p.getIndSituacao().equals("A") ? "Ativo" : "Excluído");

                        lTabela[lLinha][0] = p.getCodPessoa();
                        lTabela[lLinha][1] = p.getNomPessoa();
                        lTabela[lLinha][2] = Formatacao.formatarCPF(p.getNumCpf());
                        lTabela[lLinha][3] = Formatacao.ajustaDataDMA(p.getDtaNasc().toString());
                        lTabela[lLinha][4] = p.getDesEmail();
                        lTabela[lLinha][5] = p.getNumCelular();
                        lTabela[lLinha][6] = situacao;
                        lLinha++;
                    }
                    break;
                }
                case 2: {
                    Pessoa p = PessoaRepository.readId(Integer.parseInt(pParam));
                    if (p == null) {
                        JOptionPane.showMessageDialog(null, "Pessoa não encontrado pelo código: " + pParam);
                    } else {
                        String situacao = (p.getIndSituacao().equals("A") ? "Ativo" : "Excluído");

                        lTabela = new Object[1][7];
                        lTabela[lLinha][0] = p.getCodPessoa();
                        lTabela[lLinha][1] = p.getNomPessoa();
                        lTabela[lLinha][2] = Formatacao.formatarCPF(p.getNumCpf());
                        lTabela[lLinha][3] = Formatacao.ajustaDataDMA(p.getDtaNasc().toString());
                        lTabela[lLinha][4] = p.getDesEmail();
                        lTabela[lLinha][5] = p.getNumCelular();
                        lTabela[lLinha][6] = situacao;
                        lLinha++;
                    }
                    break;
                }
                default:
                    lTabelaTitulo = new Object[4];
                    lTabelaTitulo[0] = "Código";
                    lTabelaTitulo[1] = "Nome";
                    lTabelaTitulo[2] = "CPF";
                    lTabelaTitulo[3] = "E-mail";

                    List<Pessoa> listPessoa = PessoaRepository.readProfileNotUser();
                    lTabela = new Object[listPessoa.size()][4];
                    for (Pessoa p : listPessoa) {
                        lTabela[lLinha][0] = p.getCodPessoa();
                        lTabela[lLinha][1] = p.getNomPessoa();
                        lTabela[lLinha][2] = Formatacao.formatarCPF(p.getNumCpf());
                        lTabela[lLinha][3] = p.getDesEmail();
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
                            lColumn.setPreferredWidth(200);
                            lColumn.setCellRenderer(lLeft);
                            break;
                        case 2:
                            lColumn.setPreferredWidth(75);
                            lColumn.setCellRenderer(lLeft);
                            break;
                        case 3:
                            lColumn.setPreferredWidth(80);
                            lColumn.setCellRenderer(lLeft);
                            break;
                        case 4:
                            lColumn.setPreferredWidth(150);
                            lColumn.setCellRenderer(lCenter);
                            break;
                        case 5:
                            lColumn.setPreferredWidth(70);
                            lColumn.setCellRenderer(lLeft);
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
