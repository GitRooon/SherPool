package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ScheduleDao;
import Model.StudentBean;

@WebServlet("/CancelRide")
public class CancelRide extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CancelRide() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ScheduleDao schedule = new ScheduleDao();
		StudentBean student = (StudentBean)request.getSession(false).getAttribute("studentSession");
		
		schedule.cancelRide(Integer.parseInt(request.getParameter("scheduleNO")),student.getStudentID());

		response.sendRedirect("CheckSchedule?scheduleNO="+request.getParameter("scheduleNO"));
	}

}
