package _01_File_Recorder;

import java.io.FileWriter;

import javax.swing.JOptionPane;

public class FileRecorder {
	// Create a program that takes a message from the user and saves it to a file.
	public static void main(String[] args) {
		try {
			FileWriter fw = new FileWriter("/Users/soda/Desktop/hi.txt");
			fw.write(JOptionPane.showInputDialog("Write something"));
			fw.close();
		} catch (Exception e) {
			
		}
	}
}
