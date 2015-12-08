package patterns.decorator.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class CollectionDecorator<T> implements Collection<T>{
	Collection<T> inner;

	public CollectionDecorator(Collection<T> inner) {
		super();
		this.inner = inner;
	}

	@Override
	public void forEach(Consumer<? super T> action) {
		inner.forEach(action);
	}

	public int size() {
		return inner.size();
	}

	public boolean isEmpty() {
		return inner.isEmpty();
	}

	public boolean contains(Object o) {
		return inner.contains(o);
	}

	public Iterator<T> iterator() {
		return inner.iterator();
	}

	public Object[] toArray() {
		return inner.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return inner.toArray(a);
	}

	@Override
	public boolean add(T e) {
		return inner.add(e);
	}

	public boolean remove(Object o) {
		return inner.remove(o);
	}

	public
	@Override boolean containsAll(Collection<?> c) {
		return inner.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		return inner.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return inner.removeAll(c);
	}

	@Override
	public boolean removeIf(Predicate<? super T> filter) {
		return inner.removeIf(filter);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return inner.retainAll(c);
	}

	public void clear() {
		inner.clear();
	}

	public boolean equals(Object o) {
		return inner.equals(o);
	}

	public int hashCode() {
		return inner.hashCode();
	}

	public Spliterator<T> spliterator() {
		return inner.spliterator();
	}

	public Stream<T> stream() {
		return inner.stream();
	}

	public Stream<T> parallelStream() {
		return inner.parallelStream();
	}

}
