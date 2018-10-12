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
import geometry.Line;
import geometry.Point;

public class DlgModificationLine extends JDialog {
	private final JPanel pnlCentral = new JPanel();
	private JTextField txtX1;
	private JTextField txtY1;
	private JTextField txtX2;
	private JTextField txtY2;
	private JTextField txtColor;
	public int x1;
	public int y1;
	public int x2;
	public int y2;
	public Color color;
	public Color color1;
	public boolean stop = true;
	public boolean confirm=false;
	private JFrame mainFrame;
	private Model model;


	/**
	 * Create the dialog.
	 */
	public DlgModificationLine(Model model) {
		
		this.model = model;
		setTitle("Line modification");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		pnlCentral.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlCentral, BorderLayout.CENTER);
		pnlCentral.setLayout(new GridLayout(10, 0, 0, 0));
		{
			JLabel lblX = new JLabel("X coordinate of the first point:");
			lblX.setHorizontalAlignment(SwingConstants.CENTER);
			pnlCentral.add(lblX);
		}
		{
			txtX1 = new JTextField();
			txtX1.setHorizontalAlignment(SwingConstants.CENTER);
			pnlCentral.add(txtX1);
			txtX1.setColumns(10);
		}
		{
			JLabel lblY = new JLabel("Y coordinate of the first point:");
			lblY.setHorizontalAlignment(SwingConstants.CENTER);
			pnlCentral.add(lblY);
		}
		{
			txtY1 = new JTextField();
			txtY1.setHorizontalAlignment(SwingConstants.CENTER);
			pnlCentral.add(txtY1);
			txtY1.setColumns(10);
		}
		{
			JLabel lblX2 = new JLabel("X coordinate of the second point:");
			lblX2.setHorizontalAlignment(SwingConstants.CENTER);
			pnlCentral.add(lblX2);
		}
		{
			txtX2 = new JTextField();
			txtX2.setHorizontalAlignment(SwingConstants.CENTER);
			pnlCentral.add(txtX2);
			txtX2.setColumns(10);
		}
		{
			JLabel lblY_1 = new JLabel("Y coordinate of the second point:");
			lblY_1.setHorizontalAlignment(SwingConstants.CENTER);
			pnlCentral.add(lblY_1);
		}
		{
			txtY2 = new JTextField();
			txtY2.setHorizontalAlignment(SwingConstants.CENTER);
			pnlCentral.add(txtY2);
			txtY2.setColumns(10);
		}
		{
			JLabel lblColor = new JLabel("Color:");
			lblColor.setHorizontalAlignment(SwingConstants.CENTER);
			pnlCentral.add(lblColor);
		}
		{
			txtColor = new JTextField();
			txtColor.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					color1 = JColorChooser.showDialog(mainFrame, "Choose color", Color.black);
					if (color1 != null)
						txtColor.setBackground(color1);
				}
			});
			txtColor.setEditable(false);
			pnlCentral.add(txtColor);
			txtColor.setColumns(10);
		}
		{
			JPanel pnlButtons = new JPanel();
			getContentPane().add(pnlButtons, BorderLayout.SOUTH);
			{
				JButton btnConfirm = new JButton("Confirm");
				btnConfirm.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						color = txtColor.getBackground();
						x1 = Integer.parseInt(txtX1.getText());
						y1 = Integer.parseInt(txtY1.getText());
						x2 = Integer.parseInt(txtX2.getText());
						y2 = Integer.parseInt(txtY2.getText());
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
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowActivated(WindowEvent e) {
					if (stop){
						txtColor.setBackground(color);
						txtX1.setText(Integer.toString(x1));
						txtY1.setText(Integer.toString(y1));
						txtX2.setText(Integer.toString(x2));
						txtY2.setText(Integer.toString(y2));
						stop = false;
					}
				}
			});
		}
	}
	public Line getData() {
		return new Line(new Point(x1,y1), new Point(x2,y2), color);
	}
}
