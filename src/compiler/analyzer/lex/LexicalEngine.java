package compiler.analyzer.lex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

//NOTE: this structure is eligible to change later

public class LexicalEngine {
	private static StringBuilder leximesStr; //One line of code
	private static ArrayList<Token> tokens; //List of recognizable tokens
	private static ArrayList<Lexime> leximes; //List of lexical units

	/**
	 * Reads a line of input and scan it to extract lexemes
	 */
	public static void scan() {
		tokens = Token.loadTokens("togex.txt");
		leximes = new ArrayList<>();
		
		//the string of all tokens
		leximesStr = new StringBuilder(); //StringBuilder for flexible string manipulation
		for(Token temp : tokens)
			leximesStr.append(temp.regex + "|");
		
		leximesStr.deleteCharAt(leximesStr.length() - 1);//removing the last '|'
	   // System.out.println("865: " +  leximesStr.substring(865));
	//	System.out.println(leximesStr.toString());
		//outer loop
	//    System.out.println(readSyntax());
		String syntax = readSyntax();
		Pattern pat = Pattern.compile(leximesStr.toString());
		Matcher matcher = pat.matcher(syntax);
		
		//inner loop
		Pattern tempPat;
		Matcher tempMatcher;
		int last = -1;
		while(matcher.find()){
		//	System.out.println(matcher.group());
			if(last != -1 && last < matcher.start()){
				for(int i = last;i < matcher.start();i++)
					if(syntax.charAt(i) != ' '){
						//System.out.println("<ERROR> " + syntax.substring(last, matcher.start()));
						leximes.add(new Lexime(syntax.substring(i, matcher.start()), "ERROR"));
						break;
					}
			} else if(last == -1 && matcher.start() > 1){
				for(int i = 0;i < matcher.start();i++)
					if(syntax.charAt(i) != ' '){
						//System.out.println("<ERRO R> " + syntax.substring(0, matcher.start()));
						leximes.add(new Lexime(syntax.substring(i, matcher.start()), "ERROR"));
						break;
					}
			}
			last = matcher.end();
			for(Token tempToken : tokens){
				//System.out.println(tempToken.regex + " " + matcher.group());
				tempPat = Pattern.compile(tempToken.regex);
				tempMatcher = Pattern.compile(tempToken.regex).matcher(matcher.group());
				if(tempMatcher.find() && tempMatcher.start() == 0) {
						leximes.add(new Lexime(tempToken.type.equals("EOL") ? "" : matcher.group(), tempToken.type));
						break;
					}
			}
		}
		for(int i = last;i < syntax.length();i++)
			if(syntax.charAt(i) != ' '){
				leximes.add(new Lexime(syntax.substring(i, syntax.length()), "ERROR"));
				break;
			}	
	}
	
	private static String readSyntax() {
		File file = new File("main.mj");
		try {
			
			/*FileInputStream fis = new FileInputStream(file);
			byte[] data = new byte[(int) file.length()];
			fis.read(data);
			fis.close(); */
			return new String(Files.readAllBytes(Paths.get("main.mj")), "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void WriteTokens() {
		//this is added by andrew till the next *//*
		StringBuilder builder = new StringBuilder("");
		for(Lexime unit : leximes)
			builder.append("<" + unit.type + ">: " + unit.value + "\r\n");
	
		try {
			PrintWriter writer = new PrintWriter("tokens.txt");
			writer.print(builder.toString());
			writer.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		try {
			Document xmlResult = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			Element root = xmlResult.createElement("source");
			Element tokens = xmlResult.createElement("tokens");
			for(Lexime unit : leximes) {
				System.out.format("<%s> : %s\n", unit.type, unit.value);
				Element token = xmlResult.createElement("token");
				token.setAttribute("type", unit.type);
				token.setTextContent(unit.value);
				tokens.appendChild(token);
			}
			root.appendChild(tokens);
			xmlResult.appendChild(root);
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			transformer.transform(new DOMSource(xmlResult), new StreamResult("result.xml"));
		
		} catch (ParserConfigurationException | TransformerFactoryConfigurationError
				| TransformerException e) {
			e.printStackTrace();
		}
			//System.out.println(lex.type + " " + lex.value);
	}
	
	public static void main(String[] args) {
		scan();
		WriteTokens();
	}
}
