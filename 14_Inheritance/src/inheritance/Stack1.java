package inheritance;

import java.util.Vector;

public class Stack1<T> extends Vector<T> {
	public Stack1() { }

	public Object push(T item) {
		addElement(item);
		return item;
	}
}