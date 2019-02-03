import java.util.ArrayList;

public class Guard {
	private int id;
	private int totalSleep;
	//first date is falling asleep, second date is waking up
	private ArrayList<Date[]> sleepTimes;
	//0-59 array for naps
	int[] napTime;
	public Guard(int id) {
		super();
		this.id = id;
		totalSleep = 0;
		sleepTimes = new ArrayList<Date[]>();
		napTime = new int[60];
		for(int i = 0; i < napTime.length; i++) {
			napTime[i] = 0;
		}
	}
	
	public int[] getNapTime() {
		return napTime;
	}
	
	public int getTotalSleep() {
		return totalSleep;
	}
	public void addToTotal(int sleep) {
		this.totalSleep += sleep;
	}
	public ArrayList<Date[]> getSleepTimes() {
		return sleepTimes;
	}
	public void fallAsleep(Date time) {
		Date[] toAdd = new Date[2];
		toAdd[0] = time;
		sleepTimes.add(toAdd);
	}
	
	public void wakeUp(Date time) {
		Date[] toAdd = new Date[2];
		toAdd[0] = sleepTimes.get(sleepTimes.size()-1)[0];
		toAdd[1] = time;
		sleepTimes.remove(sleepTimes.size()-1);
		sleepTimes.add(toAdd);
		addToTotal(toAdd[1].timeSince(toAdd[0]));
		//System.out.println("Guard " + getId() + " slept from " + toAdd[0].toString() + " to " + toAdd[1].toString() + ", totalling " + toAdd[1].timeSince(toAdd[0]));
	}
	public int getId() {
		return id;
	}
	
	public void correctNapTime() {
		for(Date[] naps : sleepTimes) {
			for(int i = naps[0].minute; i < naps[1].minute; i++) {
				napTime[i]++;
			}
		}
	}
	
	
}
