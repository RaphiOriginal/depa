package jdraw.test;

import org.junit.Before;

import jdraw.framework.Figure;


public class RectangleTest extends FigureTest{

	public Figure createFigure(){
		return new jdraw.figures.Rect(0, 0, 20, 10);
	}

}
