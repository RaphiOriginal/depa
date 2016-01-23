package jdraw.figures;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

public class SouthMiddleHandle extends AbstractMiddleHandle{

	public SouthMiddleHandle(Figure f) {
		super(f);
	}

	@Override
	public Point getLocation() {
		return new Point(owner.getBounds().x + owner.getBounds().width / 2, owner.getBounds().y + owner.getBounds().height);
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
	}

	@Override
	public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
		v.getModel().getDrawCommandHandler().endScript();
		java.awt.Rectangle r = owner.getBounds();
		corner = new Point(r.x, r.y);
		corner2 = new Point(r.x + r.width, r.y);
	}

	@Override
	public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
		createCommand(v, owner, corner, new Point(corner2.x, y));
		owner.setBounds(corner, new Point(corner2.x, y));
	}

}
