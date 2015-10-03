package jdraw.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

import jdraw.framework.FigureHandle;

public class Oval extends AbstractGeometry{
	
	private java.awt.geom.Ellipse2D.Double oval;
	
	/**
	 * Create an oval of the given dimension
	 * @param x the x-coordinate of the upper left corner of the oval
	 * @param y the y-coordinate of the upper left corner of the oval
	 * @param w the oval's width
	 * @param h the oval's height
	 */
	public Oval(int x, int y, int w, int h){
		oval = new java.awt.geom.Ellipse2D.Double(x, y, w, h);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval((int)oval.x, (int)oval.y, 
				(int)oval.width, (int)oval.height);
		g.setColor(Color.BLACK);
		g.drawOval((int)oval.x, (int)oval.y, 
				(int)oval.width, (int)oval.height);
	}

	@Override
	public void move(int dx, int dy) {
		if(oval.getX() != dx || oval.getY() != dy){
			oval.setFrame(oval.x + dx, oval.y + dy, oval.width, oval.height);
			figureChanged(this);
		}
	}

	@Override
	public boolean contains(int x, int y) {
		return oval.contains(x, y);
	}

	@Override
	public void setBounds(Point origin, Point corner) {
		oval.setFrameFromDiagonal(origin, corner);
		figureChanged(this);
	}

	@Override
	public Rectangle getBounds() {
		return oval.getBounds();
	}

	@Override
	public List<FigureHandle> getHandles() {
		return null;
	}

}
