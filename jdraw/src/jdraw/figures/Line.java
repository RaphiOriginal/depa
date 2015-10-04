package jdraw.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

import jdraw.framework.FigureHandle;

public class Line extends AbstractGeometry{
	
	private java.awt.geom.Line2D.Double line;
	
	/**
	 * Create an oval of the given dimension
	 * @param x the x-coordinate of the upper left corner of the oval
	 * @param y the y-coordinate of the upper left corner of the oval
	 * @param w the oval's width
	 * @param h the oval's height
	 */
	public Line(int x, int y, int w, int h){
		line = new java.awt.geom.Line2D.Double(x, y, x+w, h+y);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawLine((int)line.x1, (int)line.y1, 
				(int)line.x2, (int)line.y2);
	}

	@Override
	public void move(int dx, int dy) {
		if(line.getX1() != dx || line.getY1() != dy){
			line.setLine(line.x1 + dx, line.y1 + dy, line.x2+dx, line.y2+dy);
			figureChanged(this);
		}
	}

	@Override
	public boolean contains(int x, int y) {
		return line.getBounds().contains(x, y);
	}

	@Override
	public void setBounds(Point origin, Point corner) {
		line.setLine(origin.x, origin.y, corner.x, corner.y);
		figureChanged(this);
	}

	@Override
	public Rectangle getBounds() {
		return line.getBounds();
	}

	@Override
	public List<FigureHandle> getHandles() {
		return null;
	}

}
