
public class Marble {
	private int num;
	private Marble clockwise;
	private Marble counterclockwise;
	
	public Marble(int num, Marble clockwise, Marble counterclockwise) {
		this.num = num;
		this.clockwise = clockwise;
		this.counterclockwise = counterclockwise;
	}
	
	public int getNum() {
		return num;
	}
	
	public Marble getClockwise() {
		return clockwise;
	}

	public void setClockwise(Marble clockwise) {
		this.clockwise = clockwise;
	}

	public Marble getCounterclockwise() {
		return counterclockwise;
	}

	public void setCounterclockwise(Marble counterclockwise) {
		this.counterclockwise = counterclockwise;
	}
	
	public String toString() {
		return ""+num;
	}
	
}
