
public class Point {
	private int x;
	private int y;
	private int vX;
	private int vY;

	
	
	public Point(int x, int y, int vX, int vY) {
		super();
		this.x = x;
		this.y = y;
		this.vX = vX;
		this.vY = vY;
	}
	
	public double dist(Point other) {
		return Math.sqrt(Math.pow(getX()-getvX(), 2)+Math.pow(getY()-getvY(), 2));
	}
	
	public void move() {
		setX(getX() + getvX());
		setY(getY() + getvY());
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getvX() {
		return vX;
	}
	public int getvY() {
		return vY;
	}
	
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
}
