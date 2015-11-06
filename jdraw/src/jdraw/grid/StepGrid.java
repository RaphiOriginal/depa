package jdraw.grid;

import java.awt.Point;

import jdraw.framework.PointConstrainer;

public class StepGrid implements PointConstrainer{
	
	public final int STEP;
	
	public StepGrid(int step){
		STEP = Math.max(1,step);
	}

	@Override
	public Point constrainPoint(Point p) {
		int x = ((p.x + STEP/2)/STEP)*STEP;
		int y = ((p.y + STEP/2)/STEP)*STEP;
		return new Point(x, y);
	}

	@Override
	public int getStepX(boolean right) {
		if(!right) return -STEP;
		return STEP;
	}

	@Override
	public int getStepY(boolean down) {
		if(!down) return -STEP;
		return STEP;
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
		
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
