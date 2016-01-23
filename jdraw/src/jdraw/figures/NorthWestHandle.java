package jdraw.figures;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

public class NorthWestHandle extends AbstractHandle{
	
	public NorthWestHandle(Figure f){
		super(f);
	}

	@Override
	public Point getLocation() {
		return owner.getBounds().getLocation();
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
	}

	@Override
	public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
		v.getModel().getDrawCommandHandler().endScript();
		java.awt.Rectangle r = owner.getBounds();
		corner = new Point(r.x + r.width, r.y +  r.height);
	}
	
	@Override
	public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
		createCommand(v, owner, new Point(x, y), corner);
		owner.setBounds(new Point(x, y), corner);
	}

}
