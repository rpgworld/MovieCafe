package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.*;

public class BbsReplyFormCmd implements Command{
	public boolean login_check = false;
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");
		
		String inputNum = request.getParameter("num");
		
		if(userID != null) {
			login_check = true;
		}
		
		if (login_check) {
			BbsDAO bbsDAO = new BbsDAO();
			
			BbsDTO writing = bbsDAO.bbsReplyForm(inputNum);
			request.setAttribute("bbsReplyForm", writing);
		}
	}
}
