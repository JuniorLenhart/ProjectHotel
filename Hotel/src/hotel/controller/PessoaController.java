package hotel.controller;

import hotel.model.Pessoa;
import hotel.repository.PessoaRepository;
import hotel.support.Formatacao;
import java.sql.Timestamp;
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

    public List<Pessoa> getReadAll() {
        try {
            return PessoaRepository.readAll();
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

    public Pessoa getReadCPF(String pCPF) {
        try {
            return PessoaRepository.readCPF(pCPF);
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }

    public void popularTabela(JTable pTabela, int pOption, String pParam, int codPessoa) {
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

            List<Pessoa> listPessoa = null;
            Pessoa pessoa = null;

            switch (pOption) {
                case 0: {
                    listPessoa = PessoaRepository.readAll();
                    break;
                }
                case 1: {
                    listPessoa = PessoaRepository.read(pParam);
                    break;
                }
                case 2: {
                    pessoa = PessoaRepository.readId(Integer.parseInt(pParam));
                    break;
                }
                case 3: {
                    listPessoa = PessoaRepository.readProfileNotUser();
                    break;
                }
                case 4: {
                    pessoa = PessoaRepository.readCPF(pParam);
                    break;
                }
                case 5: {
                    listPessoa = PessoaRepository.readSituation("A");
                    break;
                }
                case 6: {
                    listPessoa = PessoaRepository.readActive(pParam);
                    break;
                }
                case 7: {
                    pessoa = PessoaRepository.readIdActive(Integer.parseInt(pParam));
                    break;
                }
                case 8: {
                    listPessoa = PessoaRepository.readListCPF(pParam);
                    break;
                }
            }

            int lLinha = 0;
            switch (pOption) {
                case 0:
                case 1:
                    if (codPessoa != -1) {
                        lTabela = new Object[listPessoa.size() - 1][7];
                    } else {
                        lTabela = new Object[listPessoa.size()][7];
                    }
                    for (Pessoa p : listPessoa) {
                        if (p.getCodPessoa() != codPessoa) {
                            String situacao = (p.getIndSituacao().equals("A") ? "Ativo" : "Excluído");

                            lTabela[lLinha][0] = p.getCodPessoa();
                            lTabela[lLinha][1] = p.getNomPessoa();
                            lTabela[lLinha][2] = Formatacao.formatarCPF(p.getNumCpf());
                            if (p.getDtaNasc() != null) {
                                lTabela[lLinha][3] = Formatacao.ajustaDataDMA(Timestamp.valueOf(p.getDtaNasc().toString()).toString());
                            }
                            lTabela[lLinha][4] = p.getDesEmail();
                            lTabela[lLinha][5] = p.getNumCelular();
                            lTabela[lLinha][6] = situacao;
                            lLinha++;
                        }
                    }
                    break;
                case 2:
                    if (pessoa == null) {
                        JOptionPane.showMessageDialog(null, "Pessoa não encontrado pelo código: " + pParam);
                    } else {
                        if (pessoa.getCodPessoa() != codPessoa) {
                            String situacao = (pessoa.getIndSituacao().equals("A") ? "Ativo" : "Excluído");

                            lTabela = new Object[1][7];
                            lTabela[lLinha][0] = pessoa.getCodPessoa();
                            lTabela[lLinha][1] = pessoa.getNomPessoa();
                            lTabela[lLinha][2] = Formatacao.formatarCPF(pessoa.getNumCpf());
                            if (pessoa.getDtaNasc() != null) {
                                lTabela[lLinha][3] = Formatacao.ajustaDataDMA(pessoa.getDtaNasc().toString());
                            }
                            lTabela[lLinha][4] = pessoa.getDesEmail();
                            lTabela[lLinha][5] = pessoa.getNumCelular();
                            lTabela[lLinha][6] = situacao;
                            lLinha++;
                        }
                    }
                    break;
                case 3:
                case 5:
                case 6:
                case 8:
                    lTabelaTitulo = new Object[4];
                    lTabelaTitulo[0] = "Código";
                    lTabelaTitulo[1] = "Nome";
                    lTabelaTitulo[2] = "CPF";
                    lTabelaTitulo[3] = "E-mail";

                    if (codPessoa != -1) {
                        lTabela = new Object[listPessoa.size() - 1][4];
                    } else {
                        lTabela = new Object[listPessoa.size()][4];
                    }
                    for (Pessoa p : listPessoa) {
                        if (p.getCodPessoa() != codPessoa) {
                            lTabela[lLinha][0] = p.getCodPessoa();
                            lTabela[lLinha][1] = p.getNomPessoa();
                            lTabela[lLinha][2] = Formatacao.formatarCPF(p.getNumCpf());
                            lTabela[lLinha][3] = p.getDesEmail();
                            lLinha++;
                        }
                    }
                    break;
                case 4:
                    lTabelaTitulo = new Object[4];
                    lTabelaTitulo[0] = "Código";
                    lTabelaTitulo[1] = "Nome";
                    lTabelaTitulo[2] = "CPF";
                    lTabelaTitulo[3] = "E-mail";

                    if (pessoa == null) {
                        JOptionPane.showMessageDialog(null, "Pessoa não encontrado pelo CPF: " + pParam);
                    } else {
                        if (pessoa.getCodPessoa() != codPessoa) {
                            lTabela = new Object[1][4];
                            lTabela[lLinha][0] = pessoa.getCodPessoa();
                            lTabela[lLinha][1] = pessoa.getNomPessoa();
                            lTabela[lLinha][2] = Formatacao.formatarCPF(pessoa.getNumCpf());
                            lTabela[lLinha][3] = pessoa.getDesEmail();
                            lLinha++;
                        }
                    }
                    break;
                case 7:
                    lTabelaTitulo = new Object[4];
                    lTabelaTitulo[0] = "Código";
                    lTabelaTitulo[1] = "Nome";
                    lTabelaTitulo[2] = "CPF";
                    lTabelaTitulo[3] = "E-mail";

                    if (pessoa == null) {
                        JOptionPane.showMessageDialog(null, "Pessoa não encontrado pelo código: " + pParam);
                    } else {
                        if (pessoa.getCodPessoa() != codPessoa) {
                            lTabela = new Object[1][4];
                            lTabela[lLinha][0] = pessoa.getCodPessoa();
                            lTabela[lLinha][1] = pessoa.getNomPessoa();
                            lTabela[lLinha][2] = Formatacao.formatarCPF(pessoa.getNumCpf());
                            lTabela[lLinha][3] = pessoa.getDesEmail();
                            lLinha++;
                        }
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
                if (pOption != 3 && pOption != 4 && pOption != 5 && pOption != 6 && pOption != 7 && pOption != 8) {
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
                            lColumn.setCellRenderer(lCenter);
                            break;
                        case 4:
                            lColumn.setPreferredWidth(150);
                            lColumn.setCellRenderer(lLeft);
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
                            lColumn.setCellRenderer(lLeft);
                            break;
                    }
                }
            }
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
    }
}
