/*
 * Copyright (c) 2000-2016 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */
package jdraw.std;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;

import jdraw.figures.BorderDecorator;
import jdraw.figures.BundleDecorator;
import jdraw.figures.Group;
import jdraw.figures.LineTool;
import jdraw.figures.OvalTool;
import jdraw.figures.RectTool;
import jdraw.framework.DrawCommandHandler;
import jdraw.framework.DrawModel;
import jdraw.framework.DrawTool;
import jdraw.framework.DrawToolFactory;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.grid.ObjectGrid;
import jdraw.grid.SimpleGrid;
import jdraw.grid.StepGrid;

/**
 * Standard implementation of interface DrawContext.
 * 
 * @see DrawView
 * @author Dominik Gruntz & Christoph Denzler
 * @version 2.6, 24.09.09
 */
public class StdContext extends AbstractContext {
	
	private SimpleClipboard clipboard = new SimpleClipboard();

	/**
	 * Constructs a standard context with a default set of drawing tools.
	 * @param view the view that is displaying the actual drawing.
	 */
  public StdContext(DrawView view) {
		super(view, null);
	}
	
  /**
   * Constructs a standard context. The drawing tools available can be parameterized using <code>toolFactories</code>.
   * @param view the view that is displaying the actual drawing.
   * @param toolFactories a list of DrawToolFactories that are available to the user
   */
	public StdContext(DrawView view, List<DrawToolFactory> toolFactories) {
		super(view, toolFactories);
	}

