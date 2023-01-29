package kh.s12.calendar.member.model;

public class MemberVO {
	private int mid;
	private String memail;
	private String mpwd;
	private String reg_date;
	private String mname;
	private String mip;
	
	public MemberVO() {
		super();
	}

	public MemberVO(int mid, String memail, String mpwd, String reg_date, String mname, String mip) {
		super();
		this.mid = mid;
		this.memail = memail;
		this.mpwd = mpwd;
		this.reg_date = reg_date;
		this.mname = mname;
		this.mip = mip;
	}

	@Override
	public String toString() {
		return "MemberVO [mid=" + mid + ", memail=" + memail + ", mpwd=" + mpwd + ", reg_date=" + reg_date + ", mname="
				+ mname + ", mip=" + mip + "]";
	}
	
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getMemail() {
		return memail;
	}
	public void setMemail(String memail) {
		this.memail = memail;
	}
	public String getMpwd() {
		return mpwd;
	}
	public void setMpwd(String mpwd) {
		this.mpwd = mpwd;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMip() {
		return mip;
	}
	public void setMip(String mip) {
		this.mip = mip;
	}
	
}
