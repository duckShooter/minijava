package compiler.analyzer.lex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class dd {
	public static void main(String[] args) {
		Pattern p = Pattern.compile("(?<![!@#$%^&*+'\"~.:`-])\\b(?<STRING>string)\\b(?![!@#$%^&*+'\"~.:`-])");
		Matcher m = p.matcher("string mystring = 3;\n string+ s;string s = stringm");
		while(m.find())
			System.out.println(m.group("STRING") + " " + m.start() + " " + m.end());
	}
}
