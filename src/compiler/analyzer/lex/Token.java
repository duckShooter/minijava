package compiler.analyzer.lex;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Token {
	public String regex;
	public String type;
	public Token(String regex, String type){
		this.regex = regex;
		this.type = type;
	}
	
	public static ArrayList<Token> readFile(String file){
		try {
			List<String> stringList = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
			ArrayList<Token> tokens = new ArrayList<>();
			String[]line;
			for(String str : stringList){
				line = str.split(" ");
				tokens.add(new Token(line[1], "<" + line[0] + ">"));
			//	System.out.println(line[0] + " " + line[1]);
			}
			return tokens;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
