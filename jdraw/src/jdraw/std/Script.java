package jdraw.std;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import jdraw.framework.DrawCommand;

public class Script implements DrawCommand {
	
	List<DrawCommand> commands = new LinkedList<>();

	@Override
	public void redo() {
		ListIterator<DrawCommand> it = commands.listIterator();
		while(it.hasNext()) it.next().redo();
	}

	@Override
	public void undo() {
		ListIterator<DrawCommand> it = commands.listIterator();
		while(it.hasPrevious()) it.previous().undo();
	}
	
	public void add(DrawCommand command){
		commands.add(command);
	}
	
}
