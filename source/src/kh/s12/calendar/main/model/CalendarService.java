package kh.s12.calendar.main.model;

import java.sql.Connection;
import java.util.ArrayList;

import kh.s12.calendar.jdbcDriver.JDBCTemplate;

import static kh.s12.calendar.jdbcDriver.JDBCTemplate.*;

public class CalendarService {
	public ArrayList<CalendarVO> callSchedule(int mid) {
		Connection conn = getConnection();
		ArrayList<CalendarVO> list = null;
		list = CalendarDAO.callSchedule(conn, mid);
		close(conn);
		
		return list;
	}
	
	
	public int insertSchedule(CalendarVO cvo) {
		int result = 0;
		Connection conn = getConnection();
		result = new CalendarDAO().insertSchedule(conn, cvo);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public int updateSchedule(CalendarVO cvo) {
		int result = 0;
		Connection conn = getConnection();
		result = new CalendarDAO().updateSchedule(conn, cvo);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		return result;
	}
	
	public int deleteSchedule(CalendarVO cvo) {
		int result = 0;
		Connection conn = getConnection();
		result = new CalendarDAO().deleteSchedule(conn, cvo);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		return result;
	}
	
	public ArrayList<CalendarVO> searchSchedule(int mid, String searchInput) {
		Connection conn = getConnection();
		ArrayList<CalendarVO> searchList = null;
		
		if(searchInput != null || !searchInput.equals("")) {
			searchList = CalendarDAO.searchSchedule(conn, mid, searchInput);
		}
		
		close(conn);
		
		return searchList;
	}
	
	
}
