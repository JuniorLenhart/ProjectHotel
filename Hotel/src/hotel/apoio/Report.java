package hotel.apoio;

import java.io.*;
import java.util.*;
import javax.swing.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.*;
import net.sf.jasperreports.view.*;

public class Report {

    public void abrirRelatorio(Map pMap, String pArquivo) {
        try {
            JasperReport lRelatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream("/videostore/relatorio/" + pArquivo + ".jrxml"));

            //ImageIcon lImage = new ImageIcon(getClass().getResource("/videostore/imagem/LogoAgua.png"));
            //pMap.put("logo", lImage.getImage());
            JasperPrint lImpressao = JasperFillManager.fillReport(lRelatorio, pMap, ConexaoBD.getInstance().getConnection());

            JasperViewer.viewReport(lImpressao, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar relatório: " + e);
        }
    }

    public void exportarPDF(Map pMap, String pArquivo) {
        try {
            JasperReport lRelatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream("/videostore/relatorio/" + pArquivo + ".jrxml"));

            JasperPrint lImpressao = JasperFillManager.fillReport(lRelatorio, pMap, ConexaoBD.getInstance().getConnection());

            JRExporter lExportar = new JRPdfExporter();
            lExportar.setParameter(JRExporterParameter.JASPER_PRINT, lImpressao);
            lExportar.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream(pArquivo + ".pdf"));

            lExportar.exportReport();
            JOptionPane.showMessageDialog(null, "Arquivo Exportado!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao exportar relatório: " + e);
        }
    }
}
