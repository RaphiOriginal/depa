package patterns.factory.gui;

import patterns.factory.gui.Components.Label;
import patterns.factory.gui.Components.Button;
import patterns.factory.gui.Components.Field;
import patterns.factory.gui.Components.Frame;

public interface GUIStrategy {
	public Label newLabel(String text);
	public Button newButton(String label, Components.ActionListener listener);
	public Field newFieldint(int width, boolean enabled);
	public Frame newFrame(String title);
}
