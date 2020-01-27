package Model;

import java.io.Serializable;

public class RiderBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int scheduleNO, studentID;
	private long phone;
	private String riderName, email;


	public int getScheduleNO() {
		return scheduleNO;
	}

	public void setScheduleNO(int scheduleNO) {
		this.scheduleNO = scheduleNO;
	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getRiderName() {
		return riderName;
	}

	public void setRiderName(String riderName) {
		this.riderName = riderName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}