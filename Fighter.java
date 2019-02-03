
public class Fighter {
	boolean isElf;
	int x;
	int y;
	int hp;
	
	boolean hasMoved;
	public Fighter(boolean isElf, int y, int x) {
		this.isElf = isElf;
		this.x = x;
		this.y = y;
		hp = 200;
		hasMoved = false;
	}

	public void move(int nX, int nY) {
		x = nX;
		y = nY;
		hasMoved = true;
	}
	
	public void nextRound() {
		hasMoved = false;
	}

	public boolean damage() {
		//returns true if the fighter died
		hp -= 3;
		return hp <= 0;
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

	public boolean isElf() {
		return isElf;
	}

	public boolean isGoblin() {
		return !isElf;
	}

}
