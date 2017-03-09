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
	private static ArrayList<Lexime> leximes;

	/**
	 * Reads a line of input and scan it to extract lexemes
	 */
	public static void scan() {
		tokens = Token.readFile("data.txt");
		leximes = new ArrayList<>();
		
		//the string of all tokens
		leximesStr = new StringBuilder();
		
		for(Token temp : tokens)
			leximesStr.append(temp.regex + "|");
		leximesStr.deleteCharAt(leximesStr.length() - 1);//removing the last '|'
	    
	//	System.out.println(leximesStr.toString());
		//outer loop
		Pattern pat = Pattern.compile(leximesStr.toString());
		Matcher matcher = pat.matcher(readSyntax());
		
		//inner loop
		Pattern tempPat;
		Matcher tempMatcher;
		
		while(matcher.find()){
		//	System.out.println(matcher.group());
			for(Token tempToken : tokens){
				//System.out.println(tempToken.regex + " " + matcher.group());
				tempPat = Pattern.compile(tempToken.regex);
				tempMatcher = Pattern.compile(tempToken.regex).matcher(matcher.group());
				if(tempMatcher.find() && tempMatcher.start() == 0) {
						leximes.add(new Lexime(matcher.group(), tempToken.type));
						break;
					}
			}
		}
	}
	
	private static String readSyntax(){
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

	private static void WriteTokens() {
		//just for testing purpose
		for(Lexime lex : leximes)
			System.out.println(lex.type + " " + lex.value);
	}
	public static void main(String[] args) {
		scan();
		WriteTokens();
	}

}
