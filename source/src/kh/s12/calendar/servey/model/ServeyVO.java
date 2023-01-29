package kh.s12.calendar.servey.model;

public class ServeyVO {
	private String dDate;
	private int mid;
	String dServey;
	
	public ServeyVO() {
		super();
	}
	
	@Override
	public String toString() {
		return "ServeyVO [dDate=" + dDate + ", mid=" + mid + ", dServey=" + dServey + "]";
	}
	
	public ServeyVO(String dDate, int mid, String dServey) {
		super();
		this.dDate = dDate;
		this.mid = mid;
		this.dServey = dServey;
	}

	public String getdDate() {
		return dDate;
	}
	public void setdDate(String dDate) {
		this.dDate = dDate;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getdServey() {
		return dServey;
	}
	public void setdServey(String dServey) {
		this.dServey = dServey;
	}
}
