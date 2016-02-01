package com.horstmann.violet.product.diagram.state;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

/**
 * Created by Slawomir Pomazany on 2016-02-01.
 */
public class StateTransitionEdgeBeanInfo extends SimpleBeanInfo
{
    @Override
    public PropertyDescriptor[] getPropertyDescriptors()
    {
        try
        {
            PropertyDescriptor[] descriptors = new PropertyDescriptor[]
                    {
                        new PropertyDescriptor("startLabel", StateTransitionEdge.class),
                        new PropertyDescriptor("middleLabel", StateTransitionEdge.class),
                        new PropertyDescriptor("endLabel", StateTransitionEdge.class),
                        new PropertyDescriptor("bentStyle", StateTransitionEdge.class),
                    };
            for (int i = 0; i < descriptors.length; i++)
            {
                descriptors[i].setValue("priority", new Integer(i));
            }
            return descriptors;
        }
        catch (IntrospectionException exception)
        {
            exception.printStackTrace();
            return null;
        }
    }
}
