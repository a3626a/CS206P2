import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 *  THIS CLASS HAS NO RELATIONS WITH THE PROGRAM.
 *  Only for testing file I/O methods.
 *  Delete this class right before you submit our project.
 */

public class Check {
	public static void main(String[] args) throws FileNotFoundException {
		String filepath = "documents\\";
		File d1 = new File(filepath + "doc1.txt");
		boolean b = d1.exists();
		File d8 = new File(filepath + "doc8.txt");
		boolean b2 = d8.exists();
		System.out.println(b);
		System.out.println(b2);
		File f1 = new File(filepath + "doc1.txt");
		Scanner n = new Scanner (f1);
		String s = n.nextLine();
		String[] splited = s.split(" ");
		for (String chunk : splited) {
			System.out.println(chunk);
		}
		System.out.println(n.hasNextLine());
	}
}
