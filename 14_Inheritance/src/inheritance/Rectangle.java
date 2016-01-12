package inheritance;

/*
 * This mutable Rectangle class uses class Square without inheriting
 * its interface, i.e. a rectangle instance is not a square.
 */
public class Rectangle {
	private Square s;
	private int h;

	public Rectangle(int x, int y, int w, int h) {
		this.s = new Square(x, y, w);
		this.h = h;
	}

	public void setWidth(int w) { s.setWidth(w); }
	public int getWidth() { return s.getWidth(); }

	public void setHeight(int h) { this.h = h; }
	public int getHeight() { return h; }
}
