package patterns.inheritance;

import java.io.*;

public class CountedOutputStream extends StringOutputStream {

	public CountedOutputStream(OutputStream s) {
		super(s);
	}

	public int writtenChars() {
		// TODO return number of written characters
		return 0;
	}

	@Override
	public void write(char ch) throws IOException {
		// TODO Auto-generated method stub
		super.write(ch);
	}

	@Override
	public void write(String s) throws IOException {
		// TODO Auto-generated method stub
		super.write(s);
	}

}
