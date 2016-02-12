package com.horstmann.violet.product.diagram.abstracts.property;

import com.horstmann.violet.framework.swingextension.LineLabel;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.Hashtable;

/**
 * Created by Slawomir Pomazany on 2016-01-05.
 */
public class LineString implements Serializable, Cloneable {

    private final static int DEFAULT_FONT_SIZE = 12;
    private LineLabel label;
    private Font defaultFont;
    private String text;

    public LineString() {
        this.text = "";
        this.label = new LineLabel();
        this.defaultFont = new Font(Font.SANS_SERIF, Font.PLAIN, DEFAULT_FONT_SIZE);
        this.label.setFont(defaultFont);
    }

    public LineLabel getLabel() {
        return label;
    }

    public void setLabel(final LineLabel label) {
        this.label = label;
    }

    private void setLabelText() {
        this.label.setLabelText(text);
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
        setLabelText();
    }

    public Rectangle2D getBounds() {
        this.setLabelText();
        this.label.validate();
        if (text.length() == 0) {
            return new Rectangle2D.Double(0, 0, 0, 0);
        }
        Dimension dim = label.getPreferredSize();
        return new Rectangle2D.Double(0, 0, dim.getWidth(), dim.getHeight());
    }

    public void draw(Graphics2D g2, Rectangle2D r) {
        label.setBounds(0, 0, (int) r.getWidth(), (int) r.getHeight());
        g2.translate(r.getX(), r.getY());
        label.paint(g2);
        g2.translate(-r.getX(), -r.getY());
    }

    public enum FontStyle {
        UNDERLINE,
        BOLD,
        ITALIC
    }

    public void setFontStyle(final FontStyle fStyle) {
        final Hashtable<TextAttribute, Object> map = new Hashtable<>();
        switch (fStyle) {
            case UNDERLINE:
                map.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                this.defaultFont = defaultFont.deriveFont(map);
                this.label.setFont(defaultFont);
                break;
            case BOLD:
                map.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
                this.defaultFont = defaultFont.deriveFont(map);
                this.label.setFont(defaultFont);
                break;
            case ITALIC:
                map.put(TextAttribute.POSTURE, TextAttribute.POSTURE_OBLIQUE);
                this.defaultFont = defaultFont.deriveFont(map);
                this.label.setFont(defaultFont);
                break;
        }
    }

    public enum FontSize {
        SMALL  (8f),
        MEDIUM (12f),
        LARGE  (16f);

        private final float size;

        FontSize(final float size) {
            this.size = size;
        }

        public float getSize() {
            return this.size;
        }
    }

    public void setFontSize(final FontSize fs) {
        switch(fs) {
            case SMALL:
                this.defaultFont = defaultFont.deriveFont(fs.getSize());
                this.label.setFont(defaultFont);
                break;
            case MEDIUM:
                this.defaultFont = defaultFont.deriveFont(fs.getSize());
                this.label.setFont(defaultFont);
                break;
            case LARGE:
                this.defaultFont = defaultFont.deriveFont(fs.getSize());
                this.label.setFont(defaultFont);
                break;
        }
    }

    public LineString clone() {
        LineString cloned = new LineString();
        cloned.text = text;
        cloned.defaultFont = defaultFont;
        cloned.label.setFont(defaultFont);
        cloned.setLabelText();
        return cloned;
    }
}
