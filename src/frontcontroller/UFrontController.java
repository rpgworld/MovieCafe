package frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.Command;
import command.UserLoginCmd;
import command.UserRegisterCmd;

@WebServlet("*.user")
public class UFrontController extends HttpServlet {
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
		
		if(com.equals("/UserRegister.user")) {
			command = new UserRegisterCmd();
			command.execute(request, response);
			viewPage="index.jsp";
		} else if (com.equals("/UserLogin.user")) {
			command = new UserLoginCmd();
			command.execute(request, response);
			
			UserLoginCmd userCheckCmd = (UserLoginCmd) command;
			if(userCheckCmd.login_check) {
				HttpSession session = request.getSession();
				session.setAttribute("userID", request.getAttribute("userID"));
				viewPage = "index.jsp";
			} else {
				viewPage = "login.jsp";
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
		
	}

}