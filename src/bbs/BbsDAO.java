package bbs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BbsDAO {
	DataSource ds;
	public static final int WRITING_PER_PAGE = 10;
	
	public BbsDAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/MovieCafe");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 게시판 목록 조회
	public ArrayList<BbsDTO> bbsList(String curPage){
		ArrayList<BbsDTO> list = new ArrayList<BbsDTO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			String SQL = "SELECT num, name, subject, content, write_date, write_time, ref, step, lev, read_cnt, child_cnt";
			SQL += " FROM BBS ORDER BY ref desc, step asc";
			SQL += " LIMIT ?, ?";
			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, WRITING_PER_PAGE * (Integer.parseInt(curPage) - 1));
			pstmt.setInt(2, WRITING_PER_PAGE);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int num = rs.getInt("num");
				String name = rs.getString("name");
				String subject = rs.getString("subject");
				String content = rs.getString("content");
				Date writeDate = rs.getDate("write_date");
				Time writeTime = rs.getTime("write_time");
				int ref = rs.getInt("ref");
				int step = rs.getInt("step");
				int lev = rs.getInt("lev");
				int readCnt = rs.getInt("read_cnt");
				int childCnt = rs.getInt("child_cnt");
				
				BbsDTO writing = new BbsDTO();
				writing.setNum(num);
				writing.setName(name);
				writing.setSubject(subject);
				writing.setContent(content);
				writing.setWriteDate(writeDate);
				writing.setWriteTime(writeTime);
				writing.setRef(ref);
				writing.setStep(step);
				writing.setLev(lev);
				writing.setReadCnt(readCnt);
				writing.setChildCnt(childCnt);
				
				list.add(writing);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	// 게시판 페이징 처리
	public int bbsPageCnt() {
		int pageCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			String SQL = "SELECT COUNT(*) AS num FROM BBS";
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pageCnt = rs.getInt("num") / WRITING_PER_PAGE + 1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return pageCnt;
	}
	
	// 게시글 등록
	public void bbsWrite(String name, String subject, String content) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 1;
		
		try {
			conn = ds.getConnection();
			String SQL = "SELECT IFNULL(MAX(num), 0) + 1 AS num FROM BBS";
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num = rs.getInt("num");
			}
			
			SQL = "INSERT INTO BBS VALUES(?, ?, ?, ?, curdate(), curtime(), ?, 0, 0, 0, 0)";
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setInt(1, num);
			pstmt.setString(2, name);
			pstmt.setString(3, subject);
			pstmt.setString(4, content);
			pstmt.setInt(5, num);
		
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// 게시글 열람
	public BbsDTO bbsRead(String inputNum) {
		BbsDTO writing = new BbsDTO();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			String SQL = "UPDATE BBS SET read_cnt = read_cnt + 1 WHERE num=?";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, Integer.parseInt(inputNum));
			pstmt.executeUpdate();
			
			SQL = "SELECT num, name, subject, content, write_date, write_time, ref, step, lev, read_cnt, child_cnt FROM BBS WHERE num=?";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, Integer.parseInt(inputNum));
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int num = rs.getInt("num");
				String name = rs.getString("name");
				String subject = rs.getString("subject");
				String content = rs.getString("content");
				Date writeDate = rs.getDate("write_date");
				Time writeTime = rs.getTime("write_time");
				int ref = rs.getInt("ref");
				int step = rs.getInt("step");
				int lev = rs.getInt("lev");
				int readCnt = rs.getInt("read_cnt");
				int childCnt = rs.getInt("child_cnt");
				
				writing.setNum(num);
				writing.setName(name);
				writing.setSubject(subject);
				writing.setContent(content);
				writing.setWriteDate(writeDate);
				writing.setWriteTime(writeTime);
				writing.setRef(ref);
				writing.setStep(step);
				writing.setLev(lev);
				writing.setReadCnt(readCnt);
				writing.setChildCnt(childCnt);
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
		
		return writing;
	}
}
