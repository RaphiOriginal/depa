package patterns.factory.gui;

import patterns.factory.gui.Components.Frame;

public class CalculatorFactoryImpl implements CalculatorFactory {
	
	GUIStrategy g;

	public void setComponentFactory(GUIStrategy fact) {
		g = fact;
	}

	public Frame newCalculatorFrame() {
		// TODO this method is invoked by the main program to get the frame to be shown.
		return null;
	}

}
