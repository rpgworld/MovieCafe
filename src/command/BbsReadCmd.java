package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.*;

public class BbsReadCmd implements Command{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String inputNum = request.getParameter("num");
		BbsDAO bbsDAO = new BbsDAO();
		BbsDTO writing = bbsDAO.bbsRead(inputNum);
		
		request.setAttribute("bbsRead", writing);
	}
}
