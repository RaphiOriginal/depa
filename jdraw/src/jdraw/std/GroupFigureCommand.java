package jdraw.std;

import jdraw.figures.Group;
import jdraw.framework.DrawCommand;
import jdraw.framework.DrawModel;
import jdraw.framework.Figure;

public class GroupFigureCommand implements DrawCommand {
	
	DrawModel m;
	Group g;
	boolean insertGroup;
	
	public GroupFigureCommand(DrawModel m, Group g, boolean insertGroup){
		this.m = m;
		this.g = g;
		this.insertGroup = insertGroup;
	}

	@Override
	public void redo() {
		if(insertGroup){
			m.addFigure(g);
			for(Figure f:g.getParts()){
				m.removeFigure(f);
			}
		} else {
			m.removeFigure(g);
			for(Figure f:g.getParts()){
				m.addFigure(f);
			}
		}
	}

	@Override
	public void undo() {
		if(insertGroup){
			m.removeFigure(g);
			for(Figure f:g.getParts()){
				m.addFigure(f);
			}
		} else {
			m.addFigure(g);
			for(Figure f:g.getParts()){
				m.removeFigure(f);
			}
		}
	}

}
