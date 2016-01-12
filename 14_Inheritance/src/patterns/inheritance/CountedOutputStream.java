package patterns.inheritance;

import java.io.*;

public class CountedOutputStream extends StringOutputStream {
	
	private int c;
	private int depth = 0;

	public CountedOutputStream(OutputStream s) {
		super(s);
	}

	public int writtenChars() {
		// TODO return number of written characters
		return c;
	}

	@Override
	public void write(char ch) throws IOException {
		// TODO Auto-generated method stub
		if(depth == 0) c++;
		depth++;
		super.write(ch);
		depth--;
	}

	@Override
	public void write(String s) throws IOException {
		// TODO Auto-generated method stub
		if(depth == 0) c += s.length();
		depth++;
		super.write(s);
		depth--;
	}

}
