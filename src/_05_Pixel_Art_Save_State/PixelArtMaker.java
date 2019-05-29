package _05_Pixel_Art_Save_State;

import java.awt.FlowLayout;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

// TODO GridPanel constructor for File is not fully written
// TODO Art is not encoded properly
// TODO If you refuse to open the file last saved, then try to pick a different file,
//      it doesn't open the File-Opener Dialog or Quit.

public class PixelArtMaker implements MouseListener {
	private JFrame window;
	private GridInputPanel gip;
	private GridPanel gp;
	ColorSelectionPanel csp;

	public void start() {
		boolean openImg = true;
		File imgFile = null;
		
		if (JOptionPane.showConfirmDialog(null, "Open last saved image?", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			try {
				FileReader fr = new FileReader(new File("src/_05_Pixel_Art_Save_State/LATEST.txt"));
				BufferedReader br = new BufferedReader(fr);
				imgFile = new File(br.readLine());
				
				fr.close();
				br.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Couldn't open the file. :(");
			}
		} else if (JOptionPane.showConfirmDialog(null, "Open a different image?\n(Only files made with PixelArtMaker are supported.)", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			JFileChooser filech = new JFileChooser();
			filech.showOpenDialog(null);
			imgFile = filech.getSelectedFile();
		} else {
			openImg = false;
		}
		
		gip = new GridInputPanel(this);
		window = new JFrame("Pixel Art");
		window.setLayout(new FlowLayout());
		window.setResizable(false);

		if (openImg) {
			gp = new GridPanel(imgFile);
			window.add(gp);
		} else {
			window.add(gip);
		}
		window.pack();
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		window.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", null,
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

					if (JOptionPane.showConfirmDialog(null, "Do you want to save your beautiful art?", null,
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
						JFileChooser filech = new JFileChooser();
						filech.showSaveDialog(null);
						File file = filech.getSelectedFile();

						try {
							FileWriter fw = new FileWriter(file);

							for (Pixel[] p1 : gp.pixels) {
								for (Pixel p : p1) {
									fw.write(Encode.encode(p.color));
								}
								fw.write("\n");
							}
							fw.close();
							
							PrintWriter pw = new PrintWriter(new File("LATEST.txt"));
							pw.write(file.getPath());
							pw.close();

							System.exit(0);
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null,
									"Sorry, dear. Your\nbeautiful art couldn't\nbe saved. Try again.");
						}
					} else {
						System.exit(0);
					}
				}
			}
		});

		window.setVisible(true);
	}

	public void submitGridData(int w, int h, int r, int c) {
		gp = new GridPanel(w, h, r, c);
		csp = new ColorSelectionPanel();
		window.remove(gip);
		window.add(gp);
		window.add(csp);
		gp.repaint();
		gp.addMouseListener(this);
		window.pack();
	}

	public static void main(String[] args) {
		new PixelArtMaker().start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		gp.setColor(csp.getSelectedColor());
		gp.clickPixel(e.getX(), e.getY());
		gp.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
