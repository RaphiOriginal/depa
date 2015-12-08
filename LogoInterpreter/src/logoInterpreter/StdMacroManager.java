package logoInterpreter;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import logoInterpreter.commands.Command;
import logoInterpreter.commands.MacroCommand;
import logoInterpreter.commands.NotFoundCommand;

public class StdMacroManager implements MacroManager {
	private final LogoInterpreter interpreter;
	private boolean recording;
	private Map<String, MacroCommand> macros;
	private String name;

	public StdMacroManager(LogoInterpreter interpreter) {
		this.interpreter = interpreter;
		recording = false;
		macros = new HashMap<>();
		name = "";
	}
	/** 
	 * Gibt an, ob gerade ein Makro aufgezeichnet wird.
	 */
	@Override
	public boolean isRecordingMacro() {
		return recording;
	}
	/**
	 * Zeichnet ein Command auf. Der LogoInterpreter stellt dabei sicher, 
	 * dass nur Commands in den MacroManager kommen, welche auf der Schildkröte 
	 * ausgeführt werden können.  Ebenfalls wird vom LogoInterpreter sichergestellt, 
	 * dass diese Methode nur aufgerufen wird, falls ein Makro aufgezeichnet wird.
	 * 
	 */
	@Override
	public void handleCommand(Command command) {
		macros.get(name).add(command);
		command.execute();
	}
	/**
	 * Beginnt mit der Aufzeichnung eines neuen Makros. Nach Beendigung des Makros 
	 * sollte der Zustand vor der Aufzeichnung des Makros wiederhergestellt werden.
	 */
	@Override
	public void startMacro(String name) {
		recording = true;
		this.name = name;
		macros.put(name, new MacroCommand(name));
		interpreter.setColor(Color.RED);
	}
	/**
	 * Gibt das Makro mit dem Namen name zurück. Falls das Makro nicht 
	 * existiert, wird ein NotFoundCommand zurückgegeben.
	 */
	@Override
	public Command getCommand(String name) {
		if(macros.containsKey(name)) {
			return macros.get(name);
		} else {
			return new NotFoundCommand(name);
		}
	}

	/**
	 * Speichert und beendet die Aufzeichnung des aktuellen Makros.
	 */
	@Override
	public void endMacro() {
		recording = false;
		this.name = "";
		interpreter.setColor(Color.BLACK);
		interpreter.repaint();
	}

}
