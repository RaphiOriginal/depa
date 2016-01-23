package jdraw.std;

import jdraw.framework.DrawCommand;
import jdraw.framework.DrawModel;
import jdraw.framework.Figure;

public class AddFigureCommand implements DrawCommand {
	
	Figure f;
	
	DrawModel m;
	
	public AddFigureCommand(DrawModel m, Figure f){
		this.m = m;
		this.f = f;
	}

	@Override
	public void redo() {
		m.addFigure(f);
	}

	@Override
	public void undo() {
		m.removeFigure(f);
	}

}
