package jdraw.grid;

import java.awt.Point;

import jdraw.framework.PointConstrainer;

public class SimpleGrid implements PointConstrainer{

	@Override
	public Point constrainPoint(Point p) {
		System.out.println("SimpleGrid:constrainPoint: " + p);
		return p;
	}

	@Override
	public int getStepX(boolean right) {
		return 1;
	}

	@Override
	public int getStepY(boolean down) {
		return 1;
	}

	@Override
	public void activate() {
		System.out.println("SimpleGrid:achtivate()");
	}

	@Override
	public void deactivate() {
		System.out.println("SimpleGrid:deachtivate()");
	}

	@Override
	public void mouseDown() {
		System.out.println("SimpleGrid:mouseDown()");
	}

	@Override
	public void mouseUp() {
		System.out.println("SimpleGrid:mouseUp()");
	}

}
