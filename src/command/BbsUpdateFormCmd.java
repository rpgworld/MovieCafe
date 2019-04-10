package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.*;

public class BbsUpdateFormCmd implements Command{
	public boolean user_check = false;
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");
		
		String inputNum = request.getParameter("num");
		
		BbsDAO bbsDAO = new BbsDAO();
		
		if(userID != null) {
			user_check = bbsDAO.bbsUserCheck(inputNum, userID);
		}
		
		if(user_check) {
			BbsDTO writing = bbsDAO.bbsUpdateForm(inputNum);
			
			request.setAttribute("bbsUpdate", writing);
		}
	}
}

