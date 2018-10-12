package dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class DlgSquare extends JDialog {
	private final JPanel pnlCentral = new JPanel();
	private JTextField txtLengthSide;
	private JLabel lblLengthSide;
	private boolean empty=true;
	
	
	/**
	 * Create the dialog.
	 */
	public DlgSquare() {
		setTitle("Square");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		pnlCentral.setBackground(new Color(230, 230, 250));
		pnlCentral.setToolTipText("");
		pnlCentral.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlCentral, BorderLayout.CENTER);
		GridBagLayout gbl_pnlCentralni = new GridBagLayout();
		gbl_pnlCentralni.columnWidths = new int[]{98, 91, 0};
		gbl_pnlCentralni.rowHeights = new int[]{20, 0, 0, 0, 0, 0};
		gbl_pnlCentralni.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_pnlCentralni.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlCentral.setLayout(gbl_pnlCentralni);
		{
			lblLengthSide = new JLabel("Side length:");
			lblLengthSide.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			lblLengthSide.setForeground(Color.BLACK);
			GridBagConstraints gbc_lblLengthSide = new GridBagConstraints();
			gbc_lblLengthSide.insets = new Insets(0, 0, 5, 5);
			gbc_lblLengthSide.gridx = 0;
			gbc_lblLengthSide.gridy = 2;
			pnlCentral.add(lblLengthSide, gbc_lblLengthSide);
		}
		{
			txtLengthSide = new JTextField();
			txtLengthSide.setBackground(new Color(255, 250, 250));
			GridBagConstraints gbc_txtLengthSide = new GridBagConstraints();
			gbc_txtLengthSide.insets = new Insets(0, 0, 5, 0);
			gbc_txtLengthSide.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtLengthSide.gridx = 1;
			gbc_txtLengthSide.gridy = 2;
			pnlCentral.add(txtLengthSide, gbc_txtLengthSide);
			txtLengthSide.setColumns(10);
		}
		{
			JPanel pnlButtons = new JPanel();
			pnlButtons.setBackground(new Color(230, 230, 250));
			getContentPane().add(pnlButtons, BorderLayout.SOUTH);
			{
				JButton pnlConfirm = new JButton("Confirm");
				pnlConfirm.setForeground(Color.BLACK);
				pnlConfirm.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					if(!txtLengthSide.getText().isEmpty()){
						try{
							Integer.parseInt(txtLengthSide.getText());
							if(Integer.parseInt(txtLengthSide.getText()) > 0){
								empty = false;
								setVisible(false);
							} else 
								JOptionPane.showMessageDialog(null, "Side length must be greater than 0!", "Warning", JOptionPane.ERROR_MESSAGE); 

						} catch (NumberFormatException ex ){
							JOptionPane.showMessageDialog(null, "You must enter the number!", "Warning", JOptionPane.ERROR_MESSAGE);
						}
					
					} else {
						JOptionPane.showMessageDialog(null, "Duzina stranice ne sme biti prazna", "Warning", JOptionPane.ERROR_MESSAGE);
					}
					}
				});
				pnlButtons.setLayout(new GridLayout(0, 2, 0, 0));
				pnlConfirm.setActionCommand("OK");
				pnlButtons.add(pnlConfirm);
				getRootPane().setDefaultButton(pnlConfirm);
			}
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.setForeground(Color.BLACK);
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						setVisible(false);
						
						
					}
				});
				btnCancel.setActionCommand("Cancel");
				pnlButtons.add(btnCancel);
			}
		}
	}
	

	public int getlblUnesiteDuzinuStranice() {
		
		return Integer.parseInt(txtLengthSide.getText());
	}

	public boolean isEmpty() {
		return empty;
	}
	

}
