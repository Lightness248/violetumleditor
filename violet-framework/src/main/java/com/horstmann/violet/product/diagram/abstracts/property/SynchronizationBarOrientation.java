package com.horstmann.violet.product.diagram.abstracts.property;

import java.io.Serializable;

/**
 * Created by Slawomir Pomazany on 2016-01-31.
 */
public class SynchronizationBarOrientation implements Serializable, Cloneable {

    private boolean isVertical = true;

    public SynchronizationBarOrientation() {/**/}

    public SynchronizationBarOrientation(final boolean isVertical) {
        this.isVertical = isVertical;
    }

    public boolean isVertical() {
        return isVertical;
    }

    public void setVertical(final boolean vertical) {
        isVertical = vertical;
    }

    public SynchronizationBarOrientation clone() {
        final SynchronizationBarOrientation cloned = new SynchronizationBarOrientation();
        cloned.isVertical = isVertical;
        return cloned;
    }
}
