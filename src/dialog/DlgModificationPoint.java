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
import geometry.Point;

public class DlgModificationPoint extends JDialog {
	private final JPanel pnlCentral = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtColor;
	public int x;
	public int y;
	public Color color;
	public Color color1;
	private JFrame mainFrame;
	public boolean stop = true;
	public boolean confirm=false;
	private Model model;


	/**
	 * Create the dialog.
	 */
	public DlgModificationPoint( Model model) {
		this.model = model;
		
		setModal(true);
		setTitle("Point modification");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		pnlCentral.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlCentral, BorderLayout.CENTER);
		pnlCentral.setLayout(new GridLayout(8, 3, 0, 0));
		{
			JLabel lblX = new JLabel("X coordinate:");
			lblX.setForeground(Color.BLACK);
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
			JLabel lblColor = new JLabel("Color:");
			lblColor.setHorizontalAlignment(SwingConstants.CENTER);
			pnlCentral.add(lblColor);
		}
		{
			txtColor = new JTextField();
			txtColor.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					color1 = JColorChooser.showDialog(mainFrame, "Choose color:", Color.black);
					if (color1 != null)
						txtColor.setBackground(color1);
				}
			});
			txtColor.setEditable(false);
			txtColor.setHorizontalAlignment(SwingConstants.CENTER);
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
						x = Integer.parseInt(txtX.getText());
						y = Integer.parseInt(txtY.getText());
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
					public void actionPerformed(ActionEvent arg0) {
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
					txtColor.setBackground(color);
					txtX.setText(Integer.toString(x));
					txtY.setText(Integer.toString(y));
					stop = false;
				}
			}
		});
	}
	public Point getData() {
		return new Point(x,y,color, model);
	}

	public JTextField getTxtColor() {
		return txtColor;
	}

	public void setTxtColor(JTextField txtColor) {
		this.txtColor = txtColor;
	}
	
}
