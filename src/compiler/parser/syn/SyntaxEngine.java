package compiler.parser.syn;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import compiler.analyzer.lex.Lexime;
import compiler.analyzer.lex.Token;
import compiler.parser.syn.classes.Goal;

public class SyntaxEngine {
	private static ArrayList<Lexime> leximes;
	private static Goal goal;
	public static void main(String[] args) {
		readTokens("tokens.txt");
		goal = new Goal(leximes);
		//todo now deal with it Gio :D
		System.out.println(goal);
	}
	private static void analyzeSyntax(){ //useless	
	}
	private static void readTokens(String fileName) {
		try {
			List<String> stringList = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
			leximes = new ArrayList<>();
			String[] line;
			String type;
			
			for(String str : stringList) {
				line = str.split(" ");
		//		System.out.println(line.length);
				type = line[0].substring(1, line[0].length() - 2);
		//		System.out.println(type);
				if(!type.equals("?COMMENT2") && !type.equals("COMMENT2") && !type.equals("EOL") && !type.equals("COMMENT1"))
					leximes.add(new Lexime(line[1], type));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Error reading the file.");
		}		
	}
	public static void error(MutableInt index){
		System.err.println("Syntax error within token " + leximes.get(index.getValue() - 1).type + " with value "
				+ leximes.get(index.getValue() - 1).value +  ", at index " + (index.getValue() - 1));
		System.exit(1);
	}

}
