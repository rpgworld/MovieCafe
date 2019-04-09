package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.*;

public class UserLoginCmd implements Command{
	public boolean login_check;
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String userID = request.getParameter("userID");
		String userPW = request.getParameter("userPW");
		
		request.setAttribute("userID", userID);
		
		UserDAO userDAO = new UserDAO();
		login_check = userDAO.login(userID, userPW);
	}
}
