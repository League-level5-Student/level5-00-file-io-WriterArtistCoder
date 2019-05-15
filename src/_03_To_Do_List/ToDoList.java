package _03_To_Do_List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ToDoList {
	/*
	 * Create a program with five buttons, add task, view tasks, remove task, save
	 * list, and load list.
	 * 
	 * When add task is clicked: ask the user for a task and save it to an array
	 * list
	 * 
	 * When the view tasks button is clicked: show all the tasks in the list
	 * 
	 * When the remove task button is clicked: prompt the user for which task to
	 * remove and take it off of the list.
	 * 
	 * When the save list button is clicked: Save the list to a file
	 * 
	 * When the load list button is clicked: Prompt the user for the location of the
	 * file and load the list from that file
	 * 
	 * When the program starts, it should automatically load the last saved file
	 * into the list.
	 */

	public ArrayList<String> tasks;
	public boolean loaded;
	public String pathname;

	public ToDoList() {
		tasks = new ArrayList<>();
		loaded = false;

		if (JOptionPane.showConfirmDialog(null, "Load last saved list?", null,
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			try {
				FileReader fr = new FileReader(new File("src/_03_To_Do_List/todo_files/LATEST.txt"));
				BufferedReader br = new BufferedReader(fr);
				pathname = br.readLine();
				FileReader fr1 = new FileReader(new File(pathname));
				BufferedReader br1 = new BufferedReader(fr1);

				String line = br1.readLine();

				while (tasks.size() > 0) {
					tasks.remove(0);
				}

				while (line != null) {
					tasks.add(line);
					line = br1.readLine();
				}

				br.close();
				br1.close();
				loaded = true;
			} catch (IOException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "There was an error loading the last saved list.");
			}
		}

	}

	public static void main(String[] args) {
		ToDoList list = new ToDoList();

		JFrame frame = new JFrame("ToDoList");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.add(panel);

		JButton add = new JButton("Add task");
		JButton view = new JButton("View tasks");
		JButton remove = new JButton("Remove task");
		JButton save = new JButton("Save list");
		JButton load = new JButton("Load list");

		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				list.tasks.add(JOptionPane.showInputDialog(
						"Dude, I am thinking you should type a task in here\nthat I will put in your to-do list, THAT SOUNDS GOOD TO ME!!!!"));
			}

		});
		panel.add(add);

		view.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String display = "";
				for (int i = 0; i < list.tasks.size(); i++) {
					display += (i + 1) + ". " + list.tasks.get(i) + "\n";
				}

				if (list.tasks.size() > 0) {
					JOptionPane.showMessageDialog(null,
							"Homie, check it. I have found this dude\nto read your todo list to you.\nTASKS:\n"
									+ display);
				} else {
					JOptionPane.showMessageDialog(null,
							"Homie, check it. Your to-do list is LEGIT EMPTY.\nHow about filling it up with awesome-packed tasks?");
				}
			}

		});
		panel.add(view);

		remove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String display = "";
				for (int i = 0; i < list.tasks.size(); i++) {
					display += (i + 1) + ". " + list.tasks.get(i) + "\n";
				}

				if (list.tasks.size() > 0) {
					String input = JOptionPane.showInputDialog("Homie, check it. These are your tasks:\nTASKS:\n"
							+ display
							+ "\nNow, bro, type the number of which one\n you want to remove or press Cancel to cancel.");
					if (input != null) {
						list.tasks.remove(Integer.parseInt(input) - 1);
						JOptionPane.showMessageDialog(null,
								"Ok, homie, I just pwned that task\ninto oblivion. It has been deleted.");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Homie, check it. Your to-do list is LEGIT EMPTY.\nHow about filling it up with awesome-packed tasks?");
				}
			}

		});
		panel.add(remove);

		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (list.loaded) {
					try {
						FileWriter fw = new FileWriter(new File(list.pathname));
						for (String s : list.tasks) {
							fw.write(s + "\n");
						}

						fw.close();

						JOptionPane.showMessageDialog(null, "Saved!");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Homie, PREPARE YOUR FACE.\n I am going to massage your eyes with a file chooser.\nTell it where you wanna save dis list.");
					JFileChooser j = new JFileChooser();
					j.showSaveDialog(null);

					if (j.getSelectedFile() != null) {
						String path = j.getSelectedFile().getPath();
						if (!path.endsWith(".txt"))
							path += ".txt";

						try {
							// I had to use a PrintWriter
							// because a FileWriter would not
							// clear the file's contents.
							PrintWriter pw = new PrintWriter("src/_03_To_Do_List/todo_files/LATEST.txt");
							pw.write(path);
							pw.close();

							FileWriter fw = new FileWriter(new File(path));
							for (String s : list.tasks) {
								fw.write(s + "\n");
							}

							fw.close();
						} catch (IOException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "THERE WUZ AN ERROR!! OOPS!!");
						}
					}
				}
			}

		});
		panel.add(save);

		load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Homie, PREPARE YOUR FACE.\n I am going to massage your eyes with a file chooser.\nTell it which list you wanna load.");
				JFileChooser j = new JFileChooser();
				j.showOpenDialog(null);

				if (j.getSelectedFile() != null) {
					String path = j.getSelectedFile().getPath();
					list.pathname = path;
					try {
						FileReader fr = new FileReader(new File(path));
						BufferedReader br = new BufferedReader(fr);

						String line = br.readLine();

						while (list.tasks.size() > 0) {
							list.tasks.remove(0);
						}

						while (line != null) {
							list.tasks.add(line);
							line = br.readLine();
						}

						br.close();
						list.loaded = true;
					} catch (IOException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "THERE WUZ AN ERROR!! OOPS!!");
					}
				}
			}

		});
		panel.add(load);

		frame.pack();
		frame.setVisible(true);
	}

}
