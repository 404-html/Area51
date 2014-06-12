package javaMeasure;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
	public static boolean validateUsername(String s){
		return validate(s, "[^a-zA-Z0-9@\\.\\_\\-øæåØÆÅú]");
	}
	
	public static boolean validatePassword(String s) {
		boolean valid =  validate(s,"[^a-zA-Z0-9@\\.\\_\\-øæåØÆÅú]");	
		return valid && s.length() >=4 && s.length() <=100;	
	}
	
private static boolean validate(String s, String pattern) {
	Pattern p = Pattern.compile(pattern);
	Matcher m = p.matcher(s);
	//return true for no illegal
	return !m.matches();
}
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		String s = scanner.next();
		System.out.println(Validator.validatePassword(s));
		scanner.close();
	}
}
