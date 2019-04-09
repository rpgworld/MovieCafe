package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserDAO {
	DataSource ds;
	
	public UserDAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/MovieCafe");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 로그인
	public boolean login(String userID, String userPW) {
		boolean login_check = false;
		int password_check = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			String SQL = "SELECT COUNT(*) AS password_check FROM USER WHERE userID = ? and userPW = ?";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.setString(2, userPW);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				password_check = rs.getInt(1);
			}
			
			if(password_check > 0) {
				login_check = true;
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return login_check;
	}
	
	// 회원가입
	public void register(String userID, String userPW, String userName, String userGender, String userEmail) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ds.getConnection();
			String SQL = "INSERT INTO USER VALUES(NULL, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.setString(2, userPW);
			pstmt.setString(3, userName);
			pstmt.setString(4, userGender);
			pstmt.setString(5, userEmail);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
