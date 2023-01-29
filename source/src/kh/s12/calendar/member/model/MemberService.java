package kh.s12.calendar.member.model;

import java.sql.Connection;

import kh.s12.calendar.jdbcDriver.JDBCTemplate;

import static kh.s12.calendar.jdbcDriver.JDBCTemplate.*;

public class MemberService {
	public MemberService() {}
	//로그인
	public MemberVO login(String mail, String passwd) {
		Connection conn = getConnection();
		MemberVO vo = new MemberDAO().login(conn, mail, passwd);
		close(conn);
		return vo;
	}
	
	public int insertMember(MemberVO vo) {
		int result = 0;
		Connection conn = getConnection();
		result = new MemberDAO().insertMember(conn, vo);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public int dupChk(String mail) {
		int result = 0;
		Connection conn = getConnection();
		result = new MemberDAO().dupChk(conn, mail);
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public int updateMember(MemberVO mvo, String inputPwd, String inputMname) {
		Connection conn = getConnection();
		String origPwd = mvo.getMpwd();
		int result = 0;
		
		if(inputPwd.equals(origPwd)) {
			mvo.setMpwd(inputPwd);
			mvo.setMname(inputMname);
			
			result = new MemberDAO().updateMember(conn, mvo);
		} else {
			//대충 비번 틀리다는 얘기
		}
		
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		return result;
	}
	
	public int deleteMember(MemberVO mvo, String inputPwd) {
		Connection conn = getConnection();
		int result = 0;
		if(inputPwd.equals(mvo.getMpwd())) {
			String memail = mvo.getMemail();
			result = new MemberDAO().deleteMember(conn, memail);
			
			if(result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		return result;
	}
	
	
}
