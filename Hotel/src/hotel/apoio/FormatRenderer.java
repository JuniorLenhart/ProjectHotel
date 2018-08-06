package hotel.apoio;

import java.text.*;
import javax.swing.table.*;

public class FormatRenderer extends DefaultTableCellRenderer {

    private Format FFormat;

    public FormatRenderer(Format pFormat) {
        this.FFormat = pFormat;
    }

    public void setValue(Object pValue) {
        try {
            if (pValue != null) {
                pValue = FFormat.format(pValue);
            }
        } catch (IllegalArgumentException e) {
        }
        super.setValue(pValue);
    }

    public static FormatRenderer getDateRenderer() {
        return new FormatRenderer(new SimpleDateFormat("dd/MM/yyyy"));
    }

    public static FormatRenderer getDateTimeRenderer() {
        return new FormatRenderer(DateFormat.getDateTimeInstance());
    }

    public static FormatRenderer getTimeRenderer() {
        return new FormatRenderer(new SimpleDateFormat("HH:mm"));
    }
}
