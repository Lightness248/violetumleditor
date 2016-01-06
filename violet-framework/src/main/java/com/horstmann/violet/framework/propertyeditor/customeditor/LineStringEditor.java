package com.horstmann.violet.framework.propertyeditor.customeditor;

import com.horstmann.violet.product.diagram.abstracts.property.LineString;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.beans.PropertyEditorSupport;

/**
 * Created by Slawomir Pomazany on 2016-01-06.
 */
public class LineStringEditor extends PropertyEditorSupport {
    private LineString source;
    private JComponent textEditorComponent;

    private static final int COLUMNS = 30;

    public boolean supportsCustomEditor()
    {
        return true;
    }

    public Component getCustomEditor()
    {
        this.source = (LineString) getValue();
        final JPanel panel = new JPanel();
        panel.add(getTextEditorComponent());
        return panel;
    }

    private JComponent getTextEditorComponent()
    {
        if (this.textEditorComponent == null)
        {
            final JTextField textField = new JTextField(COLUMNS);

            textField.setText(source.getText());
            textField.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    source.setText(textField.getText());
                    firePropertyChange();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    source.setText(textField.getText());
                    firePropertyChange();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {

                }
            });
            this.textEditorComponent = new JScrollPane(textField);
        }
        return this.textEditorComponent;
    }
}
