package compiler.analyzer.lex;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Token {
	public String regex; //The regular expression representing (pattern) this token 
	public String type; //The token class
	
	public Token(String regex, String type){
		this.regex = regex;
		this.type = type;
	} 
	
	public static ArrayList<Token> loadTokens(String file){
		try {
			List<String> stringList = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
			ArrayList<Token> tokens = new ArrayList<>();
			String[] line;
			
			for(String str : stringList) {
				line = str.split(" ");
				tokens.add(new Token(line[1], line[0]));
			}
			return tokens;
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Error reading the file.");
		}
		return null;
	}
}