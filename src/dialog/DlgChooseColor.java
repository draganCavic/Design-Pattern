package dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



public class DlgChooseColor extends JDialog {
	private final JPanel contentPanel = new JPanel();
	public Color colorEdge;
	public Color colorArea;
	private JFrame mainFrame;
	public boolean potvrda;
	private JButton btnColorArea;
	private JButton btnColorEdge;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			DlgChooseColor dialog = new DlgChooseColor();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public DlgChooseColor() {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			{
				JPanel pnlCentral = new JPanel();
				contentPanel.add(pnlCentral);
				pnlCentral.setLayout(new GridLayout(0, 3, 0, 0));
				{
					JLabel label = new JLabel("");
					pnlCentral.add(label);
				}
				{
					JLabel label = new JLabel("");
					pnlCentral.add(label);
				}
				{
					JLabel label = new JLabel("");
					pnlCentral.add(label);
				}
				{
					JLabel label = new JLabel("");
					pnlCentral.add(label);
				}
				btnColorEdge = new JButton("Color edge");
				pnlCentral.add(btnColorEdge);
				btnColorEdge.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Color color = JColorChooser.showDialog(mainFrame, "Choose color edge", Color.black);
						if (color != null){
							colorEdge = color;
							btnColorEdge.setBackground(color);
						}
					}
				});
				{
					JLabel label = new JLabel("");
					pnlCentral.add(label);
				}
				{
					JLabel label = new JLabel("");
					pnlCentral.add(label);
				}
				{
					JLabel label = new JLabel("");
					pnlCentral.add(label);
				}
				{
					JLabel label = new JLabel("");
					pnlCentral.add(label);
				}
				{
					JLabel label = new JLabel("");
					pnlCentral.add(label);
				}
				btnColorArea = new JButton("Color area");
				pnlCentral.add(btnColorArea);
				btnColorArea.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Color color = JColorChooser.showDialog(mainFrame, "Choose color area", Color.white);
						if (color != null){
							colorArea = color;
							btnColorArea.setBackground(color);
						}
					}
				});
				{
					JLabel label = new JLabel("");
					pnlCentral.add(label);
				}
				{
					JLabel label = new JLabel("");
					pnlCentral.add(label);
				}
				{
					JLabel label = new JLabel("");
					pnlCentral.add(label);
				}
				{
					JLabel label = new JLabel("");
					pnlCentral.add(label);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JButton btnConfirm = new JButton("Confirm");
				btnConfirm.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						potvrda = true;
						setVisible(false);
					}
				});
				btnConfirm.setActionCommand("OK");
				buttonPane.add(btnConfirm);
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
				buttonPane.add(btnCancel);
			}
		}
	}

	public Color getColorArea() {
		return btnColorArea.getBackground();
	}
	public Color getColorEdge() {
		return btnColorEdge.getBackground();
	}
	
}
