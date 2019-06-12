package _05_Pixel_Art_Save_State;

import java.awt.Color;
import java.util.ArrayList;

public class Encode {
	
	ArrayList<Character> nonctrl;

	public Encode() {
		nonctrl = new ArrayList<>();
		
		for (int i = 32; nonctrl.size() < 256; i++) {
			if (i == 127) {
				i+=33;
			}
			
			nonctrl.add((char)i);
		}
	}
	
	public static void main(String[] args) {
		Encode e = new Encode();
		String s = "";
		for (char c : e.nonctrl) {
			s += c;
		}
		
		System.out.println(s);
		System.out.println(s.length());
		System.out.println(e.nonctrl.get(0)+e.nonctrl.get(0)+e.nonctrl.get(0));
	}
	
	public static String encode(Color c) {
		ArrayList<Character> nonctrl = new Encode().nonctrl;
		
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();
		
		return (""+nonctrl.get(r))+(""+nonctrl.get(g))+(""+nonctrl.get(b));
			}
	
	public static Color decode(String s) throws IllegalArgumentException {
		ArrayList<Character> basicNonctrl = new Encode().nonctrl;
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
