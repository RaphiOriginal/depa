package patterns.state.parser;

public abstract class AbstractState implements IState {

	@Override
	public abstract FloatContextInterface parse(FloatContextInterface context);
	
	protected static boolean isDigit(char ch) {
		return Character.isDigit(ch);
	}
	protected static int getNumericValue(char ch) {
		return Character.getNumericValue(ch);
	}
}
