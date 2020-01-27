package Controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ScheduleDao;
import DAO.StudentDao;
import Model.StudentBean;
import Util.MailUtil;

@WebServlet("/ReserveSpot")
public class ReserveSpot extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReserveSpot() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentBean student = (StudentBean)request.getSession(false).getAttribute("studentSession");
		
		ScheduleDao schedule = new ScheduleDao();
		schedule.addRider(student.getStudentID(),  Integer.parseInt(request.getParameter("scheduleNO")));
		try
		{
			MailUtil.sendMail(student);
		}
		catch (MessagingException e)
		{
			e.printStackTrace();
		}
		response.sendRedirect("CheckSchedule?scheduleNO="+request.getParameter("scheduleNO"));
	}
}
