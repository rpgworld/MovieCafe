package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.*;

public class UserUpdateFormCmd implements Command{
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");
		
		UserDAO userDAO = new UserDAO();
		UserDTO writing = userDAO.userUpdateForm(userID);
		
		request.setAttribute("userUpdate", writing);
	}
}

