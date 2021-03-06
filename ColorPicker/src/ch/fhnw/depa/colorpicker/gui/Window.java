package ch.fhnw.depa.colorpicker.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JTextField;

public class Window extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	//Attributes
	private int red = 0;
	private int green = 0;
	private int blue = 0;
	
	private Color usedColor = new Color(red, green, blue);
	
	private Color cred;
	private Color cgreen;
	private Color cblue;
	private Color cyellow;
	private Color ccyan;
	private Color corange;
	private Color cblack;
	
	//Layouts
	GridLayout mainLayout;
	BoxLayout scrLayout;
	GridLayout fieldsLayout;
	BoxLayout radioLayout;
	BorderLayout buttonsLayout;
	BoxLayout brightnessLayout;
	
	//Scrollbars
	JScrollBar scrRed;
	JScrollBar scrGreen;
	JScrollBar scrBlue;
	
	//Fields
	JTextField rgbRed;
	JTextField rgbGreen;
	JTextField rgbBlue;
	JTextField hexRed;
	JTextField hexGreen;
	JTextField hexBlue;
	
	//Buttons
	JButton darker;
	JButton brighter;
	
	//Panels
	JPanel scrollBar;
	JPanel fields;
	JPanel color;
	JPanel buttons;
	JPanel radio;
	JPanel brightness;
	
	//Radiobuttons
	JRadioButton radRed;
	JRadioButton radBlue;
	JRadioButton radGreen;
	JRadioButton radYellow;
	JRadioButton radCyan;
	JRadioButton radOrange;
	JRadioButton radBlack;
	
	//Buttongroupe
	ButtonGroup radioGroup;
	
	//Menu
	JMenuBar menuBar;
	JMenu file;
	JMenu attributes;
	
	//MenuItem
	JMenuItem exit;
	JMenuItem myellow;
	JMenuItem morange;
	JMenuItem mcyan;
	JMenuItem mblack;
	
	public Window(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Colorpicker");
		
		//set Layout
		mainLayout = new GridLayout(2,2);
		setLayout(mainLayout);
		
		//setup menubar
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		//setup menu
		file = new JMenu("File");
		attributes = new JMenu("Attributes");
		menuBar.add(file);
		menuBar.add(attributes);
		
		exit = new JMenuItem("Exit");
		myellow = new JMenuItem("Yellow");
		morange = new JMenuItem("Orange");
		mcyan = new JMenuItem("Cyan");
		mblack = new JMenuItem("Black");
		
		file.add(exit);
		attributes.add(myellow);
		attributes.add(morange);
		attributes.add(mcyan);
		attributes.add(mblack);
		
		//setup sliders
		scrollBar = new JPanel();
		scrLayout = new BoxLayout(scrollBar, BoxLayout.Y_AXIS);
		scrollBar.setLayout(scrLayout);
		
		scrRed = new JScrollBar(JScrollBar.HORIZONTAL, red, 1, 0, 256);
		scrGreen = new JScrollBar(JScrollBar.HORIZONTAL, green, 1, 0, 256);
		scrBlue = new JScrollBar(JScrollBar.HORIZONTAL, blue, 1, 0, 256);
		
		scrRed.setBackground(new Color(255, 0, 0));
		scrGreen.setBackground(new Color(0, 255, 0));
		scrBlue.setBackground(new Color(0, 0, 255));
		
		scrollBar.add(scrRed);
		scrollBar.add(scrGreen);
		scrollBar.add(scrBlue);
		
		add(scrollBar);
		
		//setup Fields
		fields = new JPanel();
		fieldsLayout = new GridLayout(3, 2);
		fields.setLayout(fieldsLayout);
		
		rgbRed = new JTextField(red);
		rgbGreen = new JTextField(green);
		rgbBlue = new JTextField(blue);
		
		hexRed = new JTextField();
		hexGreen = new JTextField();
		hexBlue = new JTextField();
		
		hexRed.setEditable(false);
		hexGreen.setEditable(false);
		hexBlue.setEditable(false);
		
		fields.add(rgbRed);
		fields.add(hexRed);
		fields.add(rgbGreen);
		fields.add(hexGreen);
		fields.add(rgbBlue);
		fields.add(hexBlue);
		
		add(fields, BorderLayout.EAST);
		updateFields();
		
		//setup colorpanel
		color = new JPanel();
		color.setPreferredSize(new Dimension(150, 150));
		updatePanel();
		
		add(color);
		
		//setup buttons
		buttons = new JPanel();
		radio = new JPanel();
		brightness = new JPanel();
		
		//radio buttons
		radioLayout = new BoxLayout(radio, BoxLayout.Y_AXIS);
		radio.setLayout(radioLayout);
		buttonsLayout = new BorderLayout();
		buttons.setLayout(buttonsLayout);
		brightnessLayout = new BoxLayout(brightness, BoxLayout.Y_AXIS);
		brightness.setLayout(brightnessLayout);
		
		radRed = new JRadioButton("red");
		radBlue = new JRadioButton("blue");
		radGreen = new JRadioButton("green");
		radYellow = new JRadioButton("yellow");
		radCyan = new JRadioButton("cyan");
		radOrange = new JRadioButton("orange");
		radBlack = new JRadioButton("black");
		
		radioGroup = new ButtonGroup();
		radioGroup.add(radRed);
		radioGroup.add(radBlue);
		radioGroup.add(radGreen);
		radioGroup.add(radYellow);
		radioGroup.add(radCyan);
		radioGroup.add(radOrange);
		radioGroup.add(radBlack);
		
		radio.add(radRed);
		radio.add(radBlue);
		radio.add(radGreen);
		radio.add(radYellow);
		radio.add(radCyan);
		radio.add(radOrange);
		radio.add(radBlack);
		
		buttons.add(radio, BorderLayout.WEST);
		
		//brightness buttons
		darker = new JButton("Darker");
		brighter = new JButton("Brighter");
		
		brightness.add(darker);
		brightness.add(brighter);
		
		buttons.add(brightness,  BorderLayout.EAST);
		
		add(buttons);
		
		//colors
		cred = new Color(255, 0, 0);
		cgreen = new Color(0, 255, 0);
		cblue = new Color(0, 0, 255);
		cyellow = new Color(255, 255, 0);
		ccyan = new Color(0, 255, 255);
		corange = new Color(255, 165, 0);
		cblack = new Color(0, 0, 0);
		
		//ActionListener
		radRed.addActionListener(this);
		radGreen.addActionListener(this);
		radBlue.addActionListener(this);
		radYellow.addActionListener(this);
		radCyan.addActionListener(this);
		radOrange.addActionListener(this);
		radBlack.addActionListener(this);
		
		scrRed.addAdjustmentListener(new AdjustmentListener(){
			public void adjustmentValueChanged(AdjustmentEvent e) {
				JScrollBar event = (JScrollBar) e.getSource();
				updateRed(event.getValue());
				updatePanel();
				updateFields();
			}
		});
		scrGreen.addAdjustmentListener(new AdjustmentListener(){
			public void adjustmentValueChanged(AdjustmentEvent e) {
				JScrollBar event = (JScrollBar) e.getSource();
				updateGreen(event.getValue());
				updatePanel();
				updateFields();
			}
		});
		scrBlue.addAdjustmentListener(new AdjustmentListener(){
			public void adjustmentValueChanged(AdjustmentEvent e) {
				JScrollBar event = (JScrollBar) e.getSource();
				updateBlue(event.getValue());
				updatePanel();
				updateFields();
			}
		});
		
		rgbRed.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField field = (JTextField) e.getSource();
					try {
					int value = Integer.parseInt(field.getText());
					if(value < 0) {
						value = 0;
						field.setText(""+value);
					}
					else if(value > 255) {
						value = 255;
						field.setText(""+value);
					}
					updateRed(value);
					updateSlider();
					updateFields();
					updatePanel();
				} catch(Exception exp){
					new ErrorMessage();
				}
			}
			
		});
		rgbGreen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField field = (JTextField) e.getSource();
				try{
					int value = Integer.parseInt(field.getText());
					if(value < 0) {
						value = 0;
						field.setText(""+value);
					}
					else if(value > 255) {
						value = 255;
						field.setText(""+value);
					}
					updateGreen(value);
					updateSlider();
					updateFields();
					updatePanel();
				} catch(Exception exp){
					new ErrorMessage();
				}
			}
			
		});
		rgbBlue.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField field = (JTextField) e.getSource();
				try {
					int value = Integer.parseInt(field.getText());
					if(value < 0) {
						value = 0;
						field.setText(""+value);
					}
					else if(value > 255) {
						value = 255;
						field.setText(""+value);
					}
					updateBlue(value);
					updateSlider();
					updateFields();
					updatePanel();
				} catch(Exception exp){
					new ErrorMessage();
				}
			}
			
		});
		
		brighter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(red < 255) red++;
				if(green < 255) green++;
				if(blue < 255) blue++;
				updateColor();
				updatePanel();
				updateSlider();
				updateFields();
			}
			
		});
		darker.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(red > 0) red--;
				if(green > 0) green--;
				if(blue > 0) blue--;
				updateColor();
				updatePanel();
				updateSlider();
				updateFields();
			}
			
		});
		
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		myellow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateColor(cyellow);
				updatePanel();
				updateSlider();
				updateFields();
			}
		});
		morange.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateColor(corange);
				updatePanel();
				updateSlider();
				updateFields();
			}
		});
		mcyan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateColor(ccyan);
				updatePanel();
				updateSlider();
				updateFields();
			}
		});
		mblack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateColor(cblack);
				updatePanel();
				updateSlider();
				updateFields();
			}
		});
		
		//set visible
		pack();
		setVisible(true);
	}
	
	private void updateRed(int _red){
		updateColor(_red, green, blue);
	}
	private void updateGreen(int _green){
		updateColor(red, _green, blue);
	}
	private void updateBlue(int _blue){
		updateColor(red, green, _blue);
	}
	private void updateColor(int _red, int _green, int _blue){
		red = _red;
		green = _green;
		blue = _blue;
		usedColor = new Color(red, green, blue);
		checkRadio();
	}
	private void updateColor(Color c){
		usedColor = c;
		red = c.getRed();
		green = c.getGreen();
		blue = c.getBlue();
		checkRadio();
	}
	private void updateColor(){
		usedColor = new Color(red, green, blue);
	}
	private void updateSlider(){
		scrRed.setValue(red);
		scrBlue.setValue(blue);
		scrGreen.setValue(green);
	}
	private void updateFields(){
		rgbRed.setText(""+red);
		rgbGreen.setText(""+green);
		rgbBlue.setText(""+blue);
		
		hexRed.setText("" + Integer.toHexString(red));
		hexGreen.setText("" + Integer.toHexString(green));
		hexBlue.setText("" + Integer.toHexString(blue));
	}
	private void checkRadio(){
		//TODO chose right radio buttons
		usedColor = new Color(red, green, blue);
		if(checkColor(cred)){
			unselectAll();
			radRed.setSelected(true);
		} else if(checkColor(cgreen)){
			unselectAll();
			radGreen.setSelected(true);
		} else if(checkColor(cblue)){
			unselectAll();
			radBlue.setSelected(true);
		} else if(checkColor(cyellow)){
			unselectAll();
			radYellow.setSelected(true);
		} else if(checkColor(ccyan)){
			unselectAll();
			radCyan.setSelected(true);
		} else if(checkColor(corange)){
			unselectAll();
			radOrange.setSelected(true);
		} else if(checkColor(cblack)){
			unselectAll();
			radBlack.setSelected(true);
		} else {
			unselectAll();
		}
	}
	private void updatePanel(){
		color.setBackground(usedColor);
	}
	private boolean checkColor(Color c){
		return usedColor.equals(c);
	}
	private void unselectAll(){
		radRed.setSelected(false);
		radGreen.setSelected(false);
		radBlue.setSelected(false);
		radYellow.setSelected(false);
		radCyan.setSelected(false);
		radOrange.setSelected(false);
		radBlack.setSelected(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object event = e.getSource();
		if(event instanceof JRadioButton){
			JRadioButton button = (JRadioButton) event;
			String name = button.getText();
			switch(name){
			case "red":
				updateColor(cred);
				updatePanel();
				updateSlider();
				updateFields();
				break;
			case "green":
				updateColor(cgreen);
				updatePanel();
				updateSlider();
				updateFields();
				break;
			case "blue":
				updateColor(cblue);
				updatePanel();
				updateSlider();
				updateFields();
				break;
			case "yellow":
				updateColor(cyellow);
				updatePanel();
				updateSlider();
				updateFields();
				break;
			case "cyan":
				updateColor(ccyan);
				updatePanel();
				updateSlider();
				updateFields();
				break;
			case "orange":
				updateColor(corange);
				updatePanel();
				updateSlider();
				updateFields();
				break;
			case "black":
				updateColor(cblack);
				updatePanel();
				updateSlider();
				updateFields();
				break;
			}
		}
	}
}
