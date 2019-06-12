package _05_Pixel_Art_Save_State;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JPanel;

public class GridPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private int windowWidth;
	private int windowHeight;
	private int pixelWidth;
	private int pixelHeight;
	private int rows;
	private int cols;

	// 1. Create a 2D array of pixels. Do not initialize it yet.

	private Color color;

	Pixel[][] pixels;

	public GridPanel(int w, int h, int r, int c) {
		this.windowWidth = w;
		this.windowHeight = h;
		this.rows = r;
		this.cols = c;

		this.pixelWidth = windowWidth / cols;
		this.pixelHeight = windowHeight / rows;

		color = Color.BLACK;

		setPreferredSize(new Dimension(windowWidth, windowHeight));

		// 2. Initialize the pixel array using the rows and cols variables.
		pixels = new Pixel[rows][cols];

		// 3. Iterate through the array and initialize each element to a new pixel.
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				pixels[i][j] = new Pixel(i, j);
			}
		}
	}

	public void setColor(Color c) {
		color = c;
	}

	public void clickPixel(int mouseX, int mouseY) {
		// 5. Use the mouseX and mouseY variables to change the color
		// of the pixel that was clicked. *HINT* Use the pixel's dimensions.
		pixels[mouseX / pixelWidth][mouseY / pixelHeight].color = color;
	}

	@Override
	public void paintComponent(Graphics g) {
		// 4. Iterate through the array.
		// For every pixel in the list, fill in a rectangle using the pixel's color.
		// Then, use drawRect to add a grid pattern to your display.
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				g.setColor(pixels[i][j].color);
				g.fillRect(i * pixelWidth, j * pixelHeight, pixelWidth, pixelHeight);
			}
		}

		g.setColor(Color.BLACK);
		for (int i = 0; i < rows; i++) {
			g.drawLine(0, i * pixelHeight, windowWidth, i * pixelHeight);
		}

		for (int i = 0; i < cols; i++) {
			g.drawLine(i * pixelWidth, 0, i * pixelWidth, windowHeight);
		}
	}

	public String properties() {
		return windowWidth + "," + windowHeight + "," + rows + "," + cols;
	}

	public void setPixels(File imgFile) {
		try {
			BufferedReader b = new BufferedReader(new FileReader(imgFile));
			b.readLine(); // Prevents from reading special line
			
			for (Pixel[] p1 : pixels) {
				String line;
				line = b.readLine();

				int i = 0;

				for (Pixel p : p1) {
					String s = line.substring(i, i + 3);
					p.color = Encode.decode(s);
					i += 3;
					
					System.out.print(s);
				}
				System.out.println("");
			}

			b.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
