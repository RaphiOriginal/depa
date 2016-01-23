package jdraw.std;

import java.awt.Point;

import jdraw.framework.DrawCommand;
import jdraw.framework.Figure;

public class SetBoundsCommand implements DrawCommand {
	Figure f;
	Point fromOrig, fromCorn, toOrig, toCorn;
	
	public SetBoundsCommand(Figure f, Point fromOrig, Point fromCorn, Point toOrig, Point toCorn){
		this.f = f;
		this.fromCorn = fromCorn;
		this.fromOrig = fromOrig;
		this.toCorn = toCorn;
		this.toOrig = toOrig;
	}

	@Override
	public void redo() {
		f.setBounds(toOrig, toCorn);
	}

	@Override
	public void undo() {
		f.setBounds(fromOrig, fromCorn);
	}

}
