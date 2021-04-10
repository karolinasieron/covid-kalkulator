package com.main;
/**
 * Software Entwicklung 2 Praktikum (SWE2P). SoSe 2021
 * 
 * @author Prof. Dr. Stefan Kugele, Technische Hochschule Ingolstadt
 * @version 2021-03-23
 */
public class ExposureNotificationApp {

	/**
	 * Please note: The CONTACT_SPEC_* "constants" are not final, but only for
	 * testing purposes. Do not modify these values - neither at compile nor
	 * runtime!
	 */

	public static final int MENU_QUIT = 0, MENU_NEW = 1, MENU_ADD = 2, MENU_ISEXPOSURE = 3, MENU_PRINT = 4;
	public static final int MENU_CONTACT_NO = 0, MENU_CONTACT_LOW = 1, MENU_CONTACT_HIGH = 2;

	public static String CONTACT_SPEC_HIGH_NAME = "HIGH", CONTACT_SPEC_LOW_NAME = "LOW", CONTACT_SPEC_NO_NAME = "NO";
	public static float CONTACT_SPEC_HIGH_VALUE = 0.7f, CONTACT_SPEC_LOW_VALUE = 0.3f, CONTACT_SPEC_NO_VALUE = 0.0f;
	public static float TRL_6 = 6.f, TRL_8 = 8.f, TRL_5 = 5.f, TRL_3 = 3.f, TRL_1 = 1.f;

	private static ExposureNotificationCalculator currentExposureNotificationCalculator;

	public static void main(String[] args) {
		do {
			System.out.println("Exposure Notification App\n=========================");
			printMenu();

			int selection = Input.readInt();
			switch (selection) {
			case MENU_QUIT: // 0
				System.out.println("Good bye");
				return; // end application
			case MENU_NEW: // 1
				newExposureNotificationCalculatorDialog();
				break;
			case MENU_ADD: // 2
				int type = showContactTypeDialog();
				doAddAction(type);
				break;
			case MENU_ISEXPOSURE: // 3
				checkCalcExists();
				boolean isExposedToHighRisk = currentExposureNotificationCalculator.isExposure(); // TODO: check is there is a high infection risk according to the current ExposureNotificationCalculator
				System.out.println("You are exposed to " + (isExposedToHighRisk ? "HIGH" : "NO") + " risk.");
				break;
			case MENU_PRINT: //4
				if (currentExposureNotificationCalculator == null) {
					System.out.println("You have not created an Exposure Calculator, yet.");
					newExposureNotificationCalculatorDialog();
				} else {
					String exposureNotificationCalculatorSummary = currentExposureNotificationCalculator.getSummary(); // TODO: get the current ExposureNotificationCalculator's summary
					System.out.println(exposureNotificationCalculatorSummary);
				}
				break;
			default:
				System.out.println("Unknown action!");
				break;
			}
		} while (true);

	}

	/**
	 * Action to add a {@link} t y p e
	 * 
	 * @param type of the selected add action
	 */
	private static void doAddAction(int type) { // 0, 1 or 2
		switch (type) {
		case MENU_CONTACT_NO: // 0
			currentExposureNotificationCalculator.addNoRiskContact();
			break;
		case MENU_CONTACT_LOW: // 1
			currentExposureNotificationCalculator.addLowRiskContact();
			break;
		case MENU_CONTACT_HIGH: // 2
			currentExposureNotificationCalculator.addHighRiskContact();
			break;
		}
	}

	/**
	 * Shows the on screen menu asking for the contact type to be added
	 * 
	 * @return Returns the selected type: (0: NO risk; 1: LOW risk, 2: HIGH risk)
	 */
	private static int showContactTypeDialog() {
		checkCalcExists();
		int chosenContactType = 0;
		boolean contactTypeExists = false;

		while (!contactTypeExists) {
			System.out.println("Which contact type do you want to add? (0: NO risk; 1: LOW risk, 2: HIGH risk)");
			chosenContactType = Input.readInt();
			if(chosenContactType == 0 || chosenContactType == 1 || chosenContactType == 2){
				contactTypeExists = true;
			}
			else{
				System.out.println("Wrong type, try again!");
				continue;
			}
			// contactTypeExists = ;// TODO: check if contact type exists according to the constants
		}
		return chosenContactType;
	}

	/**
	 * Checks if a current ExposureNotificationCalculator exists. If not,
	 * newExposureNotificationCalculatorDialog() is called
	 */
	private static void checkCalcExists() {
		if (currentExposureNotificationCalculator == null) {
			System.out.println("You have not created an Exposure Calculator, yet. Please do this in the following:");
			newExposureNotificationCalculatorDialog();
		}
	}

	/**
	 * Prints the on screen menu to chose between the different main actions.
	 */
	private static void printMenu() {
		System.out.println("Menu:\n-------\n" + "New Exposure:\t" + MENU_NEW + "\n" + "Add Contact: \t" + MENU_ADD
				+ "\n" + "Check Risk:\t" + MENU_ISEXPOSURE + "\n" + "Print Summary:\t" + MENU_PRINT + "\n" + "Quit:\t\t"
				+ MENU_QUIT + "\n");
	}

	/**
	 * Prints the on screen menu to set the observation horizon
	 */
	private static void newExposureNotificationCalculatorDialog() {
		int days = 0;
		do {
			System.out.println("Set observation horizon in days (0; 14]: ");
			days = Input.readInt();
		} while (days <= 0 || days > 14);

		currentExposureNotificationCalculator = new ExposureNotificationCalculator(days); // TODO: initialize new ExposureNotificationCalculator object with the specified property days
	}

}
