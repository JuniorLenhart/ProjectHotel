package hotel.controller;

import hotel.config.HibernateUtil;
import hotel.model.Parametro;
import hotel.model.Usuario;
import hotel.repository.UsuarioRepository;
import hotel.support.Formatacao;
import java.awt.HeadlessException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;

public class UsuarioController extends BaseController<Usuario> {

    public String changeSituation(int pCodigo) {
        try {
            Usuario usuario = UsuarioRepository.readId(pCodigo);
            usuario.setIndSituacao("E");
            save(usuario);
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }

    public Usuario getReadId(int pCodigo) {
        try {
            return UsuarioRepository.readId(pCodigo);
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }

    public String resetPassword(int id) {
        try {
            Usuario usuario = getReadId(id);
            resetPassword(usuario);
            save(usuario);
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }

    public String resetPassword(Usuario usuario) {
        try {
            usuario.setDesSenha(Parametro.DES_SENHA_DEFAULT);
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }

    public Boolean verifyExistsLogin(String pLogin) {
        try {
            return getUserWithLogin(pLogin) != null;
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return false;
    }

    public Usuario getUserWithLogin(String pLogin) {
        try {
            return UsuarioRepository.getUserWithLogin(pLogin);
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }

    public Usuario validaLogin(String login, String senha) {
        try {
            return UsuarioRepository.validaLogin(login, senha);
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }

    public void setUserSession(Usuario usuario) {

        HibernateUtil.getSession().doWork(new Work() {
            public void execute(Connection connection) throws SQLException {
                CallableStatement call = connection.prepareCall("{ call set_user_session(?)}");
                call.setInt(1, usuario.getCodUsuario()); // 1 é o 1º parametro, 10 é o valor
                call.execute();
            }
        });
    }

    public void popularTabela(JTable pTabela, int pOption, String pParam) {
        try {
            Object[][] lTabela = null;

            Object[] lTabelaTitulo = new Object[6];
            lTabelaTitulo[0] = "Código";
            lTabelaTitulo[1] = "Nome";
            lTabelaTitulo[2] = "CPF";
            lTabelaTitulo[3] = "Login";
            lTabelaTitulo[4] = "Tipo";
            lTabelaTitulo[5] = "Situação";

            int lLinha = 0;
            switch (pOption) {
                case 0: {
                    List<Usuario> listUsuario = UsuarioRepository.readAll();
                    lTabela = new Object[listUsuario.size()][6];
                    for (Usuario u : listUsuario) {
                        String tipo = (u.getIndTipo().equals("A") ? "Administrador" : "Usuário");
                        String situacao = (u.getIndSituacao().equals("A") ? "Ativo" : "Excluído");

                        lTabela[lLinha][0] = u.getCodUsuario();
                        lTabela[lLinha][1] = u.getPessoa().getNomPessoa();
                        lTabela[lLinha][2] = Formatacao.formatarCPF(u.getPessoa().getNumCpf());
                        lTabela[lLinha][3] = u.getDesLogin();
                        lTabela[lLinha][4] = tipo;
                        lTabela[lLinha][5] = situacao;
                        lLinha++;
                    }
                    break;
                }
                case 1: {
                    List<Usuario> listUsuario = UsuarioRepository.read(pParam);
                    lTabela = new Object[listUsuario.size()][6];
                    for (Usuario u : listUsuario) {
                        String tipo = (u.getIndTipo().equals("A") ? "Administrador" : "Usuário");
                        String situacao = (u.getIndSituacao().equals("A") ? "Ativo" : "Excluído");

                        lTabela[lLinha][0] = u.getCodUsuario();
                        lTabela[lLinha][1] = u.getPessoa().getNomPessoa();
                        lTabela[lLinha][2] = Formatacao.formatarCPF(u.getPessoa().getNumCpf());
                        lTabela[lLinha][3] = u.getDesLogin();
                        lTabela[lLinha][4] = tipo;
                        lTabela[lLinha][5] = situacao;
                        lLinha++;
                    }
                    break;
                }
                default:
                    Usuario usuario = UsuarioRepository.readId(Integer.parseInt(pParam));
                    if (usuario == null) {
                        JOptionPane.showMessageDialog(null, "Usuário não encontrado pelo código: " + pParam);
                    } else {
                        String tipo = (usuario.getIndTipo().equals("A") ? "Administrador" : "Usuário");
                        String situacao = (usuario.getIndSituacao().equals("A") ? "Ativo" : "Excluído");

                        lTabela = new Object[1][6];
                        lTabela[lLinha][0] = usuario.getCodUsuario();
                        lTabela[lLinha][1] = usuario.getPessoa().getNomPessoa();
                        lTabela[lLinha][2] = Formatacao.formatarCPF(usuario.getPessoa().getNumCpf());
                        lTabela[lLinha][3] = usuario.getDesLogin();
                        lTabela[lLinha][4] = tipo;
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
                switch (i) {
                    case 0:
                        lColumn.setPreferredWidth(20);
                        lColumn.setCellRenderer(lRight);
                        break;
                    case 1:
                        lColumn.setPreferredWidth(160);
                        lColumn.setCellRenderer(lLeft);
                        break;
                    case 2:
                        lColumn.setPreferredWidth(50);
                        lColumn.setCellRenderer(lLeft);
                        break;
                    case 3:
                        lColumn.setPreferredWidth(70);
                        lColumn.setCellRenderer(lLeft);
                        break;
                    case 4:
                        lColumn.setPreferredWidth(60);
                        lColumn.setCellRenderer(lCenter);
                        break;
                    case 5:
                        lColumn.setPreferredWidth(30);
                        lColumn.setCellRenderer(lCenter);
                        break;
                }
            }
        } catch (HeadlessException | NumberFormatException ex) {
            LoggerController.log(this.getClass(), ex);
        }
    }
}
