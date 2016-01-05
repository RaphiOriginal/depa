package patterns.singleton.perf;

public final class Singleton1 {
	
	private Singleton1() {	}

	private static Singleton1 instance = new Singleton1();

	public static Singleton1 getInstance() {
		return instance;
	}
}
