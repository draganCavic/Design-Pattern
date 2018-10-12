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
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class DlgDelete extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private int n=0;
	JButton btnYes;
	JButton btnNo;
	


	/**
	 * Create the dialog.
	 */
	public DlgDelete() {
		setModal(true);
		setBounds(100, 100, 385, 170);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{41, 107, 33, 122, 0};
		gbl_contentPanel.rowHeights = new int[]{14, 33, 23, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			 btnNo = new JButton("No");
			btnNo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					n=2;
					setVisible(false);
				}
			});
			{
				JLabel lblMessage = new JLabel("Are you sure?");
				GridBagConstraints gbc_lblMessage = new GridBagConstraints();
				gbc_lblMessage.anchor = GridBagConstraints.NORTHWEST;
				gbc_lblMessage.insets = new Insets(0, 0, 5, 0);
				gbc_lblMessage.gridwidth = 3;
				gbc_lblMessage.gridx = 1;
				gbc_lblMessage.gridy = 0;
				contentPanel.add(lblMessage, gbc_lblMessage);
			}
			{
				btnYes = new JButton("Yes");
				btnYes.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					
						n=1;
						setVisible(false);
						
					}
				});
				
				GridBagConstraints gbc_btnYes = new GridBagConstraints();
				gbc_btnYes.anchor = GridBagConstraints.NORTHWEST;
				gbc_btnYes.insets = new Insets(0, 0, 0, 5);
				gbc_btnYes.gridx = 1;
				gbc_btnYes.gridy = 2;
				contentPanel.add(btnYes, gbc_btnYes);
			}
			GridBagConstraints gbc_btnNo = new GridBagConstraints();
			gbc_btnNo.anchor = GridBagConstraints.NORTHWEST;
			gbc_btnNo.gridx = 3;
			gbc_btnNo.gridy = 2;
			contentPanel.add(btnNo, gbc_btnNo);
		}
		{
			JPanel btnButtons = new JPanel();
			btnButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(btnButtons, BorderLayout.SOUTH);
		}
	}

	
	
	public JButton getBtnYes() {
		return btnYes;
	}
	public void setBtnYes(JButton btnYes) {
		this.btnYes = btnYes;
	}
	public JButton getBtnNo() {
		return btnNo;
	}
	public void setBtnNo(JButton btnNo) {
		this.btnNo = btnNo;
	}
	public int getN() {
		return n;
	}
	
	
}
