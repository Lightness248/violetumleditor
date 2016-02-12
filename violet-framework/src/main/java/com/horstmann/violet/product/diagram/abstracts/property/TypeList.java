package com.horstmann.violet.product.diagram.abstracts.property;

import java.io.Serializable;

/**
 * Created by Slawomir Pomazany on 2016-01-07.
 */
public class TypeList implements Serializable, Cloneable {
    private LineString name;

    public TypeList(LineString name) {
        this.name = name;
    }

    public TypeList() {}

    public LineString getName() {
        return name;
    }

    public void setName(LineString name) {
        this.name = name;
    }

    public TypeList clone() {
        TypeList cloned = new TypeList();
        cloned.name = name;
        return cloned;
    }
}
