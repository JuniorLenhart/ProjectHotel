package hotel.controller;

import hotel.model.Botao;
import hotel.repository.BotaoRepository;
import java.awt.HeadlessException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class BotaoController extends BaseController<Botao>{

    public Botao getReadId(int pCodigo) {
        try {
            return BotaoRepository.readId(pCodigo);
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }
    
    public List<Botao> getReadAll() {
        try {
            return BotaoRepository.readAll();
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }
    
    public List<Botao> readAllAplicacaoID(int pCodigo) {
        try {
            return BotaoRepository.readAllAplicacaoID(pCodigo);
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
            lTabelaTitulo[3] = "Nome arquivo";
            int lLinha = 0;
            switch (pOption) {
                case 0: {
                    List<Botao> listBotao = BotaoRepository.readAll();
                    lTabela = new Object[listBotao.size()][4];
                    for (Botao b : listBotao) {

                        lTabela[lLinha][0] = b.getCodBotao();
                        lTabela[lLinha][1] = b.getCodAplicacao().getNomArquivoJava();
                        lTabela[lLinha][2] = b.getNomBotao();
                        lTabela[lLinha][3] = b.getNomBotaoForm();
                        lLinha++;
                    }
                    break;
                }
                case 1: {
                    List<Botao> listBotao = BotaoRepository.read(pParam);
                    lTabela = new Object[listBotao.size()][4];
                    for (Botao b : listBotao) {

                        lTabela[lLinha][0] = b.getCodBotao();
                        lTabela[lLinha][1] = b.getCodAplicacao().getNomArquivoJava();
                        lTabela[lLinha][2] = b.getNomBotao();
                        lTabela[lLinha][3] = b.getNomBotaoForm();
                        lLinha++;
                    }
                    break;
                }
                default:
                    Botao botao = BotaoRepository.readId(Integer.parseInt(pParam));
                    if (botao == null) {
                        JOptionPane.showMessageDialog(null, "Consumível não encontrado pelo cóodigo: " + pParam);
                    } else {

                        lTabela = new Object[1][4];
                        
                        lTabela[lLinha][0] = botao.getCodBotao();
                        lTabela[lLinha][1] = botao.getCodAplicacao().getNomArquivoJava();
                        lTabela[lLinha][2] = botao.getNomBotao();
                        lTabela[lLinha][3] = botao.getNomBotaoForm();
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
        } catch (HeadlessException | NumberFormatException ex) {
            LoggerController.log(this.getClass(), ex);
        }
    }
}
