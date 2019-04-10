package command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.BbsDAO;
import bbs.BbsDTO;

public class BbsListCmd implements Command{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		BbsDAO bbsDAO = new BbsDAO();
		ArrayList<BbsDTO> list;
		
		int pageCnt = 0;
		String curPage = request.getParameter("curPage");
		
		if (curPage == null) curPage = "1";
		
		list = bbsDAO.bbsList(curPage);
		
		request.setAttribute("bbsList", list);
		
		pageCnt = bbsDAO.bbsPageCnt();
		request.setAttribute("pageCnt", pageCnt);
	}
}
