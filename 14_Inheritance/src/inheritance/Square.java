package inheritance;

public class Square {

	@SuppressWarnings("unused")
	private int x, y, w;

	public Square(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.w = size;
	}

	public void setWidth(int w) { this.w = w; }
	public int getWidth() { return w; }
}