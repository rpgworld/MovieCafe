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
	
	// 게시판 수정
	public void bbsUpdate(String inputNum, String inputSubject, String inputContent) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ds.getConnection();
			String SQL = "UPDATE BBS SET subject=?, content=? WHERE num=?";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, inputSubject);
			pstmt.setString(2, inputContent);
			pstmt.setInt(3, Integer.parseInt(inputNum));
			
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
	
	// 게시판 수정 및 삭제를 위한, 해당 유저인지 확인 기능 조회
	public boolean bbsUserCheck(String inputNum, String userID) {
		boolean userCheckOk = false;
		int userCheck = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			String SQL = "SELECT COUNT(*) AS user_check FROM BBS WHERE num=? and name=?";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, Integer.parseInt(inputNum));
			pstmt.setString(2, userID);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				userCheck = rs.getInt("user_check");
			}
			
			if(userCheck > 0) {
				userCheckOk = true;
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
		
		return userCheckOk;
	}
	
	// 글 수정을 위한 원글 데이터 조회
	public BbsDTO bbsUpdateForm(String inputNum) {
		BbsDTO writing = new BbsDTO();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			
			String SQL = "SELECT num, name, subject, content, write_date, write_time, ref, step, lev, read_cnt, child_cnt FROM BBS WHERE num=?";
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
	
	// 게시글 삭제
	public void bbsDelete(String inputNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			String SQL = "SELECT ref, lev, step FROM BBS WHERE num=?";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, Integer.parseInt(inputNum));
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int ref = rs.getInt(1);
				int lev = rs.getInt(2);
				int step = rs.getInt(3);
				bbsDeleteChildCntUpdate(ref, lev, step); // 답글이라면 child_cnt 수정
			}
			
			SQL = "DELETE FROM BBS WHERE num=?";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, Integer.parseInt(inputNum));
			
			pstmt.executeUpdate();
			
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
	}
	
	// 삭제 대상인 게시글에 답글이 존재하는지
	public boolean bbsReplyCheck(String inputNum) {
		boolean replyCheck = false;
		int replyCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			String SQL = "SELECT child_cnt AS reply_check FROM BBS WHERE num=?";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, Integer.parseInt(inputNum));
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				replyCnt = rs.getInt("reply_check");
			}
			
			if(replyCnt == 0) {
				replyCheck = true; // replyCheck 0 인경우 삭제가 가능
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
		
		return replyCheck;
	}
	
	// 게시글이 답글일 경우 원글들의 답글 개수 줄이기
	public void bbsDeleteChildCntUpdate(int ref, int lev, int step) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = null;
		
		try {
			conn = ds.getConnection();
			for(int updateLev = lev - 1; updateLev >= 0; updateLev--) {
				SQL = "SELECT MAX(step) FROM BBS WHERE ref=? and lev=? and step<?";
				pstmt = conn.prepareStatement(SQL);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, updateLev);
				pstmt.setInt(3, step);
				
				rs = pstmt.executeQuery();
				int maxStep = 0;
				
				if(rs.next()) {
					maxStep = rs.getInt(1);
				}
				SQL  = "UPDATE BBS SET child_cnt = child_cnt - 1 WHERE ref=? and lev=? and step=?";
				pstmt = conn.prepareStatement(SQL);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, updateLev);
				pstmt.setInt(3, maxStep);
				pstmt.executeUpdate();
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
	}
	
	// 검색 기능
	public ArrayList<BbsDTO> bbsSearch(String searchOption, String searchWord){
		ArrayList<BbsDTO> list = new ArrayList<BbsDTO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			String SQL = "SELECT num, name, subject, content, write_date, write_time, ref, step, lev, read_cnt, child_cnt FROM BBS";
			
			if(searchOption.equals("subject")) {
				SQL += " WHERE subject LIKE ?";
				SQL += " ORDER BY ref desc, step asc";
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, "%" + searchWord + "%");
			} else if(searchOption.equals("content")) {
				SQL += " WHERE content LIKE ?";
				SQL += " ORDER BY ref desc, step asc";
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, "%" + searchWord + "%");
			} else if(searchOption.equals("name")) {
				SQL += " WHERE name LIKE ?";
				SQL += " ORDER BY ref desc, step asc";
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, "%" + searchWord + "%");
			} else if(searchOption.equals("both")) {
				SQL += " WHERE subject LIKE ? OR content LIKE ?";
				SQL += " ORDER BY ref desc, step asc";
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, "%" + searchWord + "%");
				pstmt.setString(2, "%" + searchWord + "%");
			}
			
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
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
}













