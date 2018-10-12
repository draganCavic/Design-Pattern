package application;




import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import command.CmdManager;
import dialog.LogPanel;
import geometry.Shape;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

public class Drawing extends JFrame implements Observer{

	private JPanel pnlCentral;
	private LogPanel pnlLog;
	private JPanel pnlActions;
	private JPanel pnlShapes;
	private View view = new View();
	private Controller c;
	private JButton btnPoint;
	private JButton btnLine;
	private JButton btnSquare;
	private JButton btnRectangle;
	private JButton btnCircle;
	private JButton btnHexagon;
	private JButton btnColor;
	private JButton btnSelect;
	private JButton btnModification;
	private JButton btnDelete;
	private JButton btnToFront;
	private JButton btnToBack;
	private JButton btnBringToFront;
	private JButton btnBringToBack;
	private JPanel pnlButtons;
	private JButton btnUndo;
	private JButton btnRedo;
	private JButton btnSave;
	private JButton btnLoad;
	private int selected;
	private Color colorEdge;
	private Color colorArea;
	private int click = 1;
	private JButton btnSaveLog;
	private JButton btnLoadLog;

	public Drawing(Model model, CmdManager manager) {
		
		manager.addObserver(this);
		model.addObserver(this);
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {

				c.drawing(arg0);

			}
		});
		view.setBackground(Color.white);



		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Dragan Cavic IT56-2015");
		setBounds(100, 100, 1000, 700);

		pnlCentral = new JPanel();
		pnlCentral.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnlCentral.setLayout(new BorderLayout(0, 0));
		setContentPane(pnlCentral);
		pnlCentral.add(view, BorderLayout.CENTER);

		pnlLog = new LogPanel();
		manager.addObserver(pnlLog);
		pnlCentral.add(pnlLog, BorderLayout.EAST);
		pnlLog.setLayout(new BoxLayout(pnlLog, BoxLayout.X_AXIS));


		pnlShapes = new JPanel();
		pnlCentral.add(pnlShapes, BorderLayout.NORTH);
		pnlShapes.setLayout(new GridLayout(1, 0, 0, 0));

		btnPoint = new JButton("Point");
		btnPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.point();
			}
		});
		pnlShapes.add(btnPoint);

		btnLine = new JButton("Line");
		btnLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.line();
			}
		});
		
		pnlShapes.add(btnLine);
		btnSquare = new JButton("Square");
		btnSquare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.square();
			}
		});
		pnlShapes.add(btnSquare);

		btnRectangle = new JButton("Rectangle");
		btnRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.rectangle();
			}
		});
		pnlShapes.add(btnRectangle);

		btnCircle = new JButton("Circle");
		btnCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.circle();
			}
		});
		pnlShapes.add(btnCircle);

		btnHexagon = new JButton("Hexagon");
		btnHexagon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.hexagon();
			}
		});
		pnlShapes.add(btnHexagon);

		btnColor = new JButton("Color");
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.color();
			}
		});
		pnlShapes.add(btnColor);

		pnlActions = new JPanel();
		pnlCentral.add(pnlActions, BorderLayout.SOUTH);
		pnlActions.setLayout(new GridLayout(1, 0, 0, 0));

		btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.select();
			}
		});
		pnlActions.add(btnSelect);

		btnModification = new JButton("Modification");
		btnModification.setEnabled(false);
		btnModification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.modification();
			}
		});
		pnlActions.add(btnModification);

		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.delete();
			}
		});
		pnlActions.add(btnDelete);

		btnToFront = new JButton("To Front");
		btnToFront.setEnabled(false);
		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.toFront();
			}
		});
		pnlActions.add(btnToFront);

		btnToBack = new JButton("To Back");
		btnToBack.setEnabled(false);
		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.toBack();
			}
		});
		pnlActions.add(btnToBack);

		btnBringToFront = new JButton("Bring To Front");
		btnBringToFront.setEnabled(false);
		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.bringToFront();
			}
		});
		pnlActions.add(btnBringToFront);

		btnBringToBack = new JButton("Bring To Back");
		btnBringToBack.setEnabled(false);
		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.bringToBack();
			}
		});
		pnlActions.add(btnBringToBack);

		pnlButtons = new JPanel();
		pnlCentral.add(pnlButtons, BorderLayout.WEST);
		GridBagLayout gbl_pnlButtons = new GridBagLayout();
		gbl_pnlButtons.rowHeights = new int[]{158, 0, 23, 0, 0, 0, 0, 0};
		gbl_pnlButtons.columnWeights = new double[]{0.0};
		gbl_pnlButtons.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlButtons.setLayout(gbl_pnlButtons);

		btnRedo = new JButton("       Redo       ");
		btnRedo.setEnabled(false);
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.redo();
			}
		});

		btnUndo = new JButton("       Undo       ");
		btnUndo.setEnabled(false);
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.undo();
			}
		});
		GridBagConstraints gbc_btnUndo = new GridBagConstraints();
		gbc_btnUndo.anchor = GridBagConstraints.WEST;
		gbc_btnUndo.insets = new Insets(0, 0, 5, 0);
		gbc_btnUndo.gridx = 0;
		gbc_btnUndo.gridy = 1;
		pnlButtons.add(btnUndo, gbc_btnUndo);
		GridBagConstraints gbc_btnRedo = new GridBagConstraints();
		gbc_btnRedo.anchor = GridBagConstraints.WEST;
		gbc_btnRedo.insets = new Insets(0, 0, 5, 0);
		gbc_btnRedo.gridx = 0;
		gbc_btnRedo.gridy = 2;
		pnlButtons.add(btnRedo, gbc_btnRedo);

		btnSave = new JButton("Save Drawing");
		btnSave.setEnabled(false);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.save(1);
			}
		});
		
		btnSaveLog = new JButton("   Save Log    ");
		btnSaveLog.setEnabled(false);
		btnSaveLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.save(0);
			}
		});
		GridBagConstraints gbc_btnSaveLog = new GridBagConstraints();
		gbc_btnSaveLog.anchor = GridBagConstraints.WEST;
		gbc_btnSaveLog.insets = new Insets(0, 0, 5, 0);
		gbc_btnSaveLog.gridx = 0;
		gbc_btnSaveLog.gridy = 3;
		pnlButtons.add(btnSaveLog, gbc_btnSaveLog);
		
		btnLoadLog = new JButton("   Load Log    ");
		btnLoadLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.load(0);
			}
		});
		GridBagConstraints gbc_btnLoadLog = new GridBagConstraints();
		gbc_btnLoadLog.anchor = GridBagConstraints.WEST;
		gbc_btnLoadLog.insets = new Insets(0, 0, 5, 0);
		gbc_btnLoadLog.gridx = 0;
		gbc_btnLoadLog.gridy = 4;
		pnlButtons.add(btnLoadLog, gbc_btnLoadLog);
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.anchor = GridBagConstraints.WEST;
		gbc_btnSave.insets = new Insets(0, 0, 5, 0);
		gbc_btnSave.gridx = 0;
		gbc_btnSave.gridy = 5;
		pnlButtons.add(btnSave, gbc_btnSave);

		btnLoad = new JButton("Load Drawing");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.load(1);
			}
		});
		GridBagConstraints gbc_btnLoad = new GridBagConstraints();
		gbc_btnLoad.anchor = GridBagConstraints.WEST;
		gbc_btnLoad.gridx = 0;
		gbc_btnLoad.gridy = 6;
		pnlButtons.add(btnLoad, gbc_btnLoad);

	}

	@Override
	public void update(Observable o, Object arg) {
		btnSave.setEnabled(true);
		btnSaveLog.setEnabled(true);
		if(o instanceof CmdManager) {
			int index = (int)arg;
			CmdManager manger = (CmdManager)o;
			if( index >= 0 && index != manger.numberOfCommands()-1) {
				btnUndo.setEnabled(true);
				btnRedo.setEnabled(true);

			}else if( index >= 0 && index == manger.numberOfCommands()-1){
				btnUndo.setEnabled(true);
				btnRedo.setEnabled(false);
			}else if( index ==-1 && manger.numberOfCommands() != 0) {
				btnUndo.setEnabled(false);
				btnRedo.setEnabled(true);
			}else {
				btnUndo.setEnabled(false);
				btnRedo.setEnabled(false);
			}
		}
		if(o instanceof Model) {
			Model repository = (Model)o;
			int size = repository.getSelectedShapes().size();
			if(size == 1 ) { //ako je jedan oblik selektovan
				btnModification.setEnabled(true);
				btnDelete.setEnabled(true);

				Shape selectedShape = repository.getSelectedShapes().get(0); //uzimamo tog prvoj (i jedinog) u listi selektovanih
				
				if(repository.getIndexOf(selectedShape) < repository.getShapes().size()-1) { //ako je indeks od selektovanog oblika manji od poslednjeg indeksa
					btnToFront.setEnabled(true);
					btnBringToFront.setEnabled(true);
				}else {

					btnToFront.setEnabled(false);
					btnBringToFront.setEnabled(false);
				}
				if(repository.getIndexOf(selectedShape) > 0 ) { //ako nije na prvom mestu u listi, moze toBack
					btnToBack.setEnabled(true);
					btnBringToBack.setEnabled(true);
				}else {
					btnToBack.setEnabled(false);
					btnBringToBack.setEnabled(false);
					
				}
				
			}else if(size > 1){ //ako je selektovano vise oblika, omogucujemo smo brisanje, bez modifikacije
				btnDelete.setEnabled(true);
				btnModification.setEnabled(false);
				
				btnToFront.setEnabled(false);
				btnToBack.setEnabled(false);
				btnBringToFront.setEnabled(false);
				btnBringToBack.setEnabled(false);
			
			}else { //ako nista nije oznaceno tj ako je argument false i nista nije selektovano
				btnModification.setEnabled(false);
				btnDelete.setEnabled(false);
				

				btnToFront.setEnabled(false);
				btnToBack.setEnabled(false);
				btnBringToFront.setEnabled(false);
				btnBringToBack.setEnabled(false);
			}
		}
	}

	public Color getColorEdge() {
		return colorEdge;
	}

	public void setColorEdge(Color colorEdge) {
		this.colorEdge = colorEdge;
	}

	public Color getColorArea() {
		return colorArea;
	}

	public void setColorArea(Color colorArea) {
		this.colorArea = colorArea;
	}

	public int getClick() {
		return click;
	}

	public void setClick(int click) {
		this.click = click;
	}

	public JButton getBtnPoint() {
		return btnPoint;
	}

	public void setBtnPoint(JButton btnPoint) {
		this.btnPoint = btnPoint;
	}

	public int getSelected() {
		return selected;
	}

	public void setSelected(int selected) {
		this.selected = selected;
	}

	public JButton getBtnUndo() {
		return btnUndo;
	}

	public JButton getBtnRedo() {
		return btnRedo;
	}

	public View getView() {
		return view;
	}
	public void setController(Controller controller) {
		this.c = controller;
	}




}
