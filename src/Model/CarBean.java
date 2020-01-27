package Model;

import java.io.Serializable;

public class CarBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String PlateNumber,
				Make,
				Model;
	private int Seats;

	public String getPlateNumber() {
		return PlateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		PlateNumber = plateNumber;
	}

	public String getMake() {
		return Make;
	}

	public void setMake(String make) {
		Make = make;
	}

	public String getModel() {
		return Model;
	}

	public void setModel(String model) {
		Model = model;
	}

	public int getSeats() {
		return Seats;
	}

	public void setSeats(int seats) {
		this.Seats = seats;
	}
}
