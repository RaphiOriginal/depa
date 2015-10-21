package jdraw.figures;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

public abstract class AbstractMiddleHandle extends AbstractHandle{
	protected Point corner2;

	public AbstractMiddleHandle(Figure f) {
		super(f);
	}

	@Override
	public abstract Point getLocation();

	@Override
	public abstract Cursor getCursor();

	@Override
	public abstract void startInteraction(int x, int y, MouseEvent e, DrawView v);

	@Override
	public abstract void dragInteraction(int x, int y, MouseEvent e, DrawView v);

}
