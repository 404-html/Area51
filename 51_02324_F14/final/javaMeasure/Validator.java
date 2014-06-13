package javaMeasure;
import java.util.Scanner;

public class Validator {
	public static boolean validateUsername(String s){
		return s.matches("[a-zA-Z0-9@\\-\\_\\.]+");
	}
	
	public static boolean validatePassword(String s) {
		boolean valid = s.matches("[a-zA-Z0-9@\\.\\_\\-]+");	
		return valid && s.length() >=4 && s.length() <=100;	
	}
	
	public static void main(String[] args){

		Scanner scanner = new Scanner(System.in);
		String s = scanner.nextLine();
		System.out.println(s);
		System.out.println(validatePassword(s));
		System.out.println(validateUsername(s));
		scanner.close();

	}
}
