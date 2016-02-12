package com.horstmann.violet.product.diagram.abstracts.property;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Slawomir Pomazany on 2016-02-01.
 */
public class SynchronizationBarOrientationTest
{

    @Test
    public void shouldBeVertical()
    {
        //given
        final SynchronizationBarOrientation synchronizationBarOrientation = new SynchronizationBarOrientation();
        synchronizationBarOrientation.setVertical(true);

        //when
        final boolean isVertical = synchronizationBarOrientation.isVertical();

        //then
        assertTrue(isVertical);
    }

    @Test
    public void shouldBeClonedObject()
    {
        //given
        final SynchronizationBarOrientation original = new SynchronizationBarOrientation(false);

        //when
        final SynchronizationBarOrientation cloned = original.clone();

        //then
        assertNotEquals(original, cloned);
        assertEquals(original.isVertical(), cloned.isVertical());
    }

}