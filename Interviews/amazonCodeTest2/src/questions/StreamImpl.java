package questions;

public class StreamImpl implements Stream {

	char [] values;
	int index = 0;
	
	public StreamImpl(String s) {
		values = s.toCharArray();
	}
	
	
	@Override
	public char getNext() {
		char next = values[index];
		index++;
		return next;
	}

	@Override
	public boolean hasNext() {
		return index < (values.length);
	}

}
