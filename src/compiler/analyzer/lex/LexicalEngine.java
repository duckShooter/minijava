package compiler.analyzer.lex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//NOTE: this structure is eligible to change later

public class LexicalEngine {
	private static StringBuilder leximesStr;
	private static ArrayList<Token> tokens;

	/**
	 * Reads a line of input and scan it to extract lexemes
	 */
	public static void scan() {
		tokens = Token.readFile("data.txt");
		
		//the string of all tokens
		leximesStr = new StringBuilder();
		
		for(Token temp : tokens)
			leximesStr.append(temp.regex + "|");
		leximesStr.deleteCharAt(leximesStr.length() - 1);//removing the last '|'
	    
		Pattern pat = Pattern.compile(leximesStr.toString());
		Matcher matcher = pat.matcher(readSyntax());
		while(matcher.find()){
			System.out.println(matcher.group());
			//finish this part then
		}
	}
	
	public static String readSyntax(){
		File file = new File("syntax.txt");
		
		try {
			FileInputStream fis = new FileInputStream(file);
			byte[] data = new byte[(int) file.length()];
			fis.read(data);
			fis.close();
			return new String(data, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public static void main(String[] args) {
		scan();
	}
}
