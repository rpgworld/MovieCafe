package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BbsWriteFormCmd implements Command{
	public boolean login_check = false;
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");
		
		if(userID != null) {
			login_check = true;
		}
		
	}
}
