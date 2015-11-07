package patterns.observer.multimodel;

import java.awt.Color;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class ColorModel {
	private int red, green, blue;

	public enum ColorChannel {
		RED, GREEN, BLUE
	}

	public ColorModel(Color c) {
		this.red = c.getRed();
		this.green = c.getGreen();
		this.blue = c.getBlue();
	}

	private Color color;
	private Map<ColorListener, EnumSet<ColorChannel>> listeners = new HashMap<ColorListener, EnumSet<ColorChannel>>();

	public void addColorListener(ColorListener l, EnumSet<ColorChannel> set) {
		listeners.put(l, set);
	}

	public void removeColorListener(ColorListener l) {
		listeners.remove(l);
	}

	public void setColor(Color c) {
		EnumSet<ColorChannel> s = EnumSet.noneOf(ColorChannel.class);
		if(red != c.getRed()) s.add(ColorChannel.RED);
		if(green != c.getGreen()) s.add(ColorChannel.GREEN);
		if(blue != c.getBlue()) s.add(ColorChannel.BLUE);
		setRed(c.getRed());
		setGreen(c.getGreen());
		setBlue(c.getBlue());
		this.color = new Color(red, green, blue);
		notifyListeners(s);
	}

	public Color getColor() {
		return color;
	}

	public void setRed(int red) {
		this.red = red;
		this.color = new Color(red, green, blue);
		notifyListeners(EnumSet.of(ColorChannel.RED));
	}

	public void setGreen(int green) {
		this.green = green;
		this.color = new Color(red, green, blue);
		notifyListeners(EnumSet.of(ColorChannel.GREEN));
	}

	public void setBlue(int blue) {
		this.blue = blue;
		this.color = new Color(red, green, blue);
		notifyListeners(EnumSet.of(ColorChannel.BLUE));
	}

	private void notifyListeners(EnumSet<ColorChannel> channels) {
		for (ColorListener l : listeners.keySet()) {
			if (!Collections.disjoint(listeners.get(l), channels))
				l.colorValueChanged(color);
		}
	}

}
