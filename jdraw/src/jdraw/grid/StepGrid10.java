package jdraw.grid;

import java.awt.Point;

import jdraw.framework.PointConstrainer;

public class StepGrid10 implements PointConstrainer{
	
	public final int STEP = 10;

	@Override
	public Point constrainPoint(Point p) {
		int x = Math.round((p.x + STEP/2)/STEP);
		int y = Math.round((p.y + STEP/2)/STEP);
		return new Point(x, y);
	}

	@Override
	public int getStepX(boolean right) {
		if(!right) return 0 - STEP;
		return STEP;
	}

	@Override
	public int getStepY(boolean down) {
		if(!down) return 0 - STEP;
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
