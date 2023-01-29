package kh.s12.calendar.member.model;

import java.sql.*;
import kh.s12.calendar.jdbcDriver.JDBCTemplate;

public class MemberDAO {
	public MemberDAO() {}
	
	public MemberVO login(Connection conn, String mail, String pw) {
		MemberVO vo = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT MID, MEMAIL, MNAME, MIP FROM MEMBER WHERE MEMAIL = ? AND MPWD= ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mail);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new MemberVO();
				vo.setMid(rs.getInt("MID"));
				vo.setMemail(rs.getString("MEMAIL"));
				//vo.setMpwd(rs.getString("MPWD"));
				//vo.setReg_date(rs.getString("REG_DATE"));
				vo.setMname(rs.getString("MNAME"));
				vo.setMip(rs.getString("MIP"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		return vo;
	}
	
	//회원가입
	public int insertMember(Connection conn, MemberVO mvo) {
		PreparedStatement pstmt = null;
		int result = 0;
		int mid = midCreate(conn);
		String sql = "INSERT INTO MEMBER VALUES(?, ?, ?, SYSDATE, ?, ?)";
		
		if(mid > 0) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, mid);
				pstmt.setString(2, mvo.getMemail());
				pstmt.setString(3, mvo.getMpwd());
				pstmt.setString(4, mvo.getMname());
				pstmt.setString(5, mvo.getMip());
				
				result = pstmt.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
		} else {
			System.out.println("mid에러!");
		}
		return result;
	}
	
	//mid 생성
	public int midCreate(Connection conn) {
		int mid = -1;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT MAX(MID) AS MID FROM MEMBER";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				mid = rs.getInt(1) + 1;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		return mid;
	}
	
	//memail 중복체크
	public int dupChk(Connection conn, String memail) { // TODO 중복체크 후 이메일 입력란 밑에 코드 입력란과 코드 전송버튼 생성
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM MEMBER WHERE MEMAIL=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memail);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	public int updateMember(Connection conn, MemberVO mvo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "UPDATE MEMBER SET MNAME=?, MPWD=? WHERE MID=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mvo.getMname());
			pstmt.setString(1, mvo.getMpwd());
			pstmt.setInt(1, mvo.getMid());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	
	public int deleteMember(Connection conn, String memail) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "DELETE FROM MEMBER WHERE MEMAIL=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memail);
			
			result = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
}
