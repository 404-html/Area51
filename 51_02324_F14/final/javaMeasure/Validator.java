package javaMeasure;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
	public static boolean validateUsername(String s){
		Pattern p = Pattern.compile("[a-zæøåú0-9.@]+");
		Matcher m = p.matcher(s);
		return m.matches();
	}
}
