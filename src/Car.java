import java.text.DecimalFormat;

/**
 * 
 */

/**
 * @author Manu
 *
 */
public class Car {
	// store all the instance variables for the Car objects that we can reference when needed
	protected String make;
	protected String model;
	protected int year;
	protected double price;
	protected int carID;
	protected String carType;

	
	Car() {
		make = null;
		model = null;
		year = 0;
		price = 0.0;
	}

	Car(String make, String model, int year, double price) {

	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getCarID() {
		return carID;
	}

	public void setCarID(int carID) {
		this.carID = carID;
	}
	
	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	
	@Override
	public String toString() {
		//create a format to display the price correctly
		DecimalFormat df = new DecimalFormat("#,###.00");

		
		return String.format("%-1d", carID) + "." + String.format("%-18s", capsFirst(make)) + String.format("%-18s", capsFirst(model)) + String.format("%-18d", year) 
		+ "$" + String.format(df.format(price));
	}
	// method to format the make and model strings, will capitalize the first letter of each word
	public static String capsFirst(String str) {
	    String[] words = str.split(" ");
	    StringBuilder ret = new StringBuilder();
	    for(int i = 0; i < words.length; i++) {
	        ret.append(Character.toUpperCase(words[i].charAt(0)));
	        ret.append(words[i].substring(1));
	        if(i < words.length - 1) {
	            ret.append(' ');
	        }
	    }
	    return ret.toString();
	}

	

}
