package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.*;

public class UserRegisterCmd implements Command {
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String userID = request.getParameter("userID");
		String userPW = request.getParameter("userPW");
		String userName = request.getParameter("userName");
		String userGender = request.getParameter("userGender");
		String userEmail = request.getParameter("userEmail");
		
		UserDAO userDAO = new UserDAO();
		userDAO.register(userID, userPW, userName, userGender, userEmail);
	}
}
