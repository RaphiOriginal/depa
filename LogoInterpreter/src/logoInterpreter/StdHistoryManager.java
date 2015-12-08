package logoInterpreter;

import java.util.LinkedList;
import java.util.List;

import logoInterpreter.commands.Command;

public class StdHistoryManager implements HistoryManager {
	private final LogoInterpreter interpreter;
	private List<Command> history;
	private int focus;

	public StdHistoryManager(LogoInterpreter interpreter) {
		this.interpreter = interpreter;
		this.history  = new LinkedList<>();
		this.focus = 0;
	}

	/**
	 * Fügt ein Command in die History ein. Für die Ausführung dieses Commands ist
	 * der Aufrufer von add verantwortlich. Die Methode add registriert es
	 * lediglich in der History für undo/redo.
	 */
	@Override
	public void add(Command command) {
		if(focus == history.size()){
			history.add(command);
		} else {
			for(int i = focus; i < history.size(); i++){
				history.remove(focus);
			}
			history.add(command);
		}
		focus++;
	}

	/**
	 * Leert die History.
	 */
	@Override
	public void clear() {
		history.clear();
		focus = 0;
	}

	/**
	 * Macht einen Schritt in der History rückgängig. Wenn das nicht möglich ist,
	 * soll eine Meldung in die Konsole geschrieben werden. Die undo-Methode ist
	 * auch dafür verantwortlich, dass die Darstellung im Fenster aktualisiert
	 * wird. Dazu muss interpreter.repaint() aufgerufen werden.
	 */
	@Override
	public void undo() {
		if(focus > 0){
			focus--;
			interpreter.repaint();
		} else {
			System.out.println("No Undo possible!");
		}
	}

	/**
	 * Stellt einen Schritt in der History wieder her. Wenn das nicht möglich ist,
	 * soll eine Meldung in die Konsole geschrieben werden. Die redo-Methode ist
	 * auch dafür verantwortlich, dass die Darstellung im Fenster aktualisiert
	 * wird. Dazu muss interpreter.repaint() aufgerufen werden.
	 */
	@Override
	public void redo() {
		if(focus < history.size()){
			focus++;
			interpreter.repaint();
		} else {
			System.out.println("Redo not possible!");
		}
	}

	/**
	 * Gibt sämtliche bisherige Commands der History zurück. Wird vor allem von
	 * der repaint()-Methode des LogoInterpreters verwendet.
	 */
	@Override
	public Iterable<Command> getCommands() {
		List<Command> copy = new LinkedList<>();
		for(int i = 0; i < focus; i++){
			copy.add(history.get(i));
		}
		return copy;
	}
}
