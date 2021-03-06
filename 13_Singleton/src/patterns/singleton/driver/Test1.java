package patterns.singleton.driver;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Test1 {

	public static void main(String[] args) throws Exception {
		Singleton1 s1=null, s2=null;
		
		s1 = Singleton1.getInstance();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		System.out.println("writeObject");
		oos.writeObject(s1);
		oos.close();
		
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bais);
		System.out.println("readObject");
		s2 = (Singleton1)ois.readObject();
		ois.close();
		
		System.out.println("s1 = s2: " + (s1 == s2));
	}

}
