/*
 * Copyright (c) 2000-2016 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.std;

import java.util.LinkedList;

import jdraw.framework.DrawCommandHandler;
import jdraw.framework.DrawModel;
import jdraw.framework.DrawModelEvent;
import jdraw.framework.DrawModelListener;
import jdraw.framework.Figure;

/**
 * Provide a standard behavior for the drawing model. This class initially does not implement the methods
 * in a proper way.
 * It is part of the course assignments to do so.
 * @author TODO add your name here
 *
 */
public class StdDrawModel implements DrawModel {
	private LinkedList<Figure> figures = new LinkedList<Figure>();
	private LinkedList<DrawModelListener> listeners = new LinkedList<DrawModelListener>();

	@Override
	public void addFigure(Figure f) {
		figures.add(f);
		modelChanged(f, DrawModelEvent.Type.FIGURE_ADDED);
	}

	@Override
	public Iterable<Figure> getFigures() {
		return figures;
	}

	@Override
	public void removeFigure(Figure f) {
		// TODO to be implemented  
		System.out.println("StdDrawModel.removeFigure has to be implemented");
	}

	@Override
	public void addModelChangeListener(DrawModelListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeModelChangeListener(DrawModelListener listener) {
		// TODO to be implemented  
		System.out.println("StdDrawModel.removeModelChangeListener has to be implemented");
	}

	/** The draw command handler. Initialized here with a dummy implementation. */
	// TODO initialize with your implementation of the undo/redo-assignment.
	private DrawCommandHandler handler = new EmptyDrawCommandHandler();

	/**
	 * Retrieve the draw command handler in use.
	 * @return the draw command handler.
	 */
	public DrawCommandHandler getDrawCommandHandler() {
		return handler;
	}

	@Override
	public void setFigureIndex(Figure f, int index) {
		// TODO to be implemented  
		System.out.println("StdDrawModel.setFigureIndex has to be implemented");
	}

	@Override
	public void removeAllFigures() {
		// TODO to be implemented  
		System.out.println("StdDrawModel.removeAllFigures has to be implemented");
	}
	
	private void modelChanged(Figure f, DrawModelEvent.Type t){
		DrawModelEvent event = new DrawModelEvent(this, f, t);
		for(DrawModelListener l:listeners){
			l.modelChanged(event);
		}
	}

}
