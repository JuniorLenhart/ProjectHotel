package hotel.controller;

import hotel.model.AplicacaoBotao;
import hotel.model.Permissao;
import hotel.repository.AplicacaoBotaoRepository;
import hotel.repository.PermissaoRepository;
import java.awt.HeadlessException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class AplicacaoBotaoController extends BaseController<AplicacaoBotao> {

    public AplicacaoBotao getReadId(int pCodigo) {
        try {
            return AplicacaoBotaoRepository.readId(pCodigo);
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }

    public List<AplicacaoBotao> getReadAll() {
        try {
            return AplicacaoBotaoRepository.readAll();
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }

    public List<AplicacaoBotao> readAllAplicacaoID(int pCodigo) {
        try {
            return AplicacaoBotaoRepository.readAllAplicacaoID(pCodigo);
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
            lTabelaTitulo[1] = "Tela";
            lTabelaTitulo[2] = "Nome";
            lTabelaTitulo[3] = "Nome Arquivo";
            
            int lLinha = 0;
            switch (pOption) {
                case 0: {
                    List<AplicacaoBotao> listBotao = AplicacaoBotaoRepository.readAll();
                    lTabela = new Object[listBotao.size()][4];
                    for (AplicacaoBotao b : listBotao) {
                        lTabela[lLinha][0] = b.getCodAplicacaoBotao();
                        lTabela[lLinha][1] = b.getAplicacao().getNomArquivoJava();
                        lTabela[lLinha][2] = b.getNomBotao();
                        lTabela[lLinha][3] = b.getNomBotaoForm();
                        lLinha++;
                    }
                    break;
                }
                case 1: {
                    List<AplicacaoBotao> listBotao = AplicacaoBotaoRepository.read(pParam);
                    lTabela = new Object[listBotao.size()][4];
                    for (AplicacaoBotao b : listBotao) {
                        lTabela[lLinha][0] = b.getCodAplicacaoBotao();
                        lTabela[lLinha][1] = b.getAplicacao().getNomArquivoJava();
                        lTabela[lLinha][2] = b.getNomBotao();
                        lTabela[lLinha][3] = b.getNomBotaoForm();
                        lLinha++;
                    }
                    break;
                }
                case 3: {
                    String split[] = pParam.split(";");
                    List<AplicacaoBotao> listBotao = AplicacaoBotaoRepository.readAllAplicacaoID(Integer.parseInt(split[0]));
                    List<Permissao> listPermissao = PermissaoRepository.readAllByTelaAndUsuario(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                    
                    lTabelaTitulo = new Object[3];
                    lTabelaTitulo[0] = "Código";
                    lTabelaTitulo[1] = "Nome";
                    lTabelaTitulo[2] = "Tem acesso?";
                    
                    lTabela = new Object[listBotao.size()][3];
                    if (listPermissao.isEmpty()) {
                        for (AplicacaoBotao b : listBotao) {
                            lTabela[lLinha][0] = b.getCodAplicacaoBotao();
                            lTabela[lLinha][1] = b.getNomBotao();
                            lTabela[lLinha][2] = (boolean) false;
                            lLinha++;
                        }
                    } else {
                        for (AplicacaoBotao b : listBotao) {
                            lTabela[lLinha][0] = b.getCodAplicacaoBotao();
                            lTabela[lLinha][1] = b.getNomBotao();
                            
                            int count = listPermissao.size();
                            while (count != 0) {
                                if (listPermissao.size() >= lLinha) {
                                    Permissao permissao = listPermissao.get(--count);
                                    if (permissao.getAplicacaoBotao().getCodAplicacaoBotao().intValue() == b.getCodAplicacaoBotao().intValue() && permissao.getUsuario().getCodUsuario() == Integer.parseInt(split[1])) {
                                        lTabela[lLinha][2] = (boolean) true;
                                        count = 0;
                                    } else {
                                        lTabela[lLinha][2] = (boolean) false;
                                    }
                                } else {
                                    count = 0;
                                    lTabela[lLinha][2] = (boolean) false;
                                }
                            }
                            lLinha++;
                        }
                    }

                    break;
                }
                default:
                    AplicacaoBotao botao = AplicacaoBotaoRepository.readId(Integer.parseInt(pParam));
                    if (botao == null) {
                        JOptionPane.showMessageDialog(null, "Botão não encontrado pelo código: " + pParam);
                    } else {
                        lTabela = new Object[1][4];

                        lTabela[lLinha][0] = botao.getCodAplicacaoBotao();
                        lTabela[lLinha][1] = botao.getAplicacao().getNomArquivoJava();
                        lTabela[lLinha][2] = botao.getNomBotao();
                        lTabela[lLinha][3] = botao.getNomBotaoForm();
                        lLinha++;
                    }
                    break;
            }

            pTabela.setModel(new DefaultTableModel(lTabela, lTabelaTitulo) {
                @Override
                public boolean isCellEditable(int pRow, int pColumn) {
                    if (pOption == 3) {
                        if (pColumn == 2) {
                            return true;
                        }
                    }
                    return false;
                }

                Class[] types = new Class[]{
                    java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class};

                @Override
                public Class getColumnClass(int pColumn) {
                    if (pOption == 3) {
                        return types[pColumn];
                    } else {
                        return Object.class;
                    }
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
                            lColumn.setPreferredWidth(30);
                            lColumn.setCellRenderer(lRight);
                            break;
                        case 1:
                            lColumn.setPreferredWidth(150);
                            lColumn.setCellRenderer(lLeft);
                            break;
                        case 2:
                            lColumn.setPreferredWidth(150);
                            lColumn.setCellRenderer(lLeft);
                            break;
                        case 3:
                            lColumn.setPreferredWidth(100);
                            lColumn.setCellRenderer(lCenter);
                            break;
                    }
                } else {
                    switch (i) {
                        case 0:
                            lColumn.setPreferredWidth(30);
                            lColumn.setCellRenderer(lRight);
                            break;
                        case 1:
                            lColumn.setPreferredWidth(150);
                            lColumn.setCellRenderer(lLeft);
                            break;
                    }
                }
            }
        } catch (HeadlessException | NumberFormatException ex) {
            LoggerController.log(this.getClass(), ex);
        }
    }
}
