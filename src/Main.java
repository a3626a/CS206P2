import java.util.Scanner;


public class Main {

	public static Scanner key = new Scanner(System.in);
	public static InvertedIndex invIndex;
	
	public static void main(String[] args) {
		System.out.println("==============================================================");
		System.out.println("This program is made by ChangHwan Lee, ChungWon Lee, and JaeWon Jung in 2015-Spring-CS206 Project2");
		System.out.println("==============================================================");
		System.out.println();
		
		while (true) {
			switch (menu()) {
			
			}
		}
	}
	
	public static int menu() {
		System.out.println("Enter a search command.");
		System.out.println("*multiple keywords are allowed");
		System.out.println("! for exclusion");

		int ret = key.nextInt();
		key.nextLine();

		return ret;
	}
	
}
