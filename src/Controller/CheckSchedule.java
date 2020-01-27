package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ScheduleDao;

@WebServlet("/CheckSchedule")
public class CheckSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public CheckSchedule() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ScheduleDao schedule = new ScheduleDao();

		request.setAttribute("checkSchedule", schedule.checkSchedule(Integer.parseInt(request.getParameter("scheduleNO"))));
		request.setAttribute("riders", schedule.getRiders(Integer.parseInt(request.getParameter("scheduleNO"))));

		request.getRequestDispatcher("checkSchedule.jsp").forward(request, response);
	}

}
