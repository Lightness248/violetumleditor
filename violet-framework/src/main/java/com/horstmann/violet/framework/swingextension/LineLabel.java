package com.horstmann.violet.framework.swingextension;

import java.awt.*;

/**
 * Created by Slawomir Pomazany on 2016-01-05.
 */
public class LineLabel extends Canvas {

    private static final int MARGIN_WIDTH = 20;
    private static final int MARGIN_HEIGHT = 20;

    private int fontHeight;
    private int fontWidth;
    private int fontAscent;
    private String labelText;

    public void setLabelText(String labelText) {
        this.labelText = labelText;
        this.measure();
        repaint();
    }

    private void measure() {
        FontMetrics fm = getFontMetrics(getFont());
        if (labelText != null && fm != null) {
            fontHeight = fm.getHeight();
            fontAscent = fm.getAscent();
            fontWidth = fm.stringWidth(labelText);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(fontWidth + MARGIN_WIDTH, fontHeight + MARGIN_HEIGHT);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(fontWidth, fontHeight);
    }

    @Override
    public void setFont(Font f) {
        super.setFont(f);
        measure();
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        Dimension d = getSize();
        int x = (d.width - fontWidth) / 2;
        int y = fontAscent + (d.height - fontHeight) / 2;

        g.setFont(getFont());
        g.drawString(labelText, x, y);
    }
}
