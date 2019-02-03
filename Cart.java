
public class Cart {
	int turn;
	int x;
	int y;
	
	boolean hasMoved;
	
	public Cart(int x, int y) {
		this.turn = -1;
		this.x = x;
		this.y = y;
		hasMoved = false;
		
	}
	
	public void move(int nX, int nY) {
		x = nX;
		y = nY;
		hasMoved = true;
	}

	public int turn() {
		
		if(turn <= 0 || turn == 1) {
			turn++;
		}else {
			turn = 0;
		}
		return turn;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean HasMoved() {
		return hasMoved;
	}
	
	public void nextTick() {
		hasMoved = false;
	}
	
	
}
