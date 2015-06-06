import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

	public static Scanner key = new Scanner(System.in);
	public static InvertedIndex invIndex;
	
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("==============================================================");
		System.out.println("This program is made by ChangHwan Lee, ChungWon Lee, and JaeWon Jung in 2015-Spring-CS206 Project2");
		System.out.println("==============================================================");
		System.out.println();
		
		invIndex = new InvertedIndex();
		buildInvertedIndex();
		invIndex.deltaCompression();
		
		while (true) {
			boolean cont = search_input_print_result();
			if (!cont) break;
		}
		
	}
	
	// Currently this method is not used.
	// Implemented for future use.
	public static boolean search_input_print_result() {
		System.out.println();
		System.out.println("Enter a search command.");
		System.out.println("* Multiple keywords are allowed");
		System.out.println("* For exclusion, use '!' command. (ex) !search");
		System.out.println("* To quit the program, enter '%QUIT'. ");
		System.out.print(">> ");

		String searchwords = key.nextLine();
		if (searchwords.equals("%QUIT")) return false;
		String[] keywords = searchwords.split(" ");
		
		ArrayList<Dict> mergeKeywords = new ArrayList<Dict>();
		ArrayList<Dict> excludeKeywords = new ArrayList<Dict>();
		for (String word : keywords) {
			if (word.charAt(0) == '!') {
				String subword = word.substring(1, word.length());
				Node n = invIndex.find(subword);
				if (n == null) continue;
				Dict d = n.getList();
				excludeKeywords.add(d);
			}
			else {
				Node n = invIndex.find(word);
				if (n == null) continue;
				Dict d = n.getList();
				mergeKeywords.add(d);
			}
		}
		
		if (mergeKeywords.size() == 0) return true;
		
		while (mergeKeywords.size() > 1) {
			Dict d1 = mergeKeywords.remove(0);
			Dict d2 = mergeKeywords.remove(0);
			mergeKeywords.add(0, d1.mergeCompressedDict(d2));
		}
		Dict result = mergeKeywords.get(0);
		while (excludeKeywords.size() > 0) {
			Dict d3 = excludeKeywords.remove(0);
			result = result.excludeCompressedDict(d3);
		}
		
		result.printDict();
		return true;
	}
	
	/*
	 *  method: buildInvertedIndex
	 *  inputs: one InvertedIndex object
	 *  outputs: none
	 *  
	 *  This method traverse through all input documents and add all word data into 
	 *  the given input object.
	 *  
	 */
	public static void buildInvertedIndex () throws FileNotFoundException {
		String filepath = "documents\\";
		int filenumber = 1;
		while (true) {
			File f = new File (filepath + "doc" + filenumber + ".txt");
			if (f.exists()) {
				int wordcount = 1;
				Scanner filereader = new Scanner (f);
				while (filereader.hasNextLine()) {
					String sentence = filereader.nextLine();
					String[] splited = sentence.split(" ");
					process_input(splited);
					for (String word : splited) {
						invIndex.add(word, filenumber, wordcount);
						//test
						// System.out.printf("%d %d %s\n", filenumber, wordcount, word);
						//test end
						wordcount++;
					}
				}
				filereader.close();
			}
			else break;
			filenumber++;
		}
	}
	
	/*
	 *  method: process_input
	 *  inputs: String[] splited
	 *  outputs: none
	 *  
	 *  This method modifies given array's every string element to exclude characters
	 *  except alphabets/numbers.
	 *  
	 */
	public static void process_input (String[] splited) {
		for (int i=0; i<splited.length; i++) {
			String result = "";
			for (int j=0; j<splited[i].length(); j++) {
				if (validChar(splited[i].charAt(j))) result = result + splited[i].charAt(j);
			}
			splited[i] = result;
		}
	}
	
	/*
	 *  method: validChar
	 *  inputs: char c
	 *  outputs: boolean b
	 *  
	 *  returns TRUE if given c is valid(alphabets or numbers). Otherwise returns FALSE.
	 */
	public static boolean validChar (char c) {
		return ((c >= '0')&&(c <= '9')) || ((c >= 'A')&&(c <= 'Z')) || ((c >= 'a')&&(c <= 'z'));
	}
}
