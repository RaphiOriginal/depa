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
		showCalculator("AWT");
	}

	private static void showCalculator(String version) {
		setStrategy(version);
		Frame f = newFrame("Calculator");
		final Field x = newField(10, true);
		final Field y = newField(10, true);
		final Field sum = newField(10, false);
		Button b = newButton("Compute", new ActionListener() {
			@Override
			public void actionPerformed(Component source) {
				int ix = Integer.parseInt(x.getText());
				int iy = Integer.parseInt(y.getText());
				sum.setText("" + (ix + iy));
			}
		});
		f.setGrid(4, 2);
		f.add(newLabel("x"));
		f.add(x);
		f.add(newLabel("y"));
		f.add(y);
		f.add(newLabel("Summe"));
		f.add(sum);
		f.add(b);
		f.setVisible(true);
	}
	
	static private void setStrategy(String version){
		switch(version){
		case "AWT":
			g = new AWTStrategy();
			break;
		case "SWING":
			g = new SwingStrategy();
			break;
		case "SWT":
			g = new SWTStrategy();
			break;
		case "FX":
			g = new FXStrategy();
			break;
		default:
			throw new IllegalStateException();
		}
	}

	static private Frame newFrame(String title) {
		return g.newFrame(title);
	}

	static private Field newField(int width, boolean enabled) {
		return g.newFieldint(width, enabled);
	}

	static private Button newButton(String label,
			Components.ActionListener listener) {
		return g.newButton(label, listener);
	}

	static private Label newLabel(String text) {
		return g.newLabel(text);
	}
}
