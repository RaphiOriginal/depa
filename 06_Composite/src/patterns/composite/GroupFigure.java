package patterns.composite;

import java.util.LinkedList;
import java.util.List;

public class GroupFigure extends Figure {
	private String name;
	private List<Figure> figures = new LinkedList<Figure>();

	public GroupFigure(String name, Figure... figures) {
		this.name = name;
		for (Figure f : figures) {
			addFigure(f);
		}
	}

	private void addFigure(Figure f) {
		if(f.isInGroup) throw new IllegalArgumentException();
		f.isInGroup = true;
		figures.add(f);
	}

	public void draw(String prefix) {
		System.out.println(prefix + name);
		for (Figure f : figures) {
			f.draw(prefix + ">>");
		}
	}
}
