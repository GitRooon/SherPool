package DAO;

import java.io.Serializable;
import Util.CarPoolDBUtil;
import Model.RiderBean;
import Model.ScheduleBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class ScheduleDao implements Serializable {
	private static final long serialVersionUID = 1L;

	Connection conn = null;

	public ArrayList<ScheduleBean> getAllSchedule() {
		ArrayList<ScheduleBean> schedules = new ArrayList<ScheduleBean>();
		ScheduleBean schedule;
		try {
			conn = CarPoolDBUtil.getConnection();
			Statement stmt = conn.createStatement();

			String sql = "SELECT * FROM Schedules "
					+ "INNER JOIN Cars ON Cars.PlateNumber = Schedules.PlateNumber "
					+ "INNER JOIN Users ON Users.StudentID = Cars.Owner";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				if (rs.getTimestamp("ExpiryDate").after(new Date())) {
					schedule = new ScheduleBean();
					schedule.setDriverName(rs.getString("LastName") + ", " + rs.getString("FirstName"));
					schedule.setSource(rs.getString("CampusSource"));
					schedule.setDestination(rs.getString("CampusDestination"));
					schedule.setExpiryDate(rs.getTimestamp("ExpiryDate"));
					schedule.setSeats(rs.getInt("Seats"));
					schedule.setScheduleNO(rs.getInt("ScheduleNO"));
					schedules.add(schedule);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CarPoolDBUtil.closeConnection();
		}
		return schedules;
	}
	
	public ScheduleBean getCurrentSchedule(int studentID) {
		ScheduleBean schedule = new ScheduleBean();
		try {
			conn = CarPoolDBUtil.getConnection();
			Statement stmt = conn.createStatement();

			String sql = "SELECT * FROM Schedules "
					+ "INNER JOIN Cars ON Cars.PlateNumber = Schedules.PlateNumber "
					+ "INNER JOIN Users ON Users.StudentID = Cars.Owner "
					+ "WHERE StudentID = " + studentID;
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				if (rs.getTimestamp("ExpiryDate").after(new Date())) {
					schedule = new ScheduleBean();
					schedule.setDriverName(rs.getString("LastName") + ", " + rs.getString("FirstName"));
					schedule.setSource(rs.getString("CampusSource"));
					schedule.setDestination(rs.getString("CampusDestination"));
					schedule.setExpiryDate(rs.getTimestamp("ExpiryDate"));
					schedule.setSeats(rs.getInt("Seats"));
					schedule.setScheduleNO(rs.getInt("ScheduleNO"));
					schedule.setStudentID(rs.getInt("StudentID"));
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CarPoolDBUtil.closeConnection();
		}
		return schedule;
	}
	
	public ScheduleBean getRiderSchedule(int studentID) {
		ScheduleBean schedule = new ScheduleBean();
		try {
			conn = CarPoolDBUtil.getConnection();
			Statement stmt = conn.createStatement();

			String sql = "SELECT * FROM " + 
					"    ScheduleDetails " + 
					"        INNER JOIN " + 
					"    Schedules ON Schedules.ScheduleNO = ScheduleDetails.ScheduleNO " + 
					"        INNER JOIN " + 
					"    Users ON Users.StudentID = ScheduleDetails.Rider " + 
					"        LEFT JOIN " + 
					"    Cars ON Cars.PlateNumber = Schedules.PlateNumber " + 
						"WHERE Rider = " + studentID;
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				if (rs.getTimestamp("ExpiryDate").after(new Date())) {
					schedule = new ScheduleBean();
					schedule.setDriverName(rs.getString("LastName") + ", " + rs.getString("FirstName"));
					schedule.setSource(rs.getString("CampusSource"));
					schedule.setDestination(rs.getString("CampusDestination"));
					schedule.setExpiryDate(rs.getTimestamp("ExpiryDate"));
					schedule.setSeats(rs.getInt("Seats"));
					schedule.setScheduleNO(rs.getInt("ScheduleNO"));
					schedule.setStudentID(rs.getInt("Rider"));
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CarPoolDBUtil.closeConnection();
		}
		return schedule;
	}
	
	public ScheduleBean checkSchedule(int scheduleNO) {
		ScheduleBean schedule = new ScheduleBean();
		try {
			conn = CarPoolDBUtil.getConnection();
			Statement stmt = conn.createStatement();

			String sql = "SELECT * FROM Schedules "
					+ "LEFT JOIN Cars ON Cars.PlateNumber = Schedules.PlateNumber "
					+ "LEFT JOIN Users ON Users.StudentID = Cars.Owner "
					+ "WHERE ScheduleNO = " + scheduleNO;
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				if (rs.getTimestamp("ExpiryDate").after(new Date())) {
					schedule = new ScheduleBean();
					schedule.setDriverName(rs.getString("LastName") + ", " + rs.getString("FirstName"));
					schedule.setEmail(rs.getString("Email"));
					schedule.setPlateNumber(rs.getString("PlateNumber"));
					schedule.setMake(rs.getString("Make"));
					schedule.setModel(rs.getString("Model"));
					schedule.setSource(rs.getString("CampusSource"));
					schedule.setDestination(rs.getString("CampusDestination"));
					schedule.setExpiryDate(rs.getTimestamp("ExpiryDate"));
					schedule.setScheduleNO(rs.getInt("ScheduleNO"));
					schedule.setStudentID(rs.getInt("StudentID"));
					schedule.setPhone(rs.getLong("Phone"));
					schedule.setSeats(rs.getInt("Seats"));
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CarPoolDBUtil.closeConnection();
		}
		return schedule;
	}
	
	public ArrayList<RiderBean> getRiders(int scheduleNO) {
		ArrayList<RiderBean> riders = new ArrayList<RiderBean>();
		RiderBean rider;
		try {
			conn = CarPoolDBUtil.getConnection();
			Statement stmt = conn.createStatement();

			String sql = "SELECT * FROM ScheduleDetails "
					+ "INNER JOIN Schedules ON Schedules.ScheduleNO = ScheduleDetails.ScheduleNO "
					+ "INNER JOIN Users ON Users.StudentID = ScheduleDetails.Rider "
					+ "WHERE Schedules.ScheduleNO = " + scheduleNO;
			
			
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
					rider = new RiderBean();
					rider.setStudentID(rs.getInt("Rider"));
					rider.setRiderName(rs.getString("LastName") + ", " + rs.getString("FirstName"));
					rider.setEmail(rs.getString("Email"));
					rider.setPhone(rs.getLong("Phone"));
					riders.add(rider);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CarPoolDBUtil.closeConnection();
		}
		return riders;
	}
	
	public void cancelSchedule(int scheduleNO) {
		try {
			conn = CarPoolDBUtil.getConnection();
			Statement stmt = conn.createStatement();

			String sql = "DELETE FROM ScheduleDetails WHERE ScheduleNO = " + scheduleNO;
			stmt.executeUpdate(sql);
			sql = "DELETE FROM Schedules WHERE ScheduleNO = " + scheduleNO;
			stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CarPoolDBUtil.closeConnection();
		}
	}
	
	public void cancelRide(int scheduleNO, int studentID) {
		ScheduleBean schedule = new ScheduleBean();
		try {
			conn = CarPoolDBUtil.getConnection();
			Statement stmt = conn.createStatement();

			String sql = "DELETE FROM ScheduleDetails WHERE ScheduleNO = " + scheduleNO + " AND Rider = " + studentID ;
			stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CarPoolDBUtil.closeConnection();
		}
	}
	
	public void addPickUp(int minutesSelected, String campusSource, String campusDestination, String carID)
	{
		Date date = new Date();
		long dateInMilli = date.getTime(); //THIS IS FOR DRIVERS TABLE AND DRIVERBEAN
		Timestamp tsDriver = new Timestamp(dateInMilli);
		long minutesToAdd = minutesSelected * 60000; //60000 = 1 minute in milliseconds
		dateInMilli += minutesToAdd;
		Timestamp tsExpiry = new Timestamp(dateInMilli);//FOR SCHEDULE TABLE, EXPIRY TIME this is current date + desired minutes from driver
		 
		try 
		{
			conn = CarPoolDBUtil.getConnection();
			
			String addToScheduleSql = "INSERT INTO Schedules (CampusSource,CampusDestination,ExpiryDate,PlateNumber) VALUES(?,?,?,?)";		
			PreparedStatement pStmt = conn.prepareStatement(addToScheduleSql);
			pStmt.setString(1, campusSource);
			pStmt.setString(2, campusDestination);
			pStmt.setTimestamp(3, tsExpiry);
			pStmt.setString(4, carID);
			pStmt.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			CarPoolDBUtil.closeConnection();
		}
	}
	
	public void addRider(int userID, int schedule)
	{
		try 
		{
			conn = CarPoolDBUtil.getConnection();
			
			String addToScheduleSql = "INSERT INTO ScheduleDetails VALUES (?,?)";		
			PreparedStatement pStmt = conn.prepareStatement(addToScheduleSql);
			pStmt.setInt(1, schedule);
			pStmt.setInt(2, userID);
			pStmt.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			CarPoolDBUtil.closeConnection();
		}
	}
}