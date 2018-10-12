package dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import application.Model;
import geometry.Circle;
import geometry.HexagonAdapter;
import geometry.Point;
import hexagon.Hexagon;

public class DlgModificationCircle extends JDialog {
	private final JPanel pnlCentral = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtRadius;
	private JTextField txtColorEdge;
	private JTextField txtColorArea;
	public int x;
	public int y;
	public int radius;
	public Color colorEdge;
	public Color colorArea;
	public Color colorEdge1;
	public Color colorArea1;
	public boolean confirm = false;
	private JFrame mainFrame;
	public boolean stop = true;
	//private Model model;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public DlgModificationCircle() {
		setModal(true);
		setTitle("Modification Circle");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		pnlCentral.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlCentral, BorderLayout.CENTER);
		pnlCentral.setLayout(new GridLayout(10, 0, 0, 0));
		{
			JLabel lblX = new JLabel("X coordinate:");
			lblX.setHorizontalAlignment(SwingConstants.CENTER);
			pnlCentral.add(lblX);
		}
		{
			txtX = new JTextField();
			txtX.setHorizontalAlignment(SwingConstants.CENTER);
			pnlCentral.add(txtX);
			txtX.setColumns(10);
		}
		{
			JLabel lblY = new JLabel("Y coordinate:");
			lblY.setHorizontalAlignment(SwingConstants.CENTER);
			pnlCentral.add(lblY);
		}
		{
			txtY = new JTextField();
			txtY.setHorizontalAlignment(SwingConstants.CENTER);
			pnlCentral.add(txtY);
			txtY.setColumns(10);
		}
		{
			JLabel lblRadius = new JLabel("Radius:");
			lblRadius.setHorizontalAlignment(SwingConstants.CENTER);
			pnlCentral.add(lblRadius);
		}
		{
			txtRadius = new JTextField();
			txtRadius.setHorizontalAlignment(SwingConstants.CENTER);
			pnlCentral.add(txtRadius);
			txtRadius.setColumns(10);
		}
		{
			JLabel lblColorEdge = new JLabel("Color edge:");
			lblColorEdge.setHorizontalAlignment(SwingConstants.CENTER);
			pnlCentral.add(lblColorEdge);
		}
		{
			txtColorEdge = new JTextField();
			txtColorEdge.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					colorEdge1 = JColorChooser.showDialog(mainFrame, "Choose color", Color.black);
					if (colorEdge1 != null)
						txtColorEdge.setBackground(colorEdge1);
				}
			});
			txtColorEdge.setEditable(false);
			txtColorEdge.setHorizontalAlignment(SwingConstants.CENTER);
			pnlCentral.add(txtColorEdge);
			txtColorEdge.setColumns(10);
		}
		{
			JLabel lblColorArea = new JLabel("Color area:");
			lblColorArea.setHorizontalAlignment(SwingConstants.CENTER);
			pnlCentral.add(lblColorArea);
		}
		{
			txtColorArea = new JTextField();
			txtColorArea.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					colorArea1 = JColorChooser.showDialog(mainFrame, "Choose color", Color.white);
					if (colorArea1 != null)
						txtColorArea.setBackground(colorArea1);
				}
			});
			txtColorArea.setEditable(false);
			txtColorArea.setHorizontalAlignment(SwingConstants.CENTER);
			pnlCentral.add(txtColorArea);
			txtColorArea.setColumns(10);
		}
		{
			JPanel pnlButtons = new JPanel();
			getContentPane().add(pnlButtons, BorderLayout.SOUTH);
			{
				JButton btnConfirm = new JButton("Confirm");
				btnConfirm.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						colorArea = txtColorArea.getBackground();
									colorEdge = txtColorEdge.getBackground();
									x = Integer.parseInt(txtX.getText());
									y = Integer.parseInt(txtY.getText());
									radius = Integer.parseInt(txtRadius.getText());
									confirm = true;
									setVisible(false);
					}
				});
				pnlButtons.setLayout(new GridLayout(0, 2, 0, 0));
				btnConfirm.setActionCommand("OK");
				pnlButtons.add(btnConfirm);
				getRootPane().setDefaultButton(btnConfirm);
			}
			
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				btnCancel.setActionCommand("Cancel");
				pnlButtons.add(btnCancel);
			}
		}
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				if (stop){
					txtColorEdge.setBackground(colorEdge);
					txtColorArea.setBackground(colorArea);
								txtX.setText(Integer.toString(x));
								txtY.setText(Integer.toString(y));
								txtRadius.setText(Integer.toString(radius));
								stop = false;
				}
			}
		});
	}
	public Circle getDataC() {
		return new Circle(new Point(x,y), radius, colorEdge, colorArea);
	}
}
