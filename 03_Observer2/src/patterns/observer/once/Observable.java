package patterns.observer.once;

import java.util.LinkedList;
import java.util.List;

public class Observable {
	private List<Observer> observers = new LinkedList<Observer>();

	public void addObserver(Observer o) {
		observers.add(o);
	}

	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	public void notifyObservers() {
		Observer[] copy;
		synchronized(this) { copy = observers.toArray(new Observer[observers.size()]); }
		for (Observer obs : copy) {
			obs.update(this);
		}
	}
}
