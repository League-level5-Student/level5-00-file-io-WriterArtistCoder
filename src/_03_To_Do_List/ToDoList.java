package _03_To_Do_List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ToDoList {
	/*
	 * Create a program with five buttons, add task, view tasks, remove task, save list, and load list. 
	 * 
	 * When add task is clicked:
	 * 		ask the user for a  task and save it to an array list
	 * 
	 * When the view tasks button is clicked:
	 *		show all the tasks in the list
	 * 
	 * When the remove task button is clicked:
	 * 		prompt the user for which task to remove and take it off of the list.
	 * 
	 * When the save list button is clicked:
	 * 		Save the list to a file
	 * 
	 * When the load list button is clicked:
	 * 		Prompt the user for the location of the file and load the list from that file
	 * 
	 * When the program starts, it should automatically load the last saved file into the list.
	 */
	
	ArrayList<String> tasks;
	
	public ToDoList() {
		tasks = new ArrayList<>();
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
				list.tasks.add(JOptionPane.showInputDialog("Dude, I am thinking you should type a task in here.\nTHAT SOUNDS GOOD TO ME!!!!\nJust pwn this pop-up dialog...\nWith lots and lots of text."));
			}
			
		});
		panel.add(add);

		view.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFrame viewList = new JFrame("Your tasks");
				viewList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				JLabel viewTasks = new JLabel();
				for (int i = 0; i < list.tasks.size(); i++) {
					String s = list.tasks.get(i);
					String x = viewTasks.getText();
					viewTasks.setText(x+i+". "+s+"\n");
				}
				
				viewList.pack();
				viewList.setVisible(true);
			}
			
		});
		panel.add(view);
		
		frame.pack();
		frame.setVisible(true);
	}
	
}
