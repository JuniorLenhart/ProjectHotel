package hotel.support;

import java.awt.*;
import java.text.*;
import java.time.*;
import java.util.*;
import javax.swing.*;

public class Unit {

    public static void setPosition(JInternalFrame pInternalFrame) {
        Dimension lDimension = pInternalFrame.getDesktopPane().getSize();
        pInternalFrame.setLocation((lDimension.width - pInternalFrame.getSize().width) / 2, (lDimension.height - pInternalFrame.getSize().height) / 2);
    }

    public static int calcularIdade(Date pNascimento) {
        return calcularIdade(LocalDateTime.ofInstant(pNascimento.toInstant(), ZoneOffset.UTC).toLocalDate());
    }

    public static int calcularIdade(String pNascimento) {
        return calcularIdade(LocalDateTime.ofInstant(stringParseDate(pNascimento).toInstant(), ZoneOffset.UTC).toLocalDate());
    }

    private static int calcularIdade(LocalDate pNascimento) {
        return Period.between(pNascimento, LocalDate.now()).getYears();
    }

    public static Date stringParseDate(String pData) {
        Date lData = null;

        try {
            lData = new SimpleDateFormat("dd/MM/yyyy").parse(pData);
        } catch (ParseException e) {
            System.err.println(e);
        }

        return lData;
    }

    public static String strIIF(String pStr) {
        if (pStr.trim().isEmpty()) {
            return null;
        } else {
            return "'" + pStr + "'";
        }
    }

    public static Date addDias(Date pDate, int pDias) {
        Calendar lCalendar = Calendar.getInstance();
        lCalendar.setTime(pDate);
        lCalendar.add(Calendar.DATE, pDias);

        return lCalendar.getTime();
    }

    public static int calcularDias(String pData1, String pData2) {
        Date lData1 = null;
        Date lData2 = null;
        try {
            SimpleDateFormat lFormat = new SimpleDateFormat("dd/MM/yyyy");
            lData1 = lFormat.parse(pData1);
            lData2 = lFormat.parse(pData2);
        } catch (ParseException e) {
            System.err.println(e);
        }

        long lDias = (lData2.getTime() - lData1.getTime()) + 3600000;

        return (int) (lDias / 86400000L);
    }

    public static Date addDias(String pDate, int pDias) {
        return addDias(stringParseDate(pDate), pDias);
    }

    public static String dateParseString(Date pData) {
        return new SimpleDateFormat("dd/MM/yyyy").format(pData);
    }

    public static String getDataAtual() {
        DateFormat lDataFormatada = new SimpleDateFormat("dd/MM/yyyy");
        return lDataFormatada.format(new Date());
    }

    public static String getDataHoraAtual() {
        DateFormat lDataFormatada = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return lDataFormatada.format(new Date());
    }
    
    public static String getDataHoraAtualConcat() {
        DateFormat lDataFormatada = new SimpleDateFormat("ddMMyyyyHHmmss");
        return lDataFormatada.format(new Date());
    }

    public static String removerAcentos(String pStr) {
        return Normalizer.normalize(pStr, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}
