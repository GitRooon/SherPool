package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.StudentDao;
import Model.StudentBean;

@WebServlet("/StudentRegistrationServlet")
public class StudentRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StudentDao nStudent;

	public StudentRegistrationServlet() {
		super();
		nStudent = new StudentDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int studentID = Integer.parseInt(request.getParameter("studentID"));
		String password = request.getParameter("password");
		String fName = request.getParameter("fName");
		String lName = request.getParameter("lName");
		String email = request.getParameter("email");
		long phone = Long.parseLong(request.getParameter("phone"));
		
		StudentBean student = new StudentBean();
		student.setStudentID(studentID);
		student.setPassword(password);
		student.setFirstName(fName);
		student.setLastName(lName);
		student.setEmail(email);
		student.setPhone(phone);
		
		nStudent.addStudent(student);
		response.sendRedirect("index.jsp");
	}
}
