package patterns.factory.gui;

import patterns.factory.gui.Components.ActionListener;
import patterns.factory.gui.Components.Button;
import patterns.factory.gui.Components.Field;
import patterns.factory.gui.Components.Frame;
import patterns.factory.gui.Components.Label;

public class FXStrategy implements GUIStrategy {

	@Override
	public Label newLabel(String text) {
		return new ComponentsFX.LabelFX(text);
	}

	@Override
	public Button newButton(String label, ActionListener listener) {
		return new ComponentsFX.ButtonFX(label, listener);
	}

	@Override
	public Field newFieldint(int width, boolean enabled) {
		return new ComponentsFX.FieldFX(width, enabled);
	}

	@Override
	public Frame newFrame(String title) {
		return new ComponentsFX.FrameFX(title);
	}

}
