package dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

public class DlgRectangle extends JDialog {
	private final JPanel pnlCentral = new JPanel();
	private JTextField txtWidth;
	private JTextField txtHeight;
	private boolean empty=true;



	/**
	 * Create the dialog.
	 */
	public DlgRectangle() {
		setTitle("Rectangle");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		pnlCentral.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlCentral, BorderLayout.CENTER);
		GridBagLayout gbl_pnlCentral = new GridBagLayout();
		gbl_pnlCentral.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_pnlCentral.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_pnlCentral.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_pnlCentral.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		pnlCentral.setLayout(gbl_pnlCentral);
		{
			JLabel lblWidth = new JLabel("Rectangle width: ");
			GridBagConstraints gbc_lblWidth = new GridBagConstraints();
			gbc_lblWidth.gridwidth = 6;
			gbc_lblWidth.insets = new Insets(0, 0, 5, 5);
			gbc_lblWidth.gridx = 0;
			gbc_lblWidth.gridy = 4;
			pnlCentral.add(lblWidth, gbc_lblWidth);
		}
		{
			txtWidth = new JTextField();
			GridBagConstraints gbc_txtWidth = new GridBagConstraints();
			gbc_txtWidth.gridwidth = 2;
			gbc_txtWidth.insets = new Insets(0, 0, 5, 0);
			gbc_txtWidth.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtWidth.gridx = 6;
			gbc_txtWidth.gridy = 4;
			pnlCentral.add(txtWidth, gbc_txtWidth);
			txtWidth.setColumns(10);
		}
		{
			JLabel lblHeight = new JLabel("Rectangle height: ");
			GridBagConstraints gbc_lblHeight = new GridBagConstraints();
			gbc_lblHeight.gridwidth = 6;
			gbc_lblHeight.insets = new Insets(0, 0, 0, 5);
			gbc_lblHeight.gridx = 0;
			gbc_lblHeight.gridy = 5;
			pnlCentral.add(lblHeight, gbc_lblHeight);
		}
		{
			txtHeight = new JTextField();
			GridBagConstraints gbc_txtHeight = new GridBagConstraints();
			gbc_txtHeight.gridwidth = 2;
			gbc_txtHeight.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtHeight.gridx = 6;
			gbc_txtHeight.gridy = 5;
			pnlCentral.add(txtHeight, gbc_txtHeight);
			txtHeight.setColumns(10);
		}
		{
			JPanel pnlButtons = new JPanel();
			pnlButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(pnlButtons, BorderLayout.SOUTH);
			{
				JButton pnlConfirm = new JButton("Confirm");
				pnlConfirm.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!txtWidth.getText().isEmpty()){
							try{
								Integer.parseInt(txtWidth.getText());
								Integer.parseInt(txtHeight.getText());
								if(Integer.parseInt(txtWidth.getText()) > 0 && Integer.parseInt(txtHeight.getText()) >0){
									click=true;
									empty = false;
									setVisible(false);
								} else 
									JOptionPane.showMessageDialog(null, "Width and height  must be greater than 0!", "Warning", JOptionPane.ERROR_MESSAGE);  
							} catch (NumberFormatException ex ){
								JOptionPane.showMessageDialog(null, "You must enter the number!", "Warning", JOptionPane.ERROR_MESSAGE);
							}
						
						} else {
							JOptionPane.showMessageDialog(null, "Popuni sva polja!!", "Warning", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				pnlConfirm.setActionCommand("OK");
				pnlButtons.add(pnlConfirm);
				getRootPane().setDefaultButton(pnlConfirm);
			}
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						click=false;
						setVisible(click);
					}
				});
				btnCancel.setActionCommand("Cancel");
				pnlButtons.add(btnCancel);
			}
		}
	}
	public int getlblWidth() {
		
		return Integer.parseInt(txtWidth.getText());
	}
	
	
	public int getLblHeight() {
		return Integer.parseInt(txtHeight.getText());
	}
	private boolean click;
	

	public boolean isClick() {
		return click;
	}

	public void setClick (boolean click) {
		this.click = click;
	}

	public boolean isEmpty() {
		return empty;
	}
	
}
