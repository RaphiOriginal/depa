package jdraw.figures;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import jdraw.framework.DrawModel;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

public class Group implements Figure{
	List<Figure> parts;
	
	public Group(List<Figure> figures, DrawModel model){
		if(figures == null || figures.size() <= 1) throw new IllegalArgumentException();
		parts = new LinkedList<Figure>();
		for(Figure f: model.getFigures()){
			if(figures.contains(f)) parts.add(f);
		}
	}

	@Override
	public void draw(Graphics g) {
		for(Figure f: parts){
			f.draw(g);
		}
	}

	@Override
	public void move(int dx, int dy) {
		for(Figure f: parts){
			f.move(dx, dy);
		}
	}

	@Override
	public boolean contains(int x, int y) {
		for(Figure f: parts){
			if(f.contains(x, y)) return true;
		}
		return false;
	}

	@Override
	public void setBounds(Point origin, Point corner) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getBounds() {
		Rectangle rect = null;
		for(Figure f: parts){
			if(rect == null) rect = f.getBounds();
			rect.add(f.getBounds());
		}
		return rect;
	}

	@Override
	public List<FigureHandle> getHandles() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Iterable<Figure> getParts(){
		return Collections.unmodifiableList(parts);
	}

	@Override
	public void addFigureListener(FigureListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeFigureListener(FigureListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Figure clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
