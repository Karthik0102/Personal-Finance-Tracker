package application;

import java.util.Arrays;
import java.util.regex.Pattern;

public class InputValidator {

	public static boolean isValidAmount(double amount) {
		return amount > 0;
	}

	public static boolean isValidDate(String date) {
		String regex = "^\\d{4}-\\d{2}-\\d{2}$";
		return Pattern.matches(regex, date);
	}

	public static boolean isValidTransctionType(String type) {
		return Arrays.asList("income", "expense").contains(type);
	}

	public static boolean isValidDescription(String description) {
		return !description.isEmpty();
	}

}
