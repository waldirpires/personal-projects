package com.amazon.test;

public class CharacterStream implements Stream {

	private String stream;
	private int pos = -1;
	
	public CharacterStream(String s)
	{
		this.stream = s;
	}
	
	@Override
	public char getNext() {
		// TODO Auto-generated method stub
		pos++;
		return stream.charAt(pos);
	}

	@Override
	public boolean hasNext() {
		return stream.length() > pos+1;
	}

}
