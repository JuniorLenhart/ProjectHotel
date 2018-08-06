package hotel.apoio;

import javax.swing.JFormattedTextField;

public class Validacao {

    private static final int[] FPesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] FPesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    private static int calcularDigito(String pStr, int[] pPeso) {
        int lSoma = 0;
        for (int lIndice = pStr.length() - 1, lDigito; lIndice >= 0; lIndice--) {
            lDigito = Integer.parseInt(pStr.substring(lIndice, lIndice + 1));
            lSoma += lDigito * pPeso[pPeso.length - pStr.length() + lIndice];
        }
        lSoma = 11 - lSoma % 11;
        return lSoma > 9 ? 0 : lSoma;
    }

    public static boolean validarCPF(String pCPF) {
        if ((pCPF == null) || (pCPF.length() != 11)) {
            return false;
        }
        Integer lDigito1 = calcularDigito(pCPF.substring(0, 9), FPesoCPF);
        Integer lDigito2 = calcularDigito(pCPF.substring(0, 9) + lDigito1, FPesoCPF);
        return pCPF.equals(pCPF.substring(0, 9) + lDigito1.toString() + lDigito2.toString());
    }

    public static boolean validarCNPJ(String pCNPJ) {
        if ((pCNPJ == null) || (pCNPJ.length() != 14)) {
            return false;
        }
        Integer lDigito1 = calcularDigito(pCNPJ.substring(0, 12), FPesoCNPJ);
        Integer lDigito2 = calcularDigito(pCNPJ.substring(0, 12) + lDigito1, FPesoCNPJ);
        return pCNPJ.equals(pCNPJ.substring(0, 12) + lDigito1.toString() + lDigito2.toString());
    }

    public static boolean validarDataDMA(int d, int m, int a) {
        boolean correto = true;
        int[] dias = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (a < 0 || m < 1 || m > 12) {
            correto = false;
        } else {
            if (a % 4 == 0 && (a % 100 != 0 || a % 400 == 0)) {
                dias[1] = 29;
            }
            if (d < 1 || d > dias[m - 1]) {
                correto = false;
            }
        }
        return (correto);
    }

    public static boolean validarDataFormatada(String pDataComFormato) {
        String[] lData = pDataComFormato.split("/");
        if (lData.length == 3) {
            return (validarDataDMA(Integer.parseInt(lData[0]), Integer.parseInt(lData[1]), Integer.parseInt(lData[2])));
        } else {
            return false;
        }
    }

    public static boolean validarTelefone(JFormattedTextField pCampo) {
        return (pCampo.getText().trim().length() < 13);
    }

    public static boolean validarCelular(JFormattedTextField pCampo) {
        return (pCampo.getText().trim().length() < 14);
    }
}
