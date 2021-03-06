package patterns.singleton.registry;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Registry1 implements Registry {
	private final Map<String, Object> entries = Collections
			.synchronizedMap(new HashMap<String, Object>());

	private Registry1() {
	}

	private static Registry1 instance = new Registry1();

	public static Registry1 getInstance() {
		return instance;
	}

	public void register(String name, Object value) {
		entries.put(name, value);
	}

	public Object lookup(String name) {
		return entries.get(name);
	}
}
