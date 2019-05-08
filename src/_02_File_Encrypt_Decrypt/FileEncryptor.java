package _02_File_Encrypt_Decrypt;

import java.io.File;
import java.io.FileWriter;

import javax.swing.JOptionPane;

public class FileEncryptor {
	// Create a program that takes a message from the user.
	// Use the methods in the String and Character classes to save
	// an encrypted form of the message to a file
	public static void main(String[] args) {
		try {
			String input = JOptionPane.showInputDialog("Hi, dude");
			FileWriter fw = new FileWriter(new File("/home/leaguestudent/Desktop/dude.txt"));
			// For Brave Law: /Users/soda/
			// For LOAP computer: /home/leaguestudent/
			StringBuilder edit = new StringBuilder(input);
			for (int i = 0; i < edit.length(); i++) {
				char c = (char)(edit.charAt(i)+2);
				edit.replace(i, i+1, c+"");
			}
			input = edit.toString();
			
			fw.write(input);
			fw.close();
		} catch (Exception e) {
			
		}
	}
}
