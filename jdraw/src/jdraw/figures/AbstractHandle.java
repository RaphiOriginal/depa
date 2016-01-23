package jdraw.figures;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

import jdraw.framework.DrawCommandHandler;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.std.SetBoundsCommand;

public abstract class AbstractHandle implements FigureHandle{
	private final int HANDLER_SIZE = 6;
	private final int HANDLER_CORRECTION = HANDLER_SIZE/2;
	protected Figure owner;
	protected Point corner;

	public AbstractHandle(Figure f){
		owner = f;
	}
	
	@Override
	public Figure getOwner() {
		return owner;
	}

	@Override
	public abstract Point getLocation();

	@Override
	public void draw(Graphics g) {
		Point loc = getLocation();
		g.setColor(Color.WHITE);
		g.fillRect(loc.x - HANDLER_CORRECTION, loc.y - HANDLER_CORRECTION, HANDLER_SIZE, HANDLER_SIZE);
		g.setColor(Color.BLACK);
		g.drawRect(loc.x - HANDLER_CORRECTION, loc.y - HANDLER_CORRECTION, HANDLER_SIZE, HANDLER_SIZE);
	}

	@Override
	public abstract Cursor getCursor();

	@Override
	public boolean contains(int x, int y) {
		if(x < getLocation().x - HANDLER_CORRECTION || x > getLocation().x + HANDLER_CORRECTION) return false;
		if(y < getLocation().y - HANDLER_CORRECTION || y > getLocation().y + HANDLER_CORRECTION) return false;
		return true;
	}

	@Override
	public abstract void startInteraction(int x, int y, MouseEvent e, DrawView v);

	@Override
	public abstract void dragInteraction(int x, int y, MouseEvent e, DrawView v);

	@Override
	public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
		v.getModel().getDrawCommandHandler().endScript();
		corner = null;
	}
	
	protected void createCommand(DrawView v, Figure f, Point toOrig, Point toCorn){
		DrawCommandHandler cmd = v.getModel().getDrawCommandHandler();
		cmd.addCommand(new SetBoundsCommand(f, f.getBounds().getLocation(), corner, toCorn, toCorn));
	}
}
