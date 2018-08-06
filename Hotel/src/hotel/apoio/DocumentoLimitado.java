package hotel.apoio;

import javax.swing.text.*;

public class DocumentoLimitado extends PlainDocument {

    private int FMaxLength = 10;

    public DocumentoLimitado(int pMaxLength) {
        this.FMaxLength = pMaxLength;
    }

    public void insertString(int pOffSet, String pStr, AttributeSet pA) throws BadLocationException {

        if (pStr == null) {
            return;
        }

        String lStrAntiga = getText(0, getLength());
        int lTamanhoNovo = lStrAntiga.length() + pStr.length();

        if (lTamanhoNovo <= FMaxLength) {
            super.insertString(pOffSet, pStr, pA);
        } else {
            super.insertString(pOffSet, "", pA);
        }
    }
}
