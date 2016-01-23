package jdraw.std;

import java.util.Stack;

import jdraw.framework.DrawCommand;
import jdraw.framework.DrawCommandHandler;

public class MyDrawCommandHandler implements DrawCommandHandler {
	
	Stack<DrawCommand> undo = new Stack<>();
	Stack<DrawCommand> redo = new Stack<>();
	Script script = null;

	@Override
	public void addCommand(DrawCommand cmd) {
		redo.clear();
		if(script != null) {
			script.add(cmd);
		} else {
			undo.push(cmd);
		}
	}

	@Override
	public void undo() {
		if(undoPossible()){
			DrawCommand tmp = undo.pop();
			tmp.undo();
			redo.push(tmp);
		}
	}

	@Override
	public void redo() {
		if(redoPossible()) {
			DrawCommand tmp = redo.pop();
			tmp.redo();
			undo.push(tmp);
		}
	}

	@Override
	public boolean undoPossible() {
		return !undo.isEmpty();
	}

	@Override
	public boolean redoPossible() {
		return !redo.isEmpty();
	}

	@Override
	public void beginScript() {
		if(script != null) throw new IllegalStateException();
		script = new Script();
	}

	@Override
	public void endScript() {
		if(script == null) throw new IllegalStateException();
		Script tmp = script;
		script = null;
		if(tmp.commands.size() > 0){
			addCommand(tmp);
		}
	}

	@Override
	public void clearHistory() {
		undo.clear();
		redo.clear();
		script = null;
	}

}
