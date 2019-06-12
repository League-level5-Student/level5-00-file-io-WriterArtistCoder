package _05_Pixel_Art_Save_State;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.Random;

import org.junit.jupiter.api.Test;

class SayLikeEncoderDecoderOrWhatever {
	
	@Test
	void nonCtrl() {
		Encode e = new Encode();
		for (char c : e.nonctrl) {
			assertFalse(Character.isISOControl(c));
		}
	}

	@Test
	void encodeProperly() {
		Encode e = new Encode();
		for (int i = 0; i < 20000; i++) {
			Color p = new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256));
			String s = Encode.encode(p);
			assertEquals(Encode.decode(s), p);
		}
	}
	
	@Test
	void mockPxls() {
		
	}

}
