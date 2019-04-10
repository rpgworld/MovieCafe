package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.*;

public class BbsUpdateCmd implements Command{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");
		
		String inputNum = request.getParameter("num");
		String inputSubject = request.getParameter("subject");
		String inputContent = request.getParameter("content");
		
		if (userID != null) {
			BbsDAO bbsDAO = new BbsDAO();
			bbsDAO.bbsUpdate(inputNum, inputSubject, inputContent);
		}
	}
}

