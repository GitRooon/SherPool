package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ScheduleDao;
import Model.StudentBean;

@WebServlet("/DashBoardServlet")
public class DashBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DashBoardServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ScheduleDao schedules = new ScheduleDao();
		StudentBean student = (StudentBean)request.getSession(false).getAttribute("studentSession");

		request.setAttribute("schedules", schedules.getAllSchedule());
		request.setAttribute("currentSchedule", schedules.getCurrentSchedule(student.getStudentID()));
		request.setAttribute("rider", schedules.getRiderSchedule(student.getStudentID()));
		
		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	}

}
