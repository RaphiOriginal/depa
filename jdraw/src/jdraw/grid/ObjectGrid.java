package jdraw.grid;

import java.awt.Point;

import jdraw.framework.DrawModelEvent;
import jdraw.framework.DrawModelListener;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.framework.PointConstrainer;

public class ObjectGrid implements PointConstrainer {
	private DrawView view;
	private static final int DISTANCE = 15;
	private Figure figure;
	private DrawModelListener listener;
	private Point p0;
	private boolean snapped = false;
	public ObjectGrid(DrawView v){
		view = v;
	}
	
	private boolean nearBy(Point a, Point b){
		return a.distance(b) < DISTANCE;
	}

	@Override
	public Point constrainPoint(Point p) {
		/*if(snapped) {
			if(nearBy(p0, p)) { return p0;}
			else { snapped = false; return p;}
		}
		for(Figure s : view.getSelection()){
			for(FigureHandle h : s.getHandles()){
				FigureHandle nearHandle = findNearHandleOf(h);
				if(nearHandle != null){
					snapped = true;
					int dx = nearHandle.getLocation().x - h.getLocation().x;
					int dy = nearHandle.getLocation().y - h.getLocation().y;
					return p0 = new Point(p.x + dx, p.y + dx);
				}
			}
		}*/
		for(Figure f: view.getModel().getFigures()){
			if(!view.getSelection().contains(f) && f != figure){
				for(FigureHandle h : f.getHandles()){
					Point pos = h.getLocation();
					if(nearBy(p, pos)) return pos;
				}
			}
		}
		return p;
	}

	@Override
	public int getStepX(boolean right) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getStepY(boolean down) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void activate() {
		listener = new DrawModelListener(){
			public void modelChanged(DrawModelEvent e){
				if (e.getType() == DrawModelEvent.Type.FIGURE_ADDED){
					figure = e.getFigure();
				}
			}
		};
		view.getModel().addModelChangeListener(listener);
		
	}
	
	private FigureHandle findNearHandleOf(FigureHandle h) {
		for (Figure f: view.getModel().getFigures()){
			if(!view.getSelection().contains(f)){
				for( FigureHandle nh : f.getHandles()){
					if(nearBy(h.getLocation(), nh.getLocation()));
					return nh;
				}
			}
		}
		return null;
	}

	@Override
	public void deactivate() {
		view.getModel().removeModelChangeListener(listener);
		
	}

	@Override
	public void mouseDown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseUp() {
		// TODO Auto-generated method stub
		
	}

}
