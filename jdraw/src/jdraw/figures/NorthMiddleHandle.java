package jdraw.figures;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

public class NorthMiddleHandle extends AbstractHandle{

	public NorthMiddleHandle(Figure f) {
		super(f);
	}

	@Override
	public Point getLocation() {
		return new Point(owner.getBounds().x + owner.getBounds().width/2, owner.getBounds().y);
	}

	@Override
	public Cursor getCursor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
		// TODO Auto-generated method stub
		
	}

}
