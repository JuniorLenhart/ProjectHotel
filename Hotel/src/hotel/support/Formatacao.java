package hotel.support;

import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;

public class Formatacao {

    public static JFormattedTextField getFormatado(String pFormato) {
        JFormattedTextField lCampo = null;
        MaskFormatter lFormat = new MaskFormatter();

        lFormat.setPlaceholderCharacter(' ');
        lFormat.setValueContainsLiteralCharacters(false);

        try {
            lFormat.setMask(pFormato);
            lCampo = new JFormattedTextField(lFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return lCampo;
    }

    public static JFormattedTextField getTelefone() {
        return getFormatado("(##) ####-####");
    }

    public static JFormattedTextField getCNPJ() {
        return getFormatado("##.###.###/####-##");
    }

    public static JFormattedTextField getCPF() {
        return getFormatado("###.###.###-##");
    }

    public static JFormattedTextField getData() {
        return getFormatado("##/##/####");
    }

    public static JFormattedTextField getDataHora() {
        return getFormatado("##/##/#### ##:##");
    }

    public static void formatarData(JFormattedTextField pCampo) {
        try {
            MaskFormatter lMask = new MaskFormatter();
            lMask.setPlaceholderCharacter(' ');
            lMask.setMask("##/##/####");

            pCampo.setFormatterFactory(null);
            pCampo.setFormatterFactory(new DefaultFormatterFactory(lMask));
            pCampo.setValue(null);
        } catch (ParseException e) {
            System.err.println(e);
        }
    }

    public static void formatarCPF(JFormattedTextField pCampo) {
        try {
            MaskFormatter lMask = new MaskFormatter();
            lMask.setPlaceholderCharacter(' ');
            lMask.setMask("###.###.###-##");

            pCampo.setFormatterFactory(null);
            pCampo.setFormatterFactory(new DefaultFormatterFactory(lMask));
            pCampo.setValue(null);
        } catch (ParseException e) {
            System.err.println(e);
        }
    }

    public static String formatarCPF(String pCPF) {
        String lCPFFormatado = null;
        try {
            MaskFormatter lMask = new MaskFormatter("###.###.###-##");
            lMask.setValueContainsLiteralCharacters(false);
            lCPFFormatado = lMask.valueToString(pCPF);
        } catch (ParseException e) {
            System.err.println(e);
        }
        return lCPFFormatado;
    }

    public static void formatarCNPJ(JFormattedTextField pCampo) {
        try {
            MaskFormatter lMask = new MaskFormatter();
            lMask.setPlaceholderCharacter(' ');
            lMask.setMask("##.###.###/####-##");

            pCampo.setFormatterFactory(null);
            pCampo.setFormatterFactory(new DefaultFormatterFactory(lMask));
            pCampo.setValue(null);
        } catch (ParseException e) {
            System.err.println(e);
        }
    }

    public static void formatarTelefone(JFormattedTextField pCampo) {
        try {

            MaskFormatter lMask = new MaskFormatter();
            lMask.setPlaceholderCharacter(' ');
            lMask.setMask("(##) ####-####");

            pCampo.setFormatterFactory(null);
            pCampo.setFormatterFactory(new DefaultFormatterFactory(lMask));
            pCampo.setValue(null);
        } catch (ParseException e) {
            System.err.println(e);
        }
    }

    public static void formatarCelular(JFormattedTextField pCampo) {
        try {

            MaskFormatter lMask = new MaskFormatter();
            lMask.setPlaceholderCharacter(' ');
            lMask.setMask("(##) #####-####");

            pCampo.setFormatterFactory(null);
            pCampo.setFormatterFactory(new DefaultFormatterFactory(lMask));
            pCampo.setValue(null);
        } catch (ParseException e) {
            System.err.println(e);
        }
    }

    public static String ajustaDataDMAH(String pData) {
        String lDataFormatada = "";

        if (pData != null) {
            if (!pData.trim().isEmpty()) {
                try {
                    Date lDataAMD = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(pData);
                    lDataFormatada = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(lDataAMD);
                } catch (ParseException e) {
                    System.err.println(e);
                }
            }
        }

        return lDataFormatada;
    }

    public static String ajustaDataDMA(String pData) {
        String lDataFormatada = "";

        if (pData != null) {
            if (!pData.trim().isEmpty()) {
                try {
                    Date lDataAMD = new SimpleDateFormat("yyyy-MM-dd").parse(pData);
                    lDataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(lDataAMD);
                } catch (ParseException e) {
                    System.err.println(e);
                }
            }
        }

        return lDataFormatada;
    }

    public static String ajustaDataAMD(String pData) {
        String lDataFormatada = "";

        if (pData != null) {
            if (!pData.trim().isEmpty()) {
                try {
                    Date lDataDMA = new SimpleDateFormat("dd/MM/yyyy").parse(pData);
                    lDataFormatada = new SimpleDateFormat("yyyy-MM-dd").format(lDataDMA);
                } catch (ParseException e) {
                    System.err.println(e);
                }
            }
        }

        return lDataFormatada;
    }

    public static String removerFormatacao(String pDado) {
        String lRetorno = "";
        for (int i = 0; i < pDado.length(); i++) {
            if (pDado.charAt(i) != '.' && pDado.charAt(i) != '/' && pDado.charAt(i) != '-'
                    && pDado.charAt(i) != '(' && pDado.charAt(i) != ')' && pDado.charAt(i) != ' ') {
                lRetorno += pDado.charAt(i);
            }
        }
        return lRetorno;
    }

    public static void toLowerCase(JTextField pCampo) {
        pCampo.setDocument(new PlainDocument() {
            @Override
            public void insertString(int pOffs, String pStr, AttributeSet pA) throws BadLocationException {
                super.insertString(pOffs, pStr.toLowerCase(), pA);
            }
        });
    }

    public static void toUpperCase(JTextField pCampo) {
        pCampo.setDocument(new PlainDocument() {
            @Override
            public void insertString(int pOffs, String pStr, AttributeSet pA) throws BadLocationException {
                super.insertString(pOffs, pStr.toUpperCase(), pA);
            }
        });
    }
    
    public static String formatacaoAuditoria(String tabelaNome, String descricaoNova, String descricaoAntiga) {
        String retorno = "";
        if(!descricaoAntiga.equals("")) {
            retorno = "| Tabela "+tabelaNome+" | Dados antigos: ["+descricaoAntiga+"], Dados novos: ["+descricaoNova+"]";
        } else {
            retorno = "| Tabela "+tabelaNome+" | Dados: ["+descricaoNova+"]";
        }
        return retorno;
    }
}
