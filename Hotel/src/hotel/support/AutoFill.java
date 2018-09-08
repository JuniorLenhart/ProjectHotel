package hotel.support;

import hotel.model.Reserva;
import hotel.repository.ReservaRepository;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class AutoFill {

    static ArrayList<Reserva> reservas;

    public AutoFill(ArrayList<Reserva> lista, JTextField field, JPanel pnlFields, JPanel pnlAcompanhante) {
        setupAutoComplete(field, lista, pnlFields, pnlAcompanhante);
        field.setColumns(30);
    }

    private static boolean isAdjusting(JComboBox cbInput) {
        if (cbInput.getClientProperty("is_adjusting") instanceof Boolean) {
            return (Boolean) cbInput.getClientProperty("is_adjusting");
        }
        return false;
    }

    private static void setAdjusting(JComboBox cbInput, boolean adjusting) {
        cbInput.putClientProperty("is_adjusting", adjusting);
    }

    public static void setupAutoComplete(final JTextField txtInput, final ArrayList<Reserva> items, JPanel pnlFields, JPanel pnlAcompanhante) {
        pnlFields.setVisible(false);
        pnlAcompanhante.setVisible(false);
        final DefaultComboBoxModel model = new DefaultComboBoxModel();
        final JComboBox cbInput = new JComboBox(model) {
            public Dimension getPreferredSize() {
                return new Dimension(super.getPreferredSize().width, 0);
            }
        };
        setAdjusting(cbInput, false);
        for (Reserva r : items) {
            model.addElement(r.getCodReserva() + " " + r.getPessoa().getNomPessoa());
        }
        cbInput.setSelectedItem(null);
        cbInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isAdjusting(cbInput)) {
                    if (cbInput.getSelectedItem() != null) {
                        String[] text = cbInput.getSelectedItem().toString().split(" ", 2);
                        txtInput.setText(text[1]);
                        Reserva reserva = ReservaRepository.readId(Integer.parseInt(text[0]));
                        pnlFields.setVisible(true);
                        pnlAcompanhante.setVisible(true);
                        for (Component component : pnlFields.getComponents()) {
                            if (component instanceof JTextField) {
                                JTextField field = (JTextField) component;
                                fazComparacoes(reserva, field);
                            }
                        }
                    }
                }
            }
        });

        txtInput.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                setAdjusting(cbInput, true);
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (cbInput.isPopupVisible()) {
                        e.setKeyCode(KeyEvent.VK_ENTER);
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    e.setSource(cbInput);
                    cbInput.dispatchEvent(e);
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        //if (!isAdjusting(cbInput)) {
                        if (cbInput.getSelectedItem() != null) {
                            String[] text = cbInput.getSelectedItem().toString().split(" ", 2);
                            txtInput.setText(text[1]);
                            Reserva reserva = ReservaRepository.readId(Integer.parseInt(text[0]));
                            pnlFields.setVisible(true);
                            pnlAcompanhante.setVisible(true);
                            Component[] component = pnlFields.getComponents();
                            for (int i = 0; i < component.length; i++) {
                                if (component[i] instanceof JTextField) {
                                    JTextField field = (JTextField) component[i];
                                    fazComparacoes(reserva, field);
                                }
                            }
                            cbInput.setPopupVisible(false);
                        }
                        //}
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    cbInput.setPopupVisible(false);
                }
                setAdjusting(cbInput, false);
            }
        }
        );
        txtInput.getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                updateList();
            }

            public void removeUpdate(DocumentEvent e) {
                updateList();
            }

            public void changedUpdate(DocumentEvent e) {
                updateList();
            }

            private void updateList() {
                setAdjusting(cbInput, true);
                model.removeAllElements();
                String input = txtInput.getText();
                if (!input.isEmpty()) {
                    for (Reserva r : items) {
                        if (r.getPessoa().getNomPessoa().toLowerCase().startsWith(input.toLowerCase()) || r.getPessoa().getNumCpf().toLowerCase().startsWith(input.toLowerCase())) {
                            model.addElement(r.getCodReserva() + " " + r.getPessoa().getNomPessoa());
                        }

                    }
                }
                boolean pessoaTrue = false;
                for (Reserva r : ReservaRepository.readAll()) {
                    if (r.getPessoa().getNomPessoa().equals(txtInput.getText()) || r.getPessoa().getNumCpf().equals(txtInput.getText())) {
                        pessoaTrue = true;
                    }
                }
                if (!(pessoaTrue)) {
                    pnlFields.setVisible(false);
                    pnlAcompanhante.setVisible(false);
                    cbInput.setPopupVisible(model.getSize() > 0);
                }
                setAdjusting(cbInput, false);
            }
        });
        txtInput.setLayout(new BorderLayout());
        txtInput.add(cbInput, BorderLayout.SOUTH);
    }

    public static void fazComparacoes(Reserva reserva, JTextField field) {
        if (field.getName().equals("tfdCodigo")) {
            field.setText(reserva.getCodReserva().toString());
        } else if (field.getName().equals("tfdQuarto")) {
            field.setText(reserva.getQuarto().getCodQuarto().toString());
        } else if (field.getName().equals("tfdNomeTitular")) {
            field.setText(reserva.getPessoa().getNomPessoa());
        } else if (field.getName().equals("tfdDataEntrada")) {
            field.setText(Formatacao.ajustaDataDMAH(reserva.getDtaEntrada().toString()));
        } else if (field.getName().equals("tfdDataSaida")) {
            field.setText(Formatacao.ajustaDataDMAH(reserva.getDtaSaida().toString()));
        } else if (field.getName().equals("tfdValor")) {
            System.out.println(Validacao.getDifferenceDays(reserva.getDtaEntrada(), reserva.getDtaSaida()));
            field.setText(reserva.getVlrReserva().toString());
        } else if (field.getName().equals("tfdCelular")) {
            field.setText(reserva.getPessoa().getNumCelular());
        } else if (field.getName().equals("tfdPessoaCodigo")) {
            field.setText(reserva.getPessoa().getCodPessoa().toString());
        }
    }
}
