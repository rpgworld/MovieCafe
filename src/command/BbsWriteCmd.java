package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.*;

public class BbsWriteCmd implements Command{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("userID");
		
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		BbsDAO bbsDAO = new BbsDAO();
		bbsDAO.bbsWrite(name, subject, content);
	}
}
