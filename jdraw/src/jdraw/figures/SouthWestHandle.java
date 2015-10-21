package jdraw.figures;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

public class SouthWestHandle extends AbstractHandle{

	public SouthWestHandle(Figure f){
		super(f);
	}

	@Override
	public Point getLocation() {
		return new Point(owner.getBounds().x, owner.getBounds().y + owner.getBounds().height);
	}
	
	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR);
	}

	@Override
	public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
		java.awt.Rectangle r = owner.getBounds();
		corner = new Point(r.x + r.width, r.y);
	}
	
	@Override
	public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
		owner.setBounds(new Point(x, corner.y), new Point(corner.x, y));
	}

}
