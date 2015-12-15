package patterns.factory.gui;

import patterns.factory.gui.Components.ActionListener;
import patterns.factory.gui.Components.Button;
import patterns.factory.gui.Components.Component;
import patterns.factory.gui.Components.Field;
import patterns.factory.gui.Components.Frame;
import patterns.factory.gui.Components.Label;

public class Gui01FactoryMethods {
	static GUIStrategy g;
	
	public static void main(String[] args) {
		showCalculator(new SwingStrategy());
	}

	private static void showCalculator(GUIStrategy g) {
		Frame f = g.newFrame("Calculator");
		final Field x = g.newFieldint(10, true);
		final Field y = g.newFieldint(10, true);
		final Field sum = g.newFieldint(10, false);
		Button b = g.newButton("Compute", new ActionListener() {
			@Override
			public void actionPerformed(Component source) {
				int ix = Integer.parseInt(x.getText());
				int iy = Integer.parseInt(y.getText());
				sum.setText("" + (ix + iy));
			}
		});
		f.setGrid(4, 2);
		f.add(g.newLabel("x"));
		f.add(x);
		f.add(g.newLabel("y"));
		f.add(y);
		f.add(g.newLabel("Summe"));
		f.add(sum);
		f.add(b);
		f.setVisible(true);
	}
}
