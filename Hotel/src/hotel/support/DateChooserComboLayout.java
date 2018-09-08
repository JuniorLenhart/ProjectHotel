package hotel.support;

import datechooser.beans.DateChooserCombo;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JFormattedTextField;

public class DateChooserComboLayout extends DateChooserCombo {
    
    public DateChooserComboLayout() {
        super();
        
        Component lComponent[] = this.getComponents();
        for (int i = 0; i < lComponent.length; i++) {
            if (lComponent[i] instanceof JFormattedTextField) {
                JFormattedTextField lField = (JFormattedTextField) lComponent[i];
                lField.setFont(new java.awt.Font("SansSerif", 0, 14));
                lField.setBackground(Color.white);
                lField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
            }
        }
    }
}
