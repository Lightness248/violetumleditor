package com.horstmann.violet.framework.propertyeditor.customeditor;

import com.horstmann.violet.product.diagram.abstracts.property.SynchronizationBarOrientation;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyEditorSupport;

/**
 * Created by Slawomir Pomazany on 2016-01-31.
 */
public class SynchronizationBarOrientationEditor extends PropertyEditorSupport {

    private SynchronizationBarOrientation source;
    private JComponent dropdownListComponent;
    private String[] orientationTypes = { "Vertical", "Horizontal" };

    public boolean supportsCustomEditor()
    {
        return true;
    }

    public Component getCustomEditor()
    {
        this.source = (SynchronizationBarOrientation) getValue();
        final JPanel panel = new JPanel();
        panel.add(getTextEditorComponent());
        return panel;
    }

    private JComponent getTextEditorComponent()
    {
        if (this.dropdownListComponent == null)
        {
            this.dropdownListComponent = new JPanel();
            final JComboBox<String> orientationList = new JComboBox<>(orientationTypes);
            if (source.isVertical()) {
                orientationList.setSelectedIndex(0);
            } else if (!source.isVertical()) {
                orientationList.setSelectedIndex(1);
            }
            this.dropdownListComponent.add(orientationList);
            orientationList.addActionListener(e -> {
                final JComboBox cb = (JComboBox) e.getSource();
                final String type = (String) cb.getSelectedItem();
                if (type.equalsIgnoreCase(orientationTypes[0])) {
                    source.setVertical(true);
                } else if (type.equalsIgnoreCase(orientationTypes[1])) {
                    source.setVertical(false);
                } else {
                    source.setVertical(true);
                }
            });
        }
        return this.dropdownListComponent;
    }
}
