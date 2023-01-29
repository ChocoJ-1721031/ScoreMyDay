package kh.s12.calendar.servey.model;

public class ServeyContentVO {
	private String dDate;
	private int mid;
	private int ser_1;
	private int ser_2;
	private int ser_3;
	private int ser_4;
	private int ser_5;
	private int score_1;
	private String ser_diary;
	
	public ServeyContentVO() {
		super();
	}
	
	public ServeyContentVO(String dDate, int mid, int ser_1, int ser_2, int ser_3, int ser_4, int ser_5, int score_1,
			String ser_diary) {
		super();
		this.dDate = dDate;
		this.mid = mid;
		this.ser_1 = ser_1;
		this.ser_2 = ser_2;
		this.ser_3 = ser_3;
		this.ser_4 = ser_4;
		this.ser_5 = ser_5;
		this.score_1 = score_1;
		this.ser_diary = ser_diary;
	}
	
	@Override
	public String toString() {
		return "ServeyContentVO [dDate=" + dDate + ", mid=" + mid + ", ser_1=" + ser_1 + ", ser_2=" + ser_2 + ", ser_3="
				+ ser_3 + ", ser_4=" + ser_4 + ", ser_5=" + ser_5 + ", score_1=" + score_1 + ", ser_diary=" + ser_diary
				+ "]";
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
	public int getSer_1() {
		return ser_1;
	}
	public void setSer_1(int ser_1) {
		this.ser_1 = ser_1;
	}
	public int getSer_2() {
		return ser_2;
	}
	public void setSer_2(int ser_2) {
		this.ser_2 = ser_2;
	}
	public int getSer_3() {
		return ser_3;
	}
	public void setSer_3(int ser_3) {
		this.ser_3 = ser_3;
	}
	public int getSer_4() {
		return ser_4;
	}
	public void setSer_4(int ser_4) {
		this.ser_4 = ser_4;
	}
	public int getSer_5() {
		return ser_5;
	}
	public void setSer_5(int ser_5) {
		this.ser_5 = ser_5;
	}
	public int getScore_1() {
		return score_1;
	}
	public void setScore_1(int score_1) {
		this.score_1 = score_1;
	}
	public String getSer_diary() {
		return ser_diary;
	}
	public void setSer_diary(String ser_diary) {
		this.ser_diary = ser_diary;
	}
	
	
	
	
}
