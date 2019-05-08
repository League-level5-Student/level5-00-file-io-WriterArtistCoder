package _02_File_Encrypt_Decrypt;

import java.io.FileReader;

import javax.swing.JOptionPane;

public class FileDecryptor {
	// Create a program that opens the file created by FileEncryptor and display
	// the decrypted message to the user in a JOptionPane.
	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader("/home/leaguestudent/Desktop/dude.txt");
			// For Brave Law: /Users/soda/
			// For LOAP computer: /home/leaguestudent/
			String s = "";
			int c = fr.read();
			while (c != -1){
				s += (char)(c-2);
				c = fr.read();
			}
			fr.close();
			
			System.out.println(s);
			JOptionPane.showMessageDialog(null, s);
		} catch (Exception e) {
			System.out.print("mcdonaldas");
			e.printStackTrace();
		}
	}
}
