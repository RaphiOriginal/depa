package inheritance;

import java.util.Vector;

public class Stack2<T> {
	public Stack2() { }
	private Vector<T> vector = new Vector<>();

	public Object push(T item) {
		vector.addElement(item);
		return item;
	}
}