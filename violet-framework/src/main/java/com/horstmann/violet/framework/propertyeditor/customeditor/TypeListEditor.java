package com.horstmann.violet.framework.propertyeditor.customeditor;

import com.horstmann.violet.product.diagram.abstracts.property.TypeList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyEditorSupport;

/**
 * Created by Slawomir Pomazany on 2016-01-07.
 */
public class TypeListEditor extends PropertyEditorSupport {

    private TypeList source;
    private JComponent dropdownListComponent;
    private String[] typeList = {"Class", "Interface", "None"};

    public boolean supportsCustomEditor()
    {
        return true;
    }
    public Component getCustomEditor()
    {
        this.source = (TypeList) getValue();
        final JPanel panel = new JPanel();
        panel.add(getTextEditorComponent());
        return panel;
    }

    private JComponent getTextEditorComponent()
    {
        if (this.dropdownListComponent == null)
        {
            this.dropdownListComponent = new JPanel();
            ButtonGroup buttons = new ButtonGroup();
            for(String s: typeList) {
                JRadioButton radioButton = new JRadioButton(s);
                buttons.add(radioButton);
                this.dropdownListComponent.add(radioButton);
                radioButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        source.getName().setText("test");
                        source.getName().getLabel().repaint();
                    }
                });
            }
        }
        return this.dropdownListComponent;
    }
/*public Component getCustomEditor()
{
    this.source = (TypeList) getValue();
    final JPanel panel = new JPanel();
    panel.add(getTextEditorComponent());
    return panel;
}*/

/*    private JComponent getTextEditorComponent()
    {
        if (this.textEditorComponent == null)
        {
            final JTextField textField = new JTextField(30);

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
    }*/

}
