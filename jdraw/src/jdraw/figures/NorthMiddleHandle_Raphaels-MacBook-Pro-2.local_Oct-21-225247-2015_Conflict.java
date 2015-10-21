package jdraw.figures;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

public class NorthMiddleHandle extends AbstractMiddleHandle{

	public NorthMiddleHandle(Figure f) {
		super(f);
	}

	@Override
	public Point getLocation() {
		return new Point(owner.getBounds().x + owner.getBounds().width/2, owner.getBounds().y);
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
	}

	@Override
	public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
		java.awt.Rectangle r = owner.getBounds();
		corner = new Point(r.x, r.y +  r.height);
		corner2 = new Point(r.x + r.width, r.y + r.height);
	}

	@Override
	public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
		owner.setBounds(new Point(corner.x, y), corner2);
	}

}
