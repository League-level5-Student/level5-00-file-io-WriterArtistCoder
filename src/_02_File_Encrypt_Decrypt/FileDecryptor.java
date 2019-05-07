package _02_File_Encrypt_Decrypt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

public class FileDecryptor {
	// Create a program that opens the file created by FileEncryptor and display
	// the decrypted message to the user in a JOptionPane.
	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader("/Users/soda/Desktop/dude.txt");
			String s = "";
			int c = fr.read();
			while (c != -1){
				s += (char)fr.read()-2+"";
			}
			fr.close();
			
			System.out.println(s+"hi");
			JOptionPane.showMessageDialog(null, s);
		} catch (Exception e) {
			System.out.print("mcdonaldas");
			e.printStackTrace();
		}
	}
}
