package hotel.support;

import java.text.*;
import javax.swing.*;

public class NumberRenderer extends FormatRenderer {

    public NumberRenderer(NumberFormat pNumberFormat) {
        super(pNumberFormat);
        setHorizontalAlignment(SwingConstants.RIGHT);
    }

    public static NumberRenderer getCurrencyRenderer() {
        return new NumberRenderer(NumberFormat.getCurrencyInstance());
    }

    public static NumberRenderer getIntegerRenderer() {
        return new NumberRenderer(NumberFormat.getIntegerInstance());
    }

    public static NumberRenderer getPercentRenderer() {
        return new NumberRenderer(NumberFormat.getPercentInstance());
    }

    public static NumberRenderer getNumberRenderer(int pFraction) {
        NumberFormat lNumberFormat = NumberFormat.getNumberInstance();
        lNumberFormat.setMaximumFractionDigits(pFraction);
        return new NumberRenderer(lNumberFormat);
    }
}
