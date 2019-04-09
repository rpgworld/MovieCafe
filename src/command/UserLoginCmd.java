package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.*;

public class UserLoginCmd implements Command{
	public boolean login_check;
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String userID = request.getParameter("userID");
		String userPW = request.getParameter("userPW");
		
		request.setAttribute("userID", userID);
		
		UserDAO userDAO = new UserDAO();
		login_check = userDAO.login(userID, userPW);
		
		if(login_check == true) {
			HttpSession session = request.getSession();
			session.setAttribute("userID", request.getAttribute("userID"));
		}
	}
}
