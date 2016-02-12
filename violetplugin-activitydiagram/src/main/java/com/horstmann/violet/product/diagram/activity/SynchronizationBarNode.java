/*
 Violet - A program for editing UML diagrams.

 Copyright (C) 2007 Cay S. Horstmann (http://horstmann.com)
 Alexandre de Pellegrin (http://alexdp.free.fr);

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package com.horstmann.violet.product.diagram.activity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import com.horstmann.violet.product.diagram.abstracts.edge.IEdge;
import com.horstmann.violet.product.diagram.abstracts.node.INode;
import com.horstmann.violet.product.diagram.abstracts.node.RectangularNode;
import com.horstmann.violet.product.diagram.abstracts.property.LineString;
import com.horstmann.violet.product.diagram.abstracts.property.SynchronizationBarOrientation;

/**
 * A synchronization bar node in an activity diagram.
 */
public class SynchronizationBarNode extends RectangularNode
{

    @Override
    public boolean addConnection(final IEdge e)
    {
        return e.getEnd() != null && this != e.getEnd();
    }

    @Override
    public Point2D getConnectionPoint(final IEdge e)
    {
        final Point2D defaultConnectionPoint = super.getConnectionPoint(e);
        if (!ActivityTransitionEdge.class.isInstance(e))
        {
            return defaultConnectionPoint;
        }
        else if (orientation.isVertical())
        {
            return verticalConnectionPoint(e);
        }
        else if (!orientation.isVertical())
        {
            return horizontalConnectionPoint(e);
        }
        return defaultConnectionPoint;
    }

    private Point2D verticalConnectionPoint(final IEdge e)
    {
        final Point2D defaultConnectionPoint = super.getConnectionPoint(e);
        if (!ActivityTransitionEdge.class.isInstance(e))
        {
            return defaultConnectionPoint;
        }
        final INode end = e.getEnd();
        final INode start = e.getStart();
        if (this == start)
        {
            final Point2D endConnectionPoint = end.getConnectionPoint(e);
            final double y = defaultConnectionPoint.getY();
            final double x = endConnectionPoint.getX();
            return new Point2D.Double(x, y);
        }
        if (this == end)
        {
            final Point2D startConnectionPoint = start.getConnectionPoint(e);
            final double y = defaultConnectionPoint.getY();
            final double x = startConnectionPoint.getX();
            return new Point2D.Double(x, y);
        }
        return defaultConnectionPoint;
    }

    private Point2D horizontalConnectionPoint(final IEdge e)
    {
        final Point2D defaultConnectionPoint = super.getConnectionPoint(e);
        if (!ActivityTransitionEdge.class.isInstance(e))
        {
            return defaultConnectionPoint;
        }
        final INode end = e.getEnd();
        final INode start = e.getStart();
        if (this == start)
        {
            final Point2D endConnectionPoint = end.getConnectionPoint(e);
            final double y = endConnectionPoint.getY();
            final double x = defaultConnectionPoint.getX();
            return new Point2D.Double(x, y);
        }
        if (this == end)
        {
            final Point2D startConnectionPoint = start.getConnectionPoint(e);
            final double y = startConnectionPoint.getY();
            final double x = defaultConnectionPoint.getX();
            return new Point2D.Double(x, y);
        }
        return defaultConnectionPoint;
    }

    @Override
    public Rectangle2D getBounds()
    {
        Rectangle2D b = getDefaultBounds();
        final List<INode> connectedNodes = getConnectedNodes();
        if (connectedNodes.size() > 0)
        {
            if (orientation.isVertical())
            {
                double minX = Double.MAX_VALUE;
                double maxX = Double.MIN_VALUE;

                for (final INode n : connectedNodes)
                {
                    minX = Math.min(minX, n.getBounds().getMinX());
                    maxX = Math.max(maxX, n.getBounds().getMaxX());
                }

                minX -= EXTRA_WIDTH;
                maxX += EXTRA_WIDTH;
                translate(minX - b.getX(), 0);
                b = new Rectangle2D.Double(minX, b.getY(), maxX - minX, DEFAULT_HEIGHT);

            }
            else if (!orientation.isVertical())
            {
                double minY = Double.MAX_VALUE;
                double maxY = Double.MIN_VALUE;

                for (final INode n : connectedNodes)
                {
                    minY = Math.min(minY, n.getBounds().getMinY());
                    maxY = Math.max(maxY, n.getBounds().getMaxY());
                }

                minY -= EXTRA_HEIGHT;
                maxY += EXTRA_HEIGHT;
                translate(0, minY - b.getY());
                b = new Rectangle2D.Double(b.getX(), minY, DEFAULT_HEIGHT, maxY - minY);
            }
        }
        return b;
    }

    /**
     * @return minimal bounds (location + default width and default height
     */
    private Rectangle2D getDefaultBounds()
    {
        final Point2D currentLocation = getLocation();
        final double x = currentLocation.getX();
        final double y = currentLocation.getY();
        final double w = DEFAULT_WIDTH;
        final double h = DEFAULT_HEIGHT;
        Rectangle2D currentBounds = null;
        if (orientation.isVertical())
        {
            currentBounds = new Rectangle2D.Double(x, y, w, h);

        }
        else if (!orientation.isVertical())
        {
            currentBounds = new Rectangle2D.Double(x, y, h, w);
        }
        return currentBounds;
    }

    /**
     * @return nodes which are connected (with edges) to this node
     */
    private List<INode> getConnectedNodes()
    {
        List<INode> connectedNodes = new ArrayList<INode>();
        // needs to contain all incoming and outgoing edges
        for (IEdge e : getGraph().getAllEdges())
        {
            if (e.getStart() == this) connectedNodes.add(e.getEnd());
            if (e.getEnd() == this) connectedNodes.add(e.getStart());
        }
        return connectedNodes;
    }

    @Override
    public void draw(Graphics2D g2)
    {
        super.draw(g2);

        // Backup current color;
        Color oldColor = g2.getColor();

        // Perform drawing
        g2.setColor(getBorderColor());
        g2.fill(getShape());

        // Restore first color
        g2.setColor(oldColor);
    }

    public void setOrientation(SynchronizationBarOrientation newValue)
    {
        orientation = newValue;
    }

    public SynchronizationBarOrientation getOrientation()
    {
        return orientation;
    }

    /**
     * @see java.lang.Object#clone()
     */
    @Override
    public SynchronizationBarNode clone()
    {
        final SynchronizationBarNode cloned = (SynchronizationBarNode) super.clone();
        cloned.orientation = orientation.clone();
        return cloned;
    }

    public SynchronizationBarNode()
    {
        this.orientation = new SynchronizationBarOrientation(true);
    }

    private static final int DEFAULT_WIDTH = 100;
    private static final int DEFAULT_HEIGHT = 4;
    private static final int EXTRA_HEIGHT = 12;
    private static final int EXTRA_WIDTH = 12;

    private SynchronizationBarOrientation orientation;
}
