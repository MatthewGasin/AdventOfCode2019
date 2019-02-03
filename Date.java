import java.util.Comparator;

public class Date implements Comparable{
	int month;
	int day;
	int minute;
	String log;
	public Date(int month, int day, int minute, String log) {
		super();
		this.month = month;
		this.day = day;
		this.minute = minute;
		this.log = log;
	}
	public int getMonth() {
		return month;
	}
	
	public int getDay() {
		return day;
	}
	public int getMinute() {
		return minute;
	}
	
	public String getLog() {
		return log;
	}
	
	@Override
	public int compareTo(Object arg0) {
		Date other = (Date) arg0;
		if(getMonth() > other.getMonth()) {
			return 1;
		}else if(getMonth() < other.getMonth()) {
			return -1;
		}else {
			if(getDay() > other.getDay()) {
				return 1;
			}else if(getDay() < other.getDay()) {
				return -1;
			}else {
				if(getMinute() > other.getMinute()) {
					return 1;
				}else if(getMinute() < other.getMinute()) {
					return -1;
				}else {
					return 0;
				}
			}
		}
	}
	
	public int timeSince(Date other) {
		return getMinute() - other.getMinute();
	}
	
	public String toString() {
		return month + "-" + day + ":" + minute;
	}
}
