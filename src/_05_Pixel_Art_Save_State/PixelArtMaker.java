package _05_Pixel_Art_Save_State;

import java.awt.FlowLayout;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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


// TODO Clean up:
// TODO 1. Break down methods
// TODO 2. Keep hacking at methods
// TODO 3. Add comments?
// TODO 4. Write tests

public class PixelArtMaker implements MouseListener, MouseMotionListener {
	private JFrame window;
	private GridInputPanel gip;
	private GridPanel gp;
	boolean needsSaving;
	ColorSelectionPanel csp;

	public void start() {
		needsSaving = false;
		gip = new GridInputPanel(this);
		window = new JFrame("Pixel Art");
		window.setLayout(new FlowLayout());
		window.setResizable(false);

		File imgFile = getFile();
		
		if (imgFile == null) {
			window.add(gip);
		} else {
			try {
				BufferedReader br = new BufferedReader(new FileReader(imgFile));
				String[] properties = br.readLine().split(",");
				br.close();
				
				needsSaving = true;
				gp = new GridPanel(Integer.parseInt(properties[0]), Integer.parseInt(properties[1]),
						Integer.parseInt(properties[2]), Integer.parseInt(properties[3]));
				gp.setPixels(imgFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			window.add(gp);
		}
		window.pack();
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addSavingListener(window);

		window.setVisible(true);
	}
	
	public File getFile() {
		File imgFile = null;

		if (JOptionPane.showConfirmDialog(null, "Open last saved image?", null,
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			try {
				FileReader fr = new FileReader(new File("src/_05_Pixel_Art_Save_State/LATEST.txt"));
				BufferedReader br = new BufferedReader(fr);
				imgFile = new File(br.readLine());

				fr.close();
				br.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Couldn't open the file. :(");
			}
		} else if (JOptionPane.showConfirmDialog(null,
				"Open a different image?\n(Only files made with PixelArtMaker are supported.)", null,
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			JFileChooser filech = new JFileChooser();
			filech.showOpenDialog(null);
			imgFile = filech.getSelectedFile();
		}
		
		return imgFile;
	}
	
	public void addSavingListener(JFrame window) {
		window.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (needsSaving) {
					if (JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", null,
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

						if (JOptionPane.showConfirmDialog(null, "Do you want to save your beautiful art?", null,
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
							JFileChooser filech = new JFileChooser();
							filech.showSaveDialog(null);
							File file = filech.getSelectedFile();

							try {
								FileWriter fw = new FileWriter(file);
								fw.write(gp.properties() + "\n");

								for (Pixel[] p1 : gp.pixels) {
									for (Pixel p : p1) {
										fw.write(Encode.encode(p.color));
										System.out.print(Encode.encode(p.color));
									}
									fw.write("\n");
									System.out.println("");
								}
								fw.close();

								PrintWriter pw = new PrintWriter(new File("src/_05_Pixel_Art_Save_State/LATEST.txt"));

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
				} else {
					System.exit(0);
				}
			}
		});
	}
	
	public void submitGridData(int w, int h, int r, int c) {
		needsSaving = true;
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

	@Override
	public void mouseDragged(MouseEvent e) {
		gp.setColor(csp.getSelectedColor());
		gp.clickPixel(e.getX(), e.getY());
		gp.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}
}
