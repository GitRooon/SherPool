package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.StudentBean;
import DAO.CarDao;
import DAO.ScheduleDao;

@WebServlet("/AddPickUp")
public class AddPickUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ScheduleDao schedule;
       
    public AddPickUp() {
        super();
        schedule = new ScheduleDao();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentBean student = (StudentBean)request.getSession(false).getAttribute("studentSession");
		int minutes = Integer.parseInt(request.getParameter("minutes"));
		String campusDestination = request.getParameter("campusDestination");
		String campusSource = request.getParameter("campusSource");
		
		CarDao car = new CarDao();
		schedule.addPickUp(minutes,campusSource,campusDestination,car.getCarByOwner(student.getStudentID()).getPlateNumber());
		
		response.sendRedirect("DashBoardServlet");
	}

}
