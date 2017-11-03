import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author Manu
 *
 */
public class CarApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.println("Welcome to the Grand Circus Motors admin console!");
		int arrSize = Validator.getInt(scan, "How many cars are you entering: \n");

		Car[] arrCars = new Car[arrSize];
		int carNum = 1;

		for (int i = 0; i < arrSize; i++) {

			Car car = new Car();
			arrCars[i] = car;
			car.setMake(Validator.getStringLine(scan, "Enter Car #" + carNum + " Make: "));
			car.setModel(Validator.getStringLine(scan, "Enter Car #" + carNum + " Model: "));
			car.setYear(Math.abs(Validator.getInt(scan, "Enter Car #" + carNum + " Year: ")));
			car.setPrice(Math.abs(Validator.getDouble(scan, "Enter Car #" + carNum + " Price: ")));
			carNum++;
		}

		System.out.println("Current Inventory:");
		showDataBase(arrCars);
		scan.close();
		
		System.out.println("Thank you for using the Grand Circus Motors admin console!");
	}

	public static void showDataBase(Car[] arr) {

		for (Car car : arr) {

			String makeInput = car.getMake().toString();
			String make = makeInput.substring(0, 1).toUpperCase() + makeInput.substring(1);
			String model = car.getModel().toString();
			int year = car.getYear();
			double price = car.getPrice();

			DecimalFormat df = new DecimalFormat("#,###.00");

			
			System.out.printf("%-18s", capsFirst(make));
			System.out.printf("%-18s", capsFirst(model));
			System.out.printf("%-18d", year);
			System.out.print("$");
			System.out.println(df.format(price));

		}
	}
	
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
