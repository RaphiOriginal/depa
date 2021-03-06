package patterns.factory.gui;

import patterns.factory.gui.Components.ActionListener;
import patterns.factory.gui.Components.Button;
import patterns.factory.gui.Components.Field;
import patterns.factory.gui.Components.Frame;
import patterns.factory.gui.Components.Label;

public class SWTStrategy implements GUIStrategy {

	@Override
	public Label newLabel(String text) {
		return new ComponentsSWT.LabelSWT(text);
	}

	@Override
	public Button newButton(String label, ActionListener listener) {
		return new ComponentsSWT.ButtonSWT(label, listener);
	}

	@Override
	public Field newFieldint(int width, boolean enabled) {
		return new ComponentsSWT.FieldSWT(enabled);
	}

	@Override
	public Frame newFrame(String title) {
		return new ComponentsSWT.FrameSWT(title);
	}

}
