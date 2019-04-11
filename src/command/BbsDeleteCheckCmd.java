package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.*;

public class BbsDeleteCheckCmd implements Command{
	
	public boolean reply_check = false;
	public boolean user_check = false;
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");
		
		String inputNum = request.getParameter("num");
		
		request.setAttribute("num", inputNum);
		
		BbsDAO bbsDAO = new BbsDAO();
		
		if(userID != null) {
			user_check = bbsDAO.bbsUserCheck(inputNum, userID);
		}
		
		if(user_check) {
			reply_check = bbsDAO.bbsReplyCheck(inputNum);
		}
	}
}
