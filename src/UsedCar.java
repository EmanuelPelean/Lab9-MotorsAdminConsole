import java.text.DecimalFormat;

public class UsedCar extends Car {

	private double mileage;
	
	public UsedCar() {}
	//get all the instance variables from the parent class
	public UsedCar(String make, String model, int year, double price, double mileage) {
		super(make, model, year, price);
		
	}

	public double getMileage() {
		return mileage;
	}

	public void setMileage(double mileage) {
		this.mileage = mileage;
	}
	
	@Override
	public String toString() {
	
		DecimalFormat df = new DecimalFormat("#,###.00"); // format for price
		DecimalFormat dfi = new DecimalFormat("#,###.0"); // format for mileage

		
		return String.format("%-1d", carID)+ "." + String.format("%-18s", capsFirst(make)) + String.format("%-18s", capsFirst(model)) + String.format("%-18d", year) 
		+ "$" + String.format(df.format(price)) + " (Used) " + String.format(dfi.format(mileage)) + " miles";
	}
	
	

}
