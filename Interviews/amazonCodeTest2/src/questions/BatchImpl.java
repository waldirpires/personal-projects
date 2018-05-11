package questions;

public class BatchImpl implements Batch{

	private int tapeSize;
	private int[] fileSizes;

	
	
	public BatchImpl(int tapeSize, int[] fileSizes) {
		super();
		this.tapeSize = tapeSize;
		this.fileSizes = fileSizes;
	}

	@Override
	public int[] getFileSizes() {
		return fileSizes;
	}

	@Override
	public int getTapeSize() {
		return tapeSize;
	}

}
