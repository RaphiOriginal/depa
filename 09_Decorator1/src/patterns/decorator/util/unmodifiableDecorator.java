package patterns.decorator.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;

public class unmodifiableDecorator<T> extends CollectionDecorator<T>{

	public unmodifiableDecorator(Collection<T> inner) {
		super(inner);
	}
	
	@Override
	public Iterator<T> iterator() {
		return new IteratorDecorator(inner.iterator());
	}
	
	@Override
	public boolean add(T e) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean addAll(Collection<? extends T> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeIf(Predicate<? super T> filter) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}

}
