
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author Manu MotorsAdminConsole
 */
public class CarApp {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		// create a boolean used to loop through the program
		boolean proceedSale = true;

		// temporary value for the main array size
		int arrSizeTemp = 0;

		// initialize method to set up background music and welcome the user
		startingMethod(scan);

		// create an array to hold all car objects and the subclasses
		ArrayList<Car> arrCars = createCars(scan);

		while (proceedSale) {

			// get the size of the Car array
			arrSizeTemp = arrCars.size();

			while (arrSizeTemp != 0) {
				showNewCarDataBase(arrCars); // if the user enters a New car, add it to the array as a Car object
				showUsedCarDataBase(arrCars); // if the user enters a Used car, add it to the array as a UsedCar object
				int userSelection = selectCar(scan, arrCars, arrSizeTemp);// get the user input on which car they choose

				Car buyCarSelection = viewCar(scan, arrCars, userSelection);// set a reference to the Car object chosen
																			// by user
				if (buyCarSelection != null) {
					proceedSale = true;
				}
				// if the Car object is valid and present, remove it from the Car array
				if (proceedSale) {
					arrSizeTemp = deleteCar(arrCars, buyCarSelection, arrSizeTemp);

					System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
					// if the array is empty, let the user know all cars are sold
					if (arrCars.isEmpty()) {
						proceedSale = false;
						System.out.println("Business is good and we are all out of cars at this time! \n"
								+ "Please check back tomorrow for the new shipment.");
						// after buying a car, if more are available ask if the user wants to buy more
					} else {
						String userContinue = Validator.getStringYN(scan,
								"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + "Do you want to make another purchase?");

						if (userContinue.equalsIgnoreCase("n")) {
							proceedSale = false;
							System.out.println("Thank you for using the Grand Circus Motors admin console for the"
									+ " purchase of your new vehicle.\nOur finance department will be in touch shortly!");
							arrSizeTemp = 0;
						}
					}
				}

			}
		}
		scan.close();
	}

	/**
	 * @param scan
	 * @return
	 */
	// create the Car array and add the user input cars to the array as Car or
	// UsedCar instances based on user input
	private static ArrayList<Car> createCars(Scanner scan) {
		// Remember to change it to MinValue of 6
		int arrSize = Validator.getInt(scan, "How many cars are you entering: \n", 1, Integer.MAX_VALUE);

		ArrayList<Car> arrCars = new ArrayList<Car>(arrSize);

		int carNum = 1;

		for (int i = 0; i < arrSize; i++) {

			String carTypeTemp = Validator.getStringYN(scan, "Is car# " + carNum + " a new car? (y/n)");

			if (carTypeTemp.equalsIgnoreCase("y")) {

				Car car = new Car();
				arrCars.add(car);
				car.setCarID(carNum);
				car.setCarType(carTypeTemp);
				car.setMake(Validator.getStringLine(scan, "Enter Car #" + carNum + " Make: "));
				car.setModel(Validator.getStringLine(scan, "Enter Car #" + carNum + " Model: "));
				car.setYear(Math.abs(Validator.getInt(scan, "Enter Car #" + carNum + " Year: ", 1900, 2018)));
				car.setPrice(Math.abs(Validator.getDouble(scan, "Enter Car #" + carNum + " Price: ")));
				carNum++;

			} else if (carTypeTemp.equalsIgnoreCase("n")) {

				UsedCar usedCar = new UsedCar();
				arrCars.add(usedCar);
				usedCar.setCarID(carNum);
				usedCar.setCarType("n");
				usedCar.setMake(Validator.getStringLine(scan, "Enter Car #" + carNum + " Make: "));
				usedCar.setModel(Validator.getStringLine(scan, "Enter Car #" + carNum + " Model: "));
				usedCar.setYear(Math.abs(Validator.getInt(scan, "Enter Car #" + carNum + " Year: ", 1900, 2018)));
				usedCar.setPrice(Math.abs(Validator.getDouble(scan, "Enter Car #" + carNum + " Price: ")));
				usedCar.setMileage((Math.abs(Validator.getDouble(scan, "Enter Car #" + carNum + " Mileage: "))));

				carNum++;
			}

		}
		return arrCars;
	}

	/**
	 * @param scan
	 */
	// create the audio setup for the background music and greet the user
	private static void startingMethod(Scanner scan) {
		String fileLocation = null;
		System.out.println("Welcome to the Grand Circus Motors admin console!\n");
		System.out.println("Before we begin we would like to set the mood by first getting to know you better\n"
				+ "Would you say you are more of a 'SuperMario' fan or a 'StarWars' fan? \n");
		String userThemeSongChoice = Validator.getStringSelection(scan,
				"Please enter your selection (SuperMario or StarWars): ");
		if (userThemeSongChoice.equalsIgnoreCase("supermario")) {
			fileLocation = "C:/users/manu/eclipse-workspace/motorsadminconsole/SuperMario.mid";
		} else if (userThemeSongChoice.equalsIgnoreCase("starwars")) {
			fileLocation = "C:/users/manu/eclipse-workspace/motorsadminconsole/StarWarsCantinaband.mid";
		}
		AudioPlayerMaster audioPlayerMaster = new AudioPlayerMaster();
		audioPlayerMaster.setFileLocation(fileLocation);
		audioPlayerMaster.play();
	}

	// validate the user selected a valid option and create a reference to that Car
	// object
	public static Car viewCar(Scanner sc, ArrayList<Car> arr, int vehicleLocation) {
		Car soldCar = null;
		for (Car car : arr) {
			if (car.getCarID() == vehicleLocation) {
				String userChoiceTemp = Validator.getStringYN(sc, "Do you want to really buy this vehicle? (y/n): ");
				if (userChoiceTemp.equalsIgnoreCase("y")) {
					soldCar = car;
				}
			}
		}
		return soldCar;
	}

	// use the car object reference to remove the Car object from the array
	public static int deleteCar(ArrayList<Car> arr, Car carSelected, int arraySizeTemp) {
		arr.remove(carSelected);
		return arraySizeTemp--;
	}

	// prompt the user to select a car from the inventory
	public static int selectCar(Scanner sc, ArrayList<Car> arr, int totalCars) {
		int carSelection = 0;
		if (!arr.isEmpty()) {
			carSelection = Validator.getInt(sc,
					"\nPlease choose which vehicle you are interested in purchasing. Please enter the vehicle#: ", 1,
					totalCars);
			for (Car car : arr) {
				if (car.getCarID() == carSelection) {
					System.out.println(car.toString());
				}
			}
		}
		return carSelection;
	}

	// loop through the Car array and show the new cars
	public static void showNewCarDataBase(ArrayList<Car> arr) {
		if (!arr.isEmpty()) {
			System.out.println("\n\nCurrent New Vehicles Inventory:");
			for (Car car : arr) {
				if (car.getCarType().equalsIgnoreCase("y")) {
					System.out.println(car.toString());
				}
			}
		} else {
		}
	}

	// loop through the Car array and show the UsedCar cas
	public static void showUsedCarDataBase(ArrayList<Car> arr) {
		if (!arr.isEmpty()) {
			System.out.println("\n\nCurrent Used Vehicles Inventory:");
			for (Car car : arr) {
				if (car.getCarType().equalsIgnoreCase("n")) {
					System.out.println(car.toString());
				}
			}
		} else {
		}
	}
}
