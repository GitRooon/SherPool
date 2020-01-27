package DAO;

import java.io.Serializable;
import Util.CarPoolDBUtil;
import Model.StudentBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class StudentDao implements Serializable{
	private static final long serialVersionUID = 1L;

	Connection conn = null;
	
//	add a driver to the database
	public void addStudent(StudentBean student) {
		try {
			conn = CarPoolDBUtil.getConnection();
			String addSql = "INSERT INTO Users" + "(StudentID,Password,FirstName,LastName,Email,Phone)"
					+ "VALUES (?,?,?,?,?,?)";

			PreparedStatement pStmt = conn.prepareStatement(addSql);
			pStmt.setInt(1, student.getStudentID());
			pStmt.setString(2, student.getPassword());
			pStmt.setString(3, student.getFirstName());
			pStmt.setString(4, student.getLastName());
			pStmt.setString(5, student.getEmail());
			pStmt.setLong(6, student.getPhone());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CarPoolDBUtil.closeConnection();
		}
	}

	public StudentBean getUserByID(int id) {
		StudentBean student = new StudentBean();
		try {
			conn = CarPoolDBUtil.getConnection();
			String selectSql = "SELECT * FROM Users WHERE StudentID =?";
			PreparedStatement pStmt = conn.prepareStatement(selectSql);

			pStmt.setInt(1, id);

			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				student.setStudentID(Integer.parseInt(rs.getString("StudentID")));
				student.setPassword(rs.getString("Password"));
				student.setFirstName(rs.getString("FirstName"));
				student.setLastName(rs.getString("LastName"));
				student.setEmail(rs.getString("Email"));
				student.setPhone(Long.parseLong(rs.getString("Phone")));
				student.setDate(rs.getTimestamp("DateRegistered"));
				student.setDriver(rs.getBoolean("Driver"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CarPoolDBUtil.closeConnection();
		}

		return student;
	}
}

//	public void addPickUp(int minutesSelected, DriverBean d, String campusSource, String campusDestination)
//	{
//		Connection conn = null;
//		Date date = new Date();
//		long dateInMilli = date.getTime(); //THIS IS FOR DRIVERS TABLE AND DRIVERBEAN
//		Timestamp tsDriver = new Timestamp(dateInMilli);
//		long minutesToAdd = minutesSelected * 60000; //60000 = 1 minute in milliseconds
//		dateInMilli += minutesToAdd;
//		Timestamp tsExpiry = new Timestamp(dateInMilli);//FOR SCHEDULE TABLE, EXPIRY TIME this is current date + desired minutes from driver
//		 
//		try 
//		{
//			conn = CarPoolDbUtil.getConnection();
//			//START add date and time
//			String updateDateSql = "UPDATE Drivers SET dateandtime=? WHERE driverid=?";
//			
//			PreparedStatement ps = conn.prepareStatement(updateDateSql);
//			ps.setTimestamp(1, tsDriver);
//			ps.setInt(2, d.getDriverid());
//			ps.executeUpdate();
//			d.setDate(tsDriver);
//			
//			String addToScheduleSql = "INSERT INTO schedule"
//					+ "(driverid,firstname,lastname,email,phone,campussource,campusdestination,datecreated,expirydate)"
//					+ "VALUES(?,?,?,?,?,?,?,?,?)";		
//			PreparedStatement pStmt = conn.prepareStatement(addToScheduleSql);
//			pStmt.setInt(1, d.getDriverid());
//			pStmt.setString(2, d.getFirstName());
//			pStmt.setString(3, d.getLastName());
//			pStmt.setString(4, d.getEmail());
//			pStmt.setLong(5, d.getPhone());
//			pStmt.setString(6, campusSource);
//			pStmt.setString(7, campusDestination);
//			pStmt.setTimestamp(8, tsDriver);
//			pStmt.setTimestamp(9, tsExpiry);
//			pStmt.executeUpdate();
//		}
//		catch(SQLException e)
//		{
//			e.printStackTrace();
//		}
//		finally
//		{
//			CarPoolDbUtil.closeConnection();
//		}
//	}
//
//	public ArrayList<Schedule> getScheduleRecords()

