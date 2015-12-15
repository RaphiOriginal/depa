package jdraw.test;

import jdraw.framework.Figure;

public class LineTest extends FigureTest{
	
	public Figure createFigure(){
		return new jdraw.figures.Line(0, 0, 20, 10);
	}

}
