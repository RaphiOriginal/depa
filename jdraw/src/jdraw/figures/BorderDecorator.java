package jdraw.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import jdraw.framework.Figure;

public class BorderDecorator extends AbstractDecorator {
	
	private static final int BORDER_OFFSET = 5;

	public BorderDecorator(Figure figure) {
		super(figure);
	}
	
	@Override
	public void draw(Graphics g){
		super.draw(g);
		Rectangle r = getBounds();
		g.setColor(Color.white);
		g.drawLine(r.x, r.y, r.x, r.y + r.height);
		g.drawLine(r.x, r.y, r.x + r.width, r.y);
		g.setColor(Color.gray);
		g.drawLine(r.x + r.width, r.y, r.x + r.width, r.y + r.height);
		g.drawLine(r.x, r.y + r.height, r.x + r.width, r.y + r.height);
	}

	@Override
	public void move(int dx, int dy) {
		inner.move(dx, dy);
	}

	@Override
	public boolean contains(int x, int y) {
		return getBounds().contains(x, y);
	}

	@Override
	public void setBounds(Point origin, Point corner) {
		inner.setBounds(origin, corner);
		
	}

	@Override
	public Rectangle getBounds() {
		Rectangle r = inner.getBounds();
		r.grow(BORDER_OFFSET, BORDER_OFFSET);
		return r;
	}

}
