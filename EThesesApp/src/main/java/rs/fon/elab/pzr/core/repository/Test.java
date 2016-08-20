package rs.fon.elab.pzr.core.repository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {
		extractWords();

	}
	
	public static void extractWords(){
		String input = "Input text, with words, punctuation, etc. Well, it's rather short.";
		Pattern p = Pattern.compile("[\\w']+");
		Matcher m = p.matcher(input);

		while ( m.find() ) {
		    System.out.println(input.substring(m.start(), m.end()));
		}
	}

}
