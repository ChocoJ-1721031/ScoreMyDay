package kh.s12.calendar.main.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import kh.s12.calendar.jdbcDriver.JDBCTemplate;

public class CalendarDAO {
	public static ArrayList<CalendarVO> callSchedule(Connection conn, int mid) {
		CalendarVO vo = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CalendarVO> list = new ArrayList<CalendarVO>();
		
		String sql = "SELECT SNUM, MID, SCONTENT, TO_CHAR(TO_DATE(SDATE_START, 'YY-MM-DD'), 'YYYY-MM-DD') AS SDATE_START , TO_CHAR(TO_DATE(SDATE_END, 'YY-MM-DD'), 'YYYY-MM-DD') AS SDATE_END FROM SCHEDULE WHERE MID=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mid);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new CalendarVO();
				vo.setSnum(rs.getInt(1));
				vo.setMid(rs.getInt(2));
				vo.setTitle(rs.getString(3));
				vo.setStart(rs.getString(4));
				vo.setEnd(rs.getString(5));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}
	
	public int insertSchedule(Connection conn, CalendarVO cvo) {
		PreparedStatement pstmt = null;
		int result = 0;
		if(cvo.getEnd() == null || cvo.getEnd().equals("")) {
			String sql = "INSERT INTO SCHEDULE(SNUM, MID, SCONTENT, SDATE_START) VALUES((SELECT MAX(SNUM)+1 AS SNUM FROM SCHEDULE), ?, ?, ?)";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cvo.getMid());
				pstmt.setString(2, cvo.getTitle());
				pstmt.setString(3, cvo.getStart());
				
				result = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(pstmt);
			}
		}else {
			String sql = "INSERT INTO SCHEDULE VALUES((SELECT MAX(SNUM)+1 AS SNUM FROM SCHEDULE), ?, ?, ?, ?)";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cvo.getMid());
				pstmt.setString(2, cvo.getTitle());
				pstmt.setString(3, cvo.getStart());
				pstmt.setString(4, cvo.getEnd());
				
				result = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(pstmt);
			}
		}
		
		return result;
	}
	
	public int updateSchedule(Connection conn, CalendarVO cvo) {
		PreparedStatement pstmt = null;
		int result = 0;
		if(cvo.getEnd() == null || cvo.getEnd().equals("")) {
			String sql = "UPDATE SCHEDULE SET SCONTENT=?, SDATE_START=?, SDATE_END=NULL WHERE SNUM=? AND MID=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, cvo.getTitle());
				pstmt.setString(2, cvo.getStart());
				pstmt.setInt(3, cvo.getSnum());
				pstmt.setInt(4, cvo.getMid());
				
				
				result = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(pstmt);
			}
		}else {
			String sql = "UPDATE SCHEDULE SET SCONTENT=?, SDATE_START=?, SDATE_END=? WHERE SNUM=? AND MID=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, cvo.getTitle());
				pstmt.setString(2, cvo.getStart());
				pstmt.setString(3, cvo.getEnd());
				pstmt.setInt(4, cvo.getSnum());
				pstmt.setInt(5, cvo.getMid());
				
				
				result = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(pstmt);
			}
		}
		
		return result;
	}
	
	public int deleteSchedule(Connection conn, CalendarVO cvo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = "DELETE FROM SCHEDULE WHERE SNUM=? AND MID=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cvo.getSnum());
			pstmt.setInt(2, cvo.getMid());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	public static ArrayList<CalendarVO> searchSchedule(Connection conn, int mid, String searchInput) {
		CalendarVO vo = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CalendarVO> searchList = new ArrayList<CalendarVO>();
		
		String sql = "SELECT SNUM, MID, SCONTENT, TO_CHAR(TO_DATE(SDATE_START, 'YY-MM-DD'), 'YYYY-MM-DD') AS SDATE_START , TO_CHAR(TO_DATE(SDATE_END, 'YY-MM-DD'), 'YYYY-MM-DD') AS SDATE_END FROM SCHEDULE";
				sql += " WHERE MID=? AND (SCONTENT LIKE '%"+searchInput+"%') ORDER BY SDATE_START DESC";
				
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mid);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new CalendarVO();
				vo.setSnum(rs.getInt(1));
				vo.setMid(rs.getInt(2));
				vo.setTitle(rs.getString(3));
				vo.setStart(rs.getString(4));
				vo.setEnd(rs.getString(5));
				searchList.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return searchList;
	}
}
