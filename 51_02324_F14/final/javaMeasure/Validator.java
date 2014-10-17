package javaMeasure;

public class Validator {
	public static boolean validateUsername(String s){
		return s.matches("[a-zA-Z0-9@\\-\\_\\.]+");
	}
	
	public static boolean validatePassword(String s) {
		boolean valid = s.matches("[a-zA-Z0-9@\\.\\_\\-]+");	
		return valid && s.length() >=4 && s.length() <=100;	
	}
	
	public static boolean validateFloat(String s) {
		return s.matches("(-?[0-9]+\\.?[0-9]*)\\d*");
	}
	
	public static void main(String[] args){

//		Scanner scanner = new Scanner(System.in);
//		String s = scanner.nextLine();
//		System.out.println(s);
//		System.out.println(validatePassword(s));
//		System.out.println(validateUsername(s));
//		scanner.close();
		
		System.out.println(validateFloat("0,014"));
		System.out.println(validateFloat(".014"));
		System.out.println(validateFloat("123"));
		System.out.println(validateFloat("13.141514"));
		System.out.println(validateFloat("12341."));
		System.out.println(validateFloat(""));
		System.out.println(validateFloat("1.1"));

	}
}
