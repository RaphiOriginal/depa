package ch.fhnw.depa.colorpicker.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ErrorMessage extends JFrame{
	JLabel label;
	String message = "Enter a number between 0 and 255!";
	public ErrorMessage(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Error!");
		label = new JLabel(message);
		add(label);
		
		pack();
		setVisible(true);
	}
}
