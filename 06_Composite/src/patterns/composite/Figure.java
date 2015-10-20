package patterns.composite;

public abstract class Figure {
	protected boolean isInGroup = false;
	public abstract void draw(String prefix);
}
