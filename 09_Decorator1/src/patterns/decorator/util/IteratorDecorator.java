package patterns.decorator.util;

import java.util.Iterator;
import java.util.function.Consumer;

public class IteratorDecorator<T> implements Iterator<T>{
	Iterator<T> inner;

	public IteratorDecorator(Iterator<T> inner) {
		super();
		this.inner = inner;
	}

	public boolean hasNext() {
		return inner.hasNext();
	}

	public T next() {
		return inner.next();
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}

	public void forEachRemaining(Consumer action) {
		throw new UnsupportedOperationException();
	}

}
