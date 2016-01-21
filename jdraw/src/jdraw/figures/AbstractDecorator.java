package jdraw.figures;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

public abstract class AbstractDecorator extends AbstractGeometry {
	
	protected Figure inner;
	
	public AbstractDecorator(Figure figure) {
		inner = figure;
	}
	
	public Figure getDecoratedFigure() {
		return inner;
	}
	
	@Override
	public void draw(Graphics g) {
		inner.draw(g);
	}
	
	@Override
	public AbstractDecorator clone() {
		AbstractDecorator f = (AbstractDecorator) super.clone();
		f.inner = inner.clone();
		return f;
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
		private final FigureHandle inner;
		
		public HandleDecorator(FigureHandle handle){
			this.inner = handle;
		}

		@Override
		public Figure getOwner() {
			return AbstractDecorator.this;
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
			return inner.getCursor();
		}

		@Override
		public boolean contains(int x, int y) {
			return inner.contains(x, y);
		}

		@Override
		public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
			inner.startInteraction(x, y, e, v);
		}

		@Override
		public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
			inner.dragInteraction(x, y, e, v);
		}

		@Override
		public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
			inner.stopInteraction(x, y, e, v);
		}
	}
}
