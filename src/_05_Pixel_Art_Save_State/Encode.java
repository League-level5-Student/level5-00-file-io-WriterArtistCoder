package _05_Pixel_Art_Save_State;

import java.awt.Color;
import java.util.ArrayList;

public class Encode {
	
	char[] nonctrl;
	// The nonctrl char array is empty?!?

	public Encode() {
		ArrayList<Character> chars = new ArrayList<>();
		for (int i = 32; chars.size() < 256; i++) {
			if (i == 127) {
				i+=33;
			}
			
			chars.add((char)i);
		}
		
		nonctrl = new char[chars.size()];
	}
	
	public static void main(String[] args) {
		char[] nonctrl = new Encode().nonctrl;
		for (char c : nonctrl) {
			System.out.print(c);
		}
	}
	
	public static String encode(Color c) {
		char[] nonctrl = new Encode().nonctrl;
		
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();
		
		return ((char)nonctrl[r])+((char)nonctrl[g])+((char)nonctrl[b])+"";
	}
	
	public static Color decode(String s) throws IllegalArgumentException {
		char[] basicNonctrl = new Encode().nonctrl;
		ArrayList<Character> nonctrl = new ArrayList<>();
		for (char c : basicNonctrl) {
			nonctrl.add(c);
		}
		
		if (s.length() != 3) {
			throw new IllegalArgumentException("Input string was not encoded properly: "+s);
		}
		
		char r = s.charAt(0);
		char g = s.charAt(1);
		char b = s.charAt(2);
		
		try {
			return new Color(nonctrl.indexOf(r), nonctrl.indexOf(g), nonctrl.indexOf(b));
		} catch (Exception e) {
			throw new IllegalArgumentException("Input string was not encoded properly: "+s);
		}
	}

}
