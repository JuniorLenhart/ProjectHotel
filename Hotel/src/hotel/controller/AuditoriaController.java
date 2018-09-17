package hotel.controller;

import hotel.model.Auditoria;
import hotel.repository.AuditoriaRepository;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class AuditoriaController extends BaseController<Auditoria> {

    public Auditoria getReadId(int pCodigo) {
        try {
            return AuditoriaRepository.readId(pCodigo);
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }

    public void popularTabela(JTable pTabela, int pOption, String pParam) {
        try {
            Object[][] lTabela = null;

            Object[] lTabelaTitulo = new Object[5];
            lTabelaTitulo[0] = "Código";
            lTabelaTitulo[1] = "Usuário";
            lTabelaTitulo[2] = "Tipo";
            lTabelaTitulo[3] = "Data";
            lTabelaTitulo[4] = "Descrição";

            int lLinha = 0;
            switch (pOption) {
                case 0: {
                    List<Auditoria> listAuditoria = AuditoriaRepository.readAll();
                    lTabela = new Object[listAuditoria.size()][5];
                    for (Auditoria a : listAuditoria) {
                        lTabela[lLinha][0] = a.getCodAuditoria();
                        lTabela[lLinha][1] = a.getUsuario().getDesLogin();
                        lTabela[lLinha][2] = a.getTipAuditoria();
                        lTabela[lLinha][3] = a.getDtaAuditoria();
                        lTabela[lLinha][4] = a.getDesAuditoria();
                        lLinha++;
                    }
                    break;
                }
                case 1: {
                    List<Auditoria> listAuditoria = AuditoriaRepository.read(pParam);
                    lTabela = new Object[listAuditoria.size()][5];
                    for (Auditoria a : listAuditoria) {
                        lTabela[lLinha][0] = a.getCodAuditoria();
                        lTabela[lLinha][1] = a.getUsuario().getDesLogin();
                        lTabela[lLinha][2] = a.getTipAuditoria();
                        lTabela[lLinha][3] = a.getDtaAuditoria();
                        lTabela[lLinha][4] = a.getDesAuditoria();
                        lLinha++;
                    }
                    break;
                }
                case 2: {
                    List<Auditoria> listAuditoria = AuditoriaRepository.readPorTipos(pParam);
                    lTabela = new Object[listAuditoria.size()][5];
                    for (Auditoria a : listAuditoria) {
                        lTabela[lLinha][0] = a.getCodAuditoria();
                        lTabela[lLinha][1] = a.getUsuario().getDesLogin();
                        lTabela[lLinha][2] = a.getTipAuditoria();
                        lTabela[lLinha][3] = a.getDtaAuditoria();
                        lTabela[lLinha][4] = a.getDesAuditoria();
                        lLinha++;
                    }
                    break;
                }
                case 3: {
                    List<Auditoria> listAuditoria = AuditoriaRepository.readLogin(pParam);
                    lTabela = new Object[listAuditoria.size()][5];
                    for (Auditoria a : listAuditoria) {
                        lTabela[lLinha][0] = a.getCodAuditoria();
                        lTabela[lLinha][1] = a.getUsuario().getDesLogin();
                        lTabela[lLinha][2] = a.getTipAuditoria();
                        lTabela[lLinha][3] = a.getDtaAuditoria();
                        lTabela[lLinha][4] = a.getDesAuditoria();
                        lLinha++;
                    }
                    break;
                }
                default:
                    Auditoria auditoria = AuditoriaRepository.readId(Integer.parseInt(pParam));
                    if (auditoria == null) {
                        JOptionPane.showMessageDialog(null, "Auditoria não encontrado pelo cóodigo: " + pParam);
                    } else {

                        lTabela = new Object[1][5];
                        lTabela[lLinha][0] = auditoria.getCodAuditoria();
                        lTabela[lLinha][1] = auditoria.getUsuario().getDesLogin();
                        lTabela[lLinha][2] = auditoria.getTipAuditoria();
                        lTabela[lLinha][3] = auditoria.getDtaAuditoria();
                        lTabela[lLinha][4] = auditoria.getDesAuditoria();
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
