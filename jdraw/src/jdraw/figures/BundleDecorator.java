package jdraw.figures;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

public class BundleDecorator extends AbstractDecorator {

	public BundleDecorator(Figure figure) {
		super(figure);
	}

	@Override
	public void move(int dx, int dy) {
		inner.move(dx, dy);
	}

	@Override
	public boolean contains(int x, int y) {
		return inner.contains(x, y);
	}

	@Override
	public void setBounds(Point origin, Point corner) {
		inner.setBounds(origin, corner);
	}

	@Override
	public Rectangle getBounds() {
		return inner.getBounds();
	}
	
	@Override
	public List<FigureHandle> getHandles() {
		List<FigureHandle> handles = new LinkedList<>();
		for(FigureHandle h: inner.getHandles()) {
			handles.add(new HandleDecorator(h));
		}
		return Collections.unmodifiableList(handles);
	}
	
	private final class HandleDecorator implements FigureHandle {
		private FigureHandle inner;
		
		public HandleDecorator(FigureHandle f) {
			this.inner = f;
		}

		@Override
		public Figure getOwner() {
			return inner.getOwner();
		}

		@Override
		public Point getLocation() {
			return inner.getLocation();
		}

		@Override
		public void draw(Graphics g) {
			inner.draw(g);
		}

		@Override
		public Cursor getCursor() {
			return Cursor.getDefaultCursor();
		}

		@Override
		public boolean contains(int x, int y) {
			return inner.contains(x, y);
		}

		@Override
		public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
			//inner.startInteraction(x, y, e, v);
		}

		@Override
		public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
			//inner.dragInteraction(x, y, e, v);
		}

		@Override
		public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
			//inner.stopInteraction(x, y, e, v);
		}
		
	}

}
