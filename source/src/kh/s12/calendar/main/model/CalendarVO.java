package kh.s12.calendar.main.model;

public class CalendarVO {
	private int snum;
	private int mid;
	private String title;
	private String start;
	private String end;
	
	public CalendarVO() {
		super();
	}

	@Override
	public String toString() {
		return "CalendarVO [snum=" + snum + ", mid=" + mid + ", title=" + title + ", start=" + start + ", end=" + end
				+ "]";
	}

	public int getSnum() {
		return snum;
	}

	public void setSnum(int snum) {
		this.snum = snum;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}
	
}
