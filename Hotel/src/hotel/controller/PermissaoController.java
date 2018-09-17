package hotel.controller;

import hotel.model.Permissao;
import hotel.repository.PermissaoRepository;
import java.awt.HeadlessException;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class PermissaoController extends BaseController<Permissao> {

    public Permissao getReadId(int pCodigo) {
        try {
            return PermissaoRepository.readId(pCodigo);
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }

    public List<Permissao> getReadAll() {
        try {
            return PermissaoRepository.readAll();
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }

    public List<Permissao> getReadAllTelaID(int pCodigo) {
        try {
            return PermissaoRepository.readAllByTela(pCodigo);
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }

    public boolean isInserted(int pCodigoAplicacaoBotao, int pCodigoUsuario) {
        try {
            return PermissaoRepository.isInserted(pCodigoAplicacaoBotao, pCodigoUsuario);
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return false;
    }

    public Permissao readByAplicacaoBotaoAndUsuario(int pCodigoUsuario, int pCodigoAplicacaoBotao) {
        try {
            return PermissaoRepository.readByAplicacaoBotaoAndUsuario(pCodigoAplicacaoBotao, pCodigoUsuario);
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
            lTabelaTitulo[2] = "Botão";
            lTabelaTitulo[3] = "Usuário";
            int lLinha = 0;
            switch (pOption) {
                case 0: {
                    List<Permissao> listPermissao = PermissaoRepository.readAll();
                    lTabela = new Object[listPermissao.size()][4];
                    for (Permissao p : listPermissao) {

                        lTabela[lLinha][0] = p.getCodPermissao();
                        lTabela[lLinha][1] = p.getCodAplicacaoBotao().getCodAplicacao().getNomAplicacao();
                        lTabela[lLinha][2] = p.getCodAplicacaoBotao().getNomBotao();
                        lTabela[lLinha][3] = p.getCodUsuario().getDesLogin();
                        lLinha++;
                    }
                    break;
                }
                case 1: {
                    List<Permissao> listPermissao = PermissaoRepository.read(pParam);
                    lTabela = new Object[listPermissao.size()][4];
                    for (Permissao p : listPermissao) {

                        lTabela[lLinha][0] = p.getCodPermissao();
                        lTabela[lLinha][1] = p.getCodAplicacaoBotao().getCodAplicacao().getNomAplicacao();
                        lTabela[lLinha][2] = p.getCodAplicacaoBotao().getNomBotao();
                        lTabela[lLinha][3] = p.getCodUsuario().getDesLogin();
                        lLinha++;
                    }
                    break;
                }
                case 2: {
                    Permissao permissao = PermissaoRepository.readId(Integer.parseInt(pParam));
                    if (permissao == null) {
                        JOptionPane.showMessageDialog(null, "Permissão não encontrada pelo cóodigo: " + pParam);
                    } else {
                        lTabela = new Object[1][4];

                        lTabela[lLinha][0] = permissao.getCodPermissao();
                        lTabela[lLinha][1] = permissao.getCodAplicacaoBotao().getCodAplicacao().getNomAplicacao();
                        lTabela[lLinha][2] = permissao.getCodAplicacaoBotao().getNomBotao();
                        lTabela[lLinha][3] = permissao.getCodUsuario().getDesLogin();
                        lLinha++;
                    }
                    break;
                }
                default:
                    List<Permissao> listPermissao = PermissaoRepository.readAll();
                    lTabela = new Object[listPermissao.size()][4];
                    for (Permissao p : listPermissao) {

                        lTabela[lLinha][0] = p.getCodPermissao();
                        lTabela[lLinha][1] = p.getCodAplicacaoBotao().getCodAplicacao().getNomAplicacao();
                        lTabela[lLinha][2] = p.getCodAplicacaoBotao().getNomBotao();
                        lTabela[lLinha][3] = p.getCodUsuario().getDesLogin();
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
            }
        } catch (HeadlessException ex) {
            LoggerController.log(this.getClass(), ex);
        }
    }
}
