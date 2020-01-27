package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CarDao;
import Model.CarBean;
import Model.StudentBean;

@WebServlet("/CarRegistrationServlet")
public class CarRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CarDao nCar;
       
    public CarRegistrationServlet() {
        super();
        nCar = new CarDao();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentBean student = (StudentBean)request.getSession(false).getAttribute("studentSession");
		
		String plate = request.getParameter("plateNumber");
		int seat = Integer.parseInt(request.getParameter("seats"));
		String make = request.getParameter("make");
		String model = request.getParameter("model");
		
		CarBean car = new CarBean();
		car.setPlateNumber(plate);
		car.setSeats(seat);
		car.setMake(make);;
		car.setModel(model);
		nCar.addCar(car, student);
		
		request.getSession(false).invalidate();
		student.setDriver(true);
		request.getSession().setAttribute("studentSession", student);
		
		response.sendRedirect("DashBoardServlet");
	}
}