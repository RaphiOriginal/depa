package jdraw.figures;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

public class WestMiddleHandle extends AbstractMiddleHandle{

	public WestMiddleHandle(Figure f) {
		super(f);
	}

	@Override
	public Point getLocation() {
		return new Point(owner.getBounds().x, owner.getBounds().y + owner.getBounds().height / 2);
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR);
	}

	@Override
	public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
		v.getModel().getDrawCommandHandler().endScript();
		java.awt.Rectangle r = owner.getBounds();
		corner = new Point(r.x + r.width, r.y);
		corner2 = new Point(r.x + r.width, r.y + r.height);
	}

	@Override
	public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
		createCommand(v, owner, new Point(x, corner.y), corner2);
		owner.setBounds(new Point(x, corner.y), corner2);
	}

}
