import java.io.File;
import java.io.FileNotFoundException;
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
		buildInvertedIndex(invIndex);
		
	}
	
	// Currently this method is not used.
	// Implemented for future use.
	public static int menu() {
		System.out.println("Enter a search command.");
		System.out.println("*multiple keywords are allowed");
		System.out.println("! for exclusion");

		int ret = key.nextInt();
		key.nextLine();

		return ret;
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
	public static void buildInvertedIndex (InvertedIndex invIndex) throws FileNotFoundException {
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
						System.out.printf("%d %d %s\n", filenumber, wordcount, word);
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
