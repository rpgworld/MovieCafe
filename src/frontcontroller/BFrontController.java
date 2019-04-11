package frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.BbsListCmd;
import command.BbsReadCmd;
import command.BbsUpdateCmd;
import command.BbsUpdateFormCmd;
import command.BbsDeleteCmd;
import command.BbsDeleteCheckCmd;
import command.BbsSearchCmd;
import command.BbsWriteCmd;
import command.BbsWriteFormCmd;
import command.Command;


@WebServlet("*.bbs")
public class BFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}
	
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Command command = null;
		String viewPage = null;
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		
		String com = uri.substring(conPath.length());
		
		if(com.equals("/bbsList.bbs")) {
			command = new BbsListCmd();
			command.execute(request, response);
			viewPage = "bbsList.jsp";
		} else if (com.equals("/bbsWriteForm.bbs")) {
			command = new BbsWriteFormCmd();
			command.execute(request, response);
			
			BbsWriteFormCmd checkCmd = (BbsWriteFormCmd) command;
			if(checkCmd.login_check) {
				viewPage = "bbsWrite.jsp";
			} else {
				viewPage = "login.jsp";
			}
		} else if (com.equals("/bbsWrite.bbs")) {
			command = new BbsWriteCmd();
			command.execute(request, response);
			viewPage = "bbsList.bbs";
		} else if (com.equals("/bbsRead.bbs")) {
			command = new BbsReadCmd();
			command.execute(request, response);
			viewPage = "bbsRead.jsp";
		} else if (com.equals("/bbsUpdateForm.bbs")) {
			command = new BbsUpdateFormCmd();
			command.execute(request, response);
			
			BbsUpdateFormCmd checkCmd = (BbsUpdateFormCmd) command;
			if(checkCmd.user_check) {
				viewPage = "bbsUpdate.jsp";
			} else {	// 권한이 없을때 경고와 함께 바로 전 페이지로 넘어가기를 구현해야 합니다.
				viewPage = "bbsList.bbs";
			}
		} else if (com.equals("/bbsUpdate.bbs")) {
			command = new BbsUpdateCmd();
			command.execute(request, response);
			viewPage = "bbsList.bbs";
		} else if (com.equals("/bbsDeleteCheck.bbs")) {
			command = new BbsDeleteCheckCmd();
			command.execute(request, response);
			
			BbsDeleteCheckCmd checkCmd = (BbsDeleteCheckCmd) command;
			if (checkCmd.reply_check) {
				viewPage = "bbsDelete.bbs";
			} else {
				viewPage = "bbsList.bbs";
			}
		} else if (com.equals("/bbsDelete.bbs")) {
			command = new BbsDeleteCmd();
			command.execute(request, response);
			viewPage = "bbsList.bbs";
		} else if (com.equals("/bbsSearch.bbs")) {
			command = new BbsSearchCmd();
			command.execute(request, response);
			viewPage = "bbsSearchList.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
		
	}

}