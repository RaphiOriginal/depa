/*
 * Copyright (c) 2000-2016 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.std;

import java.util.Iterator;
import java.util.LinkedList;

import jdraw.framework.DrawCommandHandler;
import jdraw.framework.DrawModel;
import jdraw.framework.DrawModelEvent;
import jdraw.framework.DrawModelListener;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureListener;

/**
 * Provide a standard behavior for the drawing model. This class initially does not implement the methods
 * in a proper way.
 * It is part of the course assignments to do so.
 * @author TODO add your name here
 *
 */
public class StdDrawModel implements DrawModel, FigureListener{
	private LinkedList<Figure> figures = new LinkedList<Figure>();
	private LinkedList<DrawModelListener> listeners = new LinkedList<DrawModelListener>();

	@Override
	public void addFigure(Figure f) {
		if(!figures.contains(f)){
			figures.add(f);
			f.addFigureListener(this);
			modelChanged(f, DrawModelEvent.Type.FIGURE_ADDED);
			handler.addCommand(new AddFigureCommand(this, f));
		}
	}

	@Override
	public Iterable<Figure> getFigures() {
		return figures;
	}

	@Override
	public void removeFigure(Figure f) {
			if(figures.remove(f)) modelChanged(f, DrawModelEvent.Type.FIGURE_REMOVED);
			f.removeFigureListener(this);
	}

	@Override
	public void addModelChangeListener(DrawModelListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeModelChangeListener(DrawModelListener listener) {
		listeners.remove(listener);
	}

	/** The draw command handler. Initialized here with a dummy implementation. */
	// TODO initialize with your implementation of the undo/redo-assignment.
	private DrawCommandHandler handler = new MyDrawCommandHandler();

	/**
	 * Retrieve the draw command handler in use.
	 * @return the draw command handler.
	 */
	public DrawCommandHandler getDrawCommandHandler() {
		return handler;
	}

	@Override
	public void setFigureIndex(Figure f, int index) {
		if(index < 0 || index > figures.size() || !figures.contains(f)) throw new IllegalArgumentException();
		figures.remove(f);
		figures.add(index, f);
		modelChanged(f, DrawModelEvent.Type.DRAWING_CHANGED);
	}

	@Override
	public void removeAllFigures() {
		Iterator<Figure> it = figures.iterator();
		while(it.hasNext()){
			Figure f = it.next();
			it.remove();
			f.removeFigureListener(this);
			modelChanged(f, DrawModelEvent.Type.DRAWING_CLEARED);
		}
	}
	
	private void modelChanged(Figure f, DrawModelEvent.Type t){
		DrawModelEvent event = new DrawModelEvent(this, f, t);
		for(DrawModelListener l:listeners){
			l.modelChanged(event);
		}
	}

	@Override
	public void figureChanged(FigureEvent e) {
		modelChanged(e.getFigure(), DrawModelEvent.Type.FIGURE_CHANGED);
	}

}
