package jdraw.figures;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

public abstract class AbstractGeometry implements Figure{

	@Override
	public abstract void draw(Graphics g);

	@Override
	public abstract void move(int dx, int dy);

	@Override
	public abstract boolean contains(int x, int y);

	@Override
	public abstract void setBounds(Point origin, Point corner);

	@Override
	public abstract Rectangle getBounds();

	/**
	 * Returns a list of 8 handles for this Rectangle.
	 * @return all handles that are attached to the targeted figure.
	 * @see jdraw.framework.Figure#getHandles()
	 */	
	@Override
	public List<FigureHandle> getHandles() {
		List<FigureHandle> handles = new LinkedList<>();
		handles.add(new NorthWestHandle(this));
		handles.add(new NorthEastHandle(this));
		handles.add(new SouthWestHandle(this));
		handles.add(new SouthEastHandle(this));
		handles.add(new NorthMiddleHandle(this));
		handles.add(new WestMiddleHandle(this));
		handles.add(new EastMiddleHandle(this));
		handles.add(new SouthMiddleHandle(this));
		return handles;
	}

	@Override
	public void addFigureListener(FigureListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeFigureListener(FigureListener listener) {
		listeners.remove(listener);
	}

	@Override
	public Figure clone() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private List<FigureListener> listeners = new LinkedList<FigureListener>();
	
	protected void figureChanged(Figure f){
		FigureEvent e = new FigureEvent(f);
		for(FigureListener l: listeners){
			l.figureChanged(e);
		}
	}

}