	/**
	 * Creates and initializes the "Edit" menu.
	 * 
	 * @return the new "Edit" menu.
	 */
	@Override
	protected JMenu createEditMenu() {
		JMenu editMenu = new JMenu("Edit");
		final JMenuItem undo = new JMenuItem("Undo");
		undo.setAccelerator(KeyStroke.getKeyStroke("control Z"));
		editMenu.add(undo);
		undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final DrawCommandHandler h = getModel().getDrawCommandHandler();
				if (h.undoPossible()) {
					h.undo();
				}
			}
		});

		final JMenuItem redo = new JMenuItem("Redo");
		redo.setAccelerator(KeyStroke.getKeyStroke("control Y"));
		editMenu.add(redo);
		redo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final DrawCommandHandler h = getModel().getDrawCommandHandler();
				if (h.redoPossible()) {
					h.redo();
				}
			}
		});
		editMenu.addSeparator();

		JMenuItem sa = new JMenuItem("SelectAll");
		sa.setAccelerator(KeyStroke.getKeyStroke("control A"));
		editMenu.add(sa);
		sa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Figure f : getModel().getFigures()) {
					getView().addToSelection(f);
				}
				getView().repaint();
			}
		});

		editMenu.addSeparator();
		JMenuItem cut = new JMenuItem("Cut");
		editMenu.add(cut);
		cut.addActionListener(e -> {
			clipboard.clear();
			for(Figure f: getView().getSelection()) {
				clipboard.add(f.clone());
				
				getModel().removeFigure(f);
			}
		});
		
		JMenuItem copy = new JMenuItem("Copy");
		editMenu.add(copy);
		copy.addActionListener(e -> {
			clipboard.clear();
			for(Figure f: getView().getSelection()) {
				clipboard.add(f.clone());
			}
		});
		
		JMenuItem paste = new JMenuItem("Paste");
		editMenu.add(paste);
		paste.addActionListener(e -> {
			getView().clearSelection();
			for(Figure f:clipboard.getFigures()) {
				Figure c = f.clone();
				getModel().addFigure(c);
				getView().addToSelection(c);
			}
		});

		editMenu.addSeparator();
		JMenuItem group = new JMenuItem("Group");
		group.addActionListener(e -> {
			List<Figure> selection = getView().getSelection();
			if(selection != null && selection.size() >= 2){
				Group g = new Group(selection, getModel());
				DrawModel m = getView().getModel();
				for(Figure f: getView().getSelection()){
					m.removeFigure(f);
				}
				m.addFigure(g);
				getView().addToSelection(g);
				DrawCommandHandler cmd = getModel().getDrawCommandHandler();
				cmd.addCommand(new GroupFigureCommand(m, g, true));
			}
		});
		editMenu.add(group);

		JMenuItem ungroup = new JMenuItem("Ungroup");
		ungroup.addActionListener(e -> {
			for(Figure g: getView().getSelection()){
				if(g instanceof Group){
					getModel().removeFigure(g);
					for(Figure f: ((Group)g).getParts()){
						getModel().addFigure(f);
						getView().addToSelection(f);
					}
					DrawCommandHandler cmd = getModel().getDrawCommandHandler();
					cmd.addCommand(new GroupFigureCommand(getView().getModel(), (Group) g, false));
				}
			}
		});
		editMenu.add(ungroup);

		editMenu.addSeparator();

		JMenu orderMenu = new JMenu("Order...");
		JMenuItem frontItem = new JMenuItem("Bring To Front");
		frontItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bringToFront(getView().getModel(), getView().getSelection());
				getView().repaint();
			}
		});
		orderMenu.add(frontItem);
		JMenuItem backItem = new JMenuItem("Send To Back");
		backItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendToBack(getView().getModel(), getView().getSelection());
				getView().repaint();
			}
		});
		orderMenu.add(backItem);
		editMenu.add(orderMenu);

		JMenu grid = new JMenu("Grid...");
		
		JMenuItem nullGrid = new JMenuItem("No Grid");
		nullGrid.addActionListener(e -> getView().setConstrainer(null));
		grid.add(nullGrid);
		
		JMenuItem simpleGrid = new JMenuItem("Simple Grid");
		simpleGrid.addActionListener(e -> getView().setConstrainer(new SimpleGrid()));
		grid.add(simpleGrid);
		
		JMenuItem stepGrid10 = new JMenuItem("Grid 10");
		stepGrid10.addActionListener(e -> getView().setConstrainer(new StepGrid(10)));
		grid.add(stepGrid10);
		
		JMenuItem stepGrid20 = new JMenuItem("Grid 20");
		stepGrid20.addActionListener(e -> getView().setConstrainer(new StepGrid(30)));
		grid.add(stepGrid20);
		
		JMenuItem stepGrid30 = new JMenuItem("Grid 30");
		stepGrid30.addActionListener(e -> getView().setConstrainer(new StepGrid(30)));
		grid.add(stepGrid30);
		
		JMenuItem ObjectGrid = new JMenuItem("ObjectGrid");
		ObjectGrid.addActionListener(e -> getView().setConstrainer(new ObjectGrid(super.getView())));
		grid.add(ObjectGrid);
		
		editMenu.add(grid);
		
		editMenu.addSeparator();
		
		JMenuItem borderDecorator = new JMenuItem("Border");
		borderDecorator.addActionListener(e -> {
			List<Figure> s = getView().getSelection();
			getView().clearSelection();
			for(Figure f: s) {
				BorderDecorator bd = new BorderDecorator(f);
				getModel().removeFigure(f);
				getModel().addFigure(bd);
				getView().addToSelection(bd);
			}
		});
		
		editMenu.add(borderDecorator);
		
		JMenuItem bundleDecorator = new JMenuItem("Bundle");
		bundleDecorator.addActionListener(e -> {
			List<Figure> s = getView().getSelection();
			getView().clearSelection();
			for(Figure f: s) {
				BundleDecorator bd = new BundleDecorator(f);
				getModel().removeFigure(f);
				getModel().addFigure(bd);
				getView().addToSelection(bd);
			}
		});
		
		editMenu.add(bundleDecorator);
		
		return editMenu;
	}

	/**
	 * Creates and initializes items in the file menu.
	 * 
	 * @return the new "File" menu.
	 */
	@Override
	protected JMenu createFileMenu() {
	  JMenu fileMenu = new JMenu("File");
		JMenuItem open = new JMenuItem("Open");
		fileMenu.add(open);
		open.setAccelerator(KeyStroke.getKeyStroke("control O"));
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doOpen();
			}
		});

		JMenuItem save = new JMenuItem("Save");
		save.setAccelerator(KeyStroke.getKeyStroke("control S"));
		fileMenu.add(save);
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doSave();
			}
		});

		JMenuItem exit = new JMenuItem("Exit");
		fileMenu.add(exit);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		return fileMenu;
	}
	

	@Override
	protected void doRegisterDrawTools() {
		// TODO Add new figure tools here
		
		for(DrawToolFactory dt: getToolFactories()){
			addTool(dt == null ? null : dt.createTool(this));
		}
		/*DrawTool rectangleTool = new RectTool(this);
		addTool(rectangleTool);
		DrawTool ovalTool = new OvalTool(this);
		addTool(ovalTool);
		DrawTool lineTool = new LineTool(this);
		addTool(lineTool);*/
	}

	/**
	 * Changes the order of figures and moves the figures in the selection
	 * to the front, i.e. moves them to the end of the list of figures.
	 * @param model model in which the order has to be changed
	 * @param selection selection which is moved to front
	 */
	public void bringToFront(DrawModel model, List<Figure> selection) {
		// the figures in the selection are ordered according to the order in
		// the model
		List<Figure> orderedSelection = new LinkedList<Figure>();
		int pos = 0;
		for (Figure f : model.getFigures()) {
			pos++;
			if (selection.contains(f)) {
				orderedSelection.add(0, f);
			}
		}
		for (Figure f : orderedSelection) {
			model.setFigureIndex(f, --pos);
		}
	}

	/**
	 * Changes the order of figures and moves the figures in the selection
	 * to the back, i.e. moves them to the front of the list of figures.
	 * @param model model in which the order has to be changed
	 * @param selection selection which is moved to the back
	 */
	public void sendToBack(DrawModel model, List<Figure> selection) {
		// the figures in the selection are ordered according to the order in
		// the model
		List<Figure> orderedSelection = new LinkedList<Figure>();
		for (Figure f : model.getFigures()) {
			if (selection.contains(f)) {
				orderedSelection.add(f);
			}
		}
		int pos = 0;
		for (Figure f : orderedSelection) {
			model.setFigureIndex(f, pos++);
		}
	}

	/**
	 * Handles the saving of a drawing to a file.
	 */
	private void doSave() {
		JFileChooser chooser = new JFileChooser(getClass().getResource("")
				.getFile());
		chooser.setDialogTitle("Save Graphic");
		chooser.setDialogType(JFileChooser.SAVE_DIALOG);
		FileFilter filter = new FileFilter() {
			@Override
			public String getDescription() {
				return "JDraw Graphic (*.draw)";
			}

			@Override
			public boolean accept(File f) {
				return f.getName().endsWith(".draw");
			}
		};
		chooser.setFileFilter(filter);
		int res = chooser.showOpenDialog(this);

		if (res == JFileChooser.APPROVE_OPTION) {
			// save graphic
			File file = chooser.getSelectedFile();
			if (chooser.getFileFilter() == filter && !filter.accept(file)) {
				file = new File(chooser.getCurrentDirectory(), file.getName() + ".draw");
			}
			try {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
				for(Figure f: getModel().getFigures()) {
					oos.writeObject(f.clone());
				}
				oos.writeObject(null);
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("save current graphic to file " + file.getName());
		}
	}

	/**
	 * Handles the opening of a new drawing from a file.
	 */
	private void doOpen() {
		JFileChooser chooser = new JFileChooser(getClass().getResource("")
				.getFile());
		chooser.setDialogTitle("Open Graphic");
		chooser.setDialogType(JFileChooser.OPEN_DIALOG);
		chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
			@Override
			public String getDescription() {
				return "JDraw Graphic (*.draw)";
			}

			@Override
			public boolean accept(File f) {
				return f.isDirectory() || f.getName().endsWith(".draw");
			}
		});
		int res = chooser.showOpenDialog(this);

		if (res == JFileChooser.APPROVE_OPTION) {
			// read jdraw graphic
			try {
				DrawModel m = getModel();
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(chooser.getSelectedFile()));
				while(true) {
					try {
						Object obj = ois.readObject();
						if(obj == null) break;
						Figure f = (Figure) obj;
						m.addFigure(f);
						obj = ois.readObject();
					}  catch (ClassNotFoundException e) {
						System.out.println("Figure not Found!");
					}
				}
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("read file "
					+ chooser.getSelectedFile().getName());
		}
	}

}
