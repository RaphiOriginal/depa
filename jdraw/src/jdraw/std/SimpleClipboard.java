package jdraw.std;

import java.util.LinkedList;
import java.util.List;

import jdraw.framework.Figure;

public class SimpleClipboard {
	private final List<Figure> figures = new LinkedList<>();
	
	public void add(Figure figure) {
		figures.add(figure);
	}
	
	public List<Figure> getFigures(){
		return figures;
	}
	
	public void clear() {
		figures.clear();
	}
}
