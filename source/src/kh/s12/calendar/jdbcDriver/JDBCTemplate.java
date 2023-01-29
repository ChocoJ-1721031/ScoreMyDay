package kh.s12.calendar.jdbcDriver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	public JDBCTemplate() {}
	
	public static Connection getConnection() {
		Connection conn = null;
		Properties prop = new Properties();
		
		try {
			String currentPath = JDBCTemplate.class.getResource("./").getPath();
			prop.load(new BufferedReader(new FileReader(currentPath+"jdbc.properties")));
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("pw"));
			if(conn != null) {
				System.out.println("DB연결 성공!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB 에러!");
		}
		return conn;
	}
	
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {conn.close();}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) {stmt.close();}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs) {
		try {
			if(rs != null && !rs.isClosed()) {rs.close();}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {conn.commit();}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {conn.rollback();}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
