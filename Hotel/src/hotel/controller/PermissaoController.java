package hotel.controller;

import hotel.model.Permissao;
import hotel.model.Usuario;
import hotel.repository.PermissaoRepository;
import java.awt.HeadlessException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
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

    public void loadPermission(Usuario usuario) {
        try {
            List<Permissao> listPermissao = PermissaoRepository.readPermissionWithUser(usuario);

            Map<String, Set<String>> map = new HashMap();
            for (Permissao p : listPermissao) {
                if (map.containsKey(p.getAplicacaoBotao().getAplicacao().getNomArquivoJava())) {
                    Set<String> value = map.get(p.getAplicacaoBotao().getAplicacao().getNomArquivoJava());
                    value.add(p.getAplicacaoBotao().getNomBotaoForm());
                } else {
                    Set<String> value = new TreeSet();
                    value.add(p.getAplicacaoBotao().getNomBotaoForm());
                    map.put(p.getAplicacaoBotao().getAplicacao().getNomArquivoJava(), value);
                }
            }

            Permissao.PERMISSAO = map;
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
    }

    public static boolean hasPermission(String view, String button) {
        if (Permissao.PERMISSAO.containsKey(view)) {
            return Permissao.PERMISSAO.get(view).contains(button);
        }
        return false;
    }

    public static boolean hasPermission(String view) {
        return Permissao.PERMISSAO.containsKey(view);
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
                        lTabela[lLinha][1] = p.getAplicacaoBotao().getAplicacao().getNomAplicacao();
                        lTabela[lLinha][2] = p.getAplicacaoBotao().getNomBotao();
                        lTabela[lLinha][3] = p.getUsuario().getDesLogin();
                        lLinha++;
                    }
                    break;
                }
                case 1: {
                    List<Permissao> listPermissao = PermissaoRepository.read(pParam);
                    lTabela = new Object[listPermissao.size()][4];
                    for (Permissao p : listPermissao) {

                        lTabela[lLinha][0] = p.getCodPermissao();
                        lTabela[lLinha][1] = p.getAplicacaoBotao().getAplicacao().getNomAplicacao();
                        lTabela[lLinha][2] = p.getAplicacaoBotao().getNomBotao();
                        lTabela[lLinha][3] = p.getUsuario().getDesLogin();
                        lLinha++;
                    }
                    break;
                }
                case 2: {
                    Permissao permissao = PermissaoRepository.readId(Integer.parseInt(pParam));
                    if (permissao == null) {
                        JOptionPane.showMessageDialog(null, "Permissão não encontrada pelo código: " + pParam);
                    } else {
                        lTabela = new Object[1][4];

                        lTabela[lLinha][0] = permissao.getCodPermissao();
                        lTabela[lLinha][1] = permissao.getAplicacaoBotao().getAplicacao().getNomAplicacao();
                        lTabela[lLinha][2] = permissao.getAplicacaoBotao().getNomBotao();
                        lTabela[lLinha][3] = permissao.getUsuario().getDesLogin();
                        lLinha++;
                    }
                    break;
                }
                default:
                    List<Permissao> listPermissao = PermissaoRepository.readAll();
                    lTabela = new Object[listPermissao.size()][4];
                    for (Permissao p : listPermissao) {

                        lTabela[lLinha][0] = p.getCodPermissao();
                        lTabela[lLinha][1] = p.getAplicacaoBotao().getAplicacao().getNomAplicacao();
                        lTabela[lLinha][2] = p.getAplicacaoBotao().getNomBotao();
                        lTabela[lLinha][3] = p.getUsuario().getDesLogin();
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
