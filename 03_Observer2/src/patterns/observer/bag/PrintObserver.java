package patterns.observer.bag;

public class PrintObserver implements java.util.Observer {

	@Override
	public void update(java.util.Observable o, Object arg) {
		IntegerBag bag = (IntegerBag) o;
		System.out.print("Content has changed: [ ");
		for (int x : bag.getValues()) {
			System.out.print(x + " ");
		}
		System.out.println("]");
	}
}
