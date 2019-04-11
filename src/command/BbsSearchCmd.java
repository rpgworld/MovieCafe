package command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.BbsDAO;
import bbs.BbsDTO;

public class BbsSearchCmd implements Command{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		BbsDAO bbsDAO = new BbsDAO();
		
		String searchOption = request.getParameter("searchOption");
		String searchWord = request.getParameter("searchWord");
		
		ArrayList<BbsDTO> list = bbsDAO.bbsSearch(searchOption, searchWord);
		request.setAttribute("bbsList", list);
	}
}
