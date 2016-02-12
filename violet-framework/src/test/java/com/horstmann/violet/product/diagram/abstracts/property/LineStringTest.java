package com.horstmann.violet.product.diagram.abstracts.property;

import com.horstmann.violet.framework.swingextension.LineLabel;
import org.junit.Test;

import java.awt.geom.Rectangle2D;

import static org.junit.Assert.*;

/**
 * Created by Slawomir Pomazany on 2016-02-03.
 */
public class LineStringTest
{

    @Test
    public void shouldReturnLineLabelFromLineString()
    {
        //given
        final LineString lineString = new LineString();
        final LineLabel lineLabel = new LineLabel();
        lineString.setLabel(lineLabel);

        //when
        final LineLabel lineLabelFromLineString = lineString.getLabel();

        //then
        assertEquals(lineLabel, lineLabelFromLineString);
    }

    @Test
    public void shouldReturnRectangle2DFromLineString() {
        //given
        final LineString lineString = new LineString();
        final Rectangle2D rectangle2D = new Rectangle2D.Double(0, 0, 0, 0);

        //when
        final Rectangle2D rectangle2DfromLineString = lineString.getBounds();

        //then
        assertEquals(rectangle2D, rectangle2DfromLineString);
    }

    @Test
    public void shouldReturnTextFromLineString()
    {
        //given
        final LineString lineString = new LineString();
        final String text = "Test";
        lineString.setText(text);

        //when
        final String textFromLineString = lineString.getText();

        //then
        assertEquals(text, textFromLineString);
    }
}