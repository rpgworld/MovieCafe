package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.*;

public class BbsReplyCmd implements Command{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");
		
		String num = request.getParameter("num");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String ref = request.getParameter("ref");
		String lev = request.getParameter("lev");
		String step = request.getParameter("step");
		
		
		if (userID != null) {
			BbsDAO bbsDAO = new BbsDAO();
			bbsDAO.bbsReply(num, userID, subject, content, ref, step, lev);
		}
	}
}
