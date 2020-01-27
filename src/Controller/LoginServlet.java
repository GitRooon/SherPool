package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.StudentDao;
import Model.StudentBean;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		StudentDao studentDao = new StudentDao();
		HttpSession studentSession = request.getSession();
		
		try {
			int userID = Integer.parseInt(request.getParameter("studentID"));
			String password = request.getParameter("password");
			StudentBean student = studentDao.getUserByID(userID);

			if (student.getPassword().equals(password)) {
				studentSession.setAttribute("studentSession", student);
				response.sendRedirect("DashBoardServlet");
			} else {
				response.sendRedirect("index.jsp");
			}
		} catch (Exception e) {
			response.sendRedirect("index.jsp");

		}
	}
}
