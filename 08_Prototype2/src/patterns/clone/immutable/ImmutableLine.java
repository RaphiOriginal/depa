package patterns.clone.immutable;

import java.awt.Point;

public class ImmutableLine implements Cloneable {
	private Point start, end;
	
	public ImmutableLine(Point start, Point end) {
		this.start = start;
		this.end = end;
	}
	
	public Point getStartPoint() {
		return (Point)start.clone();
	}

	public ImmutableLine setStartPoint(Point start) {
		return new ImmutableLine(start, end);
	}

	public Point getEndPoint() {
		return (Point)end.clone();
	}

	public ImmutableLine setEndPoint(Point end) {
		return new ImmutableLine(start, end);
	}

	@Override
	public ImmutableLine clone() {
		return this;
	}

	@Override
	public String toString() {
	   return String.format("Line[start=%s, end=%s]", start, end);
	}
}
