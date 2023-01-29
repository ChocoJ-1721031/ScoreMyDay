package kh.s12.calendar.servey.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import kh.s12.calendar.jdbcDriver.JDBCTemplate;

public class ServeyDAO {

	public static int checkServey(Connection conn, ServeyVO svo) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT DSERVEY FROM SERVEY_DATE WHERE DDATE=? AND MID=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, svo.getdDate());
			pstmt.setInt(2, svo.getMid());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1) == "Y" || rs.getString(1).equals("Y")) {
					result = 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	public static int insertServey(Connection conn, ServeyVO svo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO SERVEY_DATE VALUES(?, ?, 'Y')";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, svo.getdDate());
			pstmt.setInt(2, svo.getMid());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	public static int insertServeyContent(Connection conn, ServeyContentVO scvo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO SERVEY_CONTENT VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, scvo.getdDate());
			pstmt.setInt(2, scvo.getMid());
			pstmt.setInt(3, scvo.getSer_1());
			pstmt.setInt(4, scvo.getSer_2());
			pstmt.setInt(5, scvo.getSer_3());
			pstmt.setInt(6, scvo.getSer_4());
			pstmt.setInt(7, scvo.getSer_5());
			pstmt.setInt(8, scvo.getScore_1());
			pstmt.setString(9, scvo.getSer_diary());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	
	public static ArrayList<ServeyContentVO> callAllServey(Connection conn, int mid) {
		ServeyContentVO scvo = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ServeyContentVO> list =  new ArrayList<ServeyContentVO>();
		String sql = "SELECT TO_CHAR(TO_DATE(DDATE, 'YY-MM-DD'), 'YYYY-MM-DD') AS DDATE, MID, SER_1, SER_2, SER_3, SER_4, SER_5, SER_SCORE_1, SER_CONTENT FROM SERVEY_CONTENT WHERE MID=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mid);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				scvo = new ServeyContentVO();
				scvo.setdDate(rs.getString(1));
				scvo.setMid(rs.getInt(2));
				scvo.setSer_1(rs.getInt(3));
				scvo.setSer_2(rs.getInt(4));
				scvo.setSer_3(rs.getInt(5));
				scvo.setSer_4(rs.getInt(6));
				scvo.setSer_5(rs.getInt(7));
				scvo.setScore_1(rs.getInt(8));
				scvo.setSer_diary(rs.getString(9));
				
				list.add(scvo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}
	
	public static ArrayList<ServeyContentVO> callServey(Connection conn, int mid, String date) {
		ServeyContentVO scvo = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ServeyContentVO> list = new ArrayList<ServeyContentVO>();
		String sql = "SELECT TO_CHAR(TO_DATE(DDATE, 'YY-MM-DD'), 'YYYY-MM-DD') AS DDATE, MID, SER_1, SER_2, SER_3, SER_4, SER_5, SER_SCORE_1, SER_CONTENT FROM SERVEY_CONTENT WHERE DDATE=? AND MID=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date);
			pstmt.setInt(2, mid);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				scvo = new ServeyContentVO();
				scvo.setdDate(rs.getString(1));
				scvo.setMid(rs.getInt(2));
				scvo.setSer_1(rs.getInt(3));
				scvo.setSer_2(rs.getInt(4));
				scvo.setSer_3(rs.getInt(5));
				scvo.setSer_4(rs.getInt(6));
				scvo.setSer_5(rs.getInt(7));
				scvo.setScore_1(rs.getInt(8));
				scvo.setSer_diary(rs.getString(9));
				
				list.add(scvo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}
	
	

}
