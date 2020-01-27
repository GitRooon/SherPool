package DAO;

import java.io.Serializable;
import Util.CarPoolDBUtil;
import Model.CarBean;
import Model.StudentBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarDao implements Serializable{
	private static final long serialVersionUID = 1L;

	Connection conn = null;
	
//	add a driver to the database
	public void addCar(CarBean car, StudentBean owner) {
		try {
			conn = CarPoolDBUtil.getConnection();
			String addSql = "INSERT INTO Cars (PlateNumber,Make,Model,Seats,Owner)"
					+ "VALUES (?,?,?,?,?)";

			PreparedStatement pStmt = conn.prepareStatement(addSql);
			pStmt.setString(1, car.getPlateNumber());
			pStmt.setString(2, car.getMake());
			pStmt.setString(3, car.getModel());
			pStmt.setInt(4, car.getSeats());
			pStmt.setInt(5, owner.getStudentID());
			pStmt.executeUpdate();
			
			addSql = "UPDATE Users SET Driver = 1 Where StudentID = ?";
			pStmt = conn.prepareStatement(addSql);
			pStmt.setInt(1, owner.getStudentID());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CarPoolDBUtil.closeConnection();
		}
	}
	
	public CarBean getCarByOwner(int id) {
		CarBean car = new CarBean();
		try {
			conn = CarPoolDBUtil.getConnection();
			String selectSql = "SELECT * FROM Cars WHERE Owner =?";
			PreparedStatement pStmt = conn.prepareStatement(selectSql);

			pStmt.setInt(1, id);

			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				car.setPlateNumber(rs.getString("PlateNumber"));
				car.setMake(rs.getString("Make"));
				car.setModel(rs.getString("Model"));
				car.setSeats(Integer.parseInt(rs.getString("LastName")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CarPoolDBUtil.closeConnection();
		}

		return car;
	}
}