package dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class DlgCircle extends JDialog {
	private final JPanel pnlCentral = new JPanel();
	private boolean empty=true;



	/**
	 * Create the dialog.
	 */
	public DlgCircle() {
		setTitle("Circle");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		pnlCentral.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlCentral, BorderLayout.CENTER);
		pnlCentral.setLayout(new BorderLayout(0, 0));
		{
			JPanel pnlMain = new JPanel();
			pnlMain.setBackground(new Color(230, 230, 250));
			pnlCentral.add(pnlMain);
			{
				lblRadius = new JLabel("Radius: ");
				lblRadius.setForeground(Color.BLACK);
				pnlMain.add(lblRadius);
			}
			{
				txtRadius = new JTextField();
				txtRadius.setBackground(new Color(255, 250, 250));
				pnlMain.add(txtRadius);
				txtRadius.setColumns(10);
			}
		}
		{
			JPanel pnlButtons = new JPanel();
			pnlButtons.setBackground(new Color(230, 230, 250));
			getContentPane().add(pnlButtons, BorderLayout.SOUTH);
			{
				JButton btnConfirm = new JButton("Confirm");
				btnConfirm.setForeground(Color.BLACK);
				btnConfirm.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(!txtRadius.getText().isEmpty()){
							
							try{
								Integer.parseInt(txtRadius.getText());
								if(Integer.parseInt(txtRadius.getText()) > 0){
									click=true;
									empty=false;
									setVisible(false);
								}
								else 
									JOptionPane.showMessageDialog(null, "Radius must be greater than 0!", "Warning", JOptionPane.ERROR_MESSAGE);
								} 
								
							catch (NumberFormatException ex ){
								JOptionPane.showMessageDialog(null, "You must enter the number!", "Warning", JOptionPane.ERROR_MESSAGE);
							}		
							
					}else {
								JOptionPane.showMessageDialog(null, "Vrednost ne sme biti prazna", "Warning", JOptionPane.ERROR_MESSAGE);
							}
						}
				}
					);
				pnlButtons.setLayout(new GridLayout(0, 2, 0, 0));
				btnConfirm.setActionCommand("OK");
				pnlButtons.add(btnConfirm);
				getRootPane().setDefaultButton(btnConfirm);
			}
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.setForeground(Color.BLACK);
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
	
	private JLabel lblRadius;
	
	
	private boolean click;
	private JTextField txtRadius;
	public int getTxtRadius() {
		return Integer.parseInt(txtRadius.getText());
	}

	public boolean isClick() {
		return click;
	}

	public void setClick(boolean click) {
		this.click = click;
	}

	public boolean isEmpty() {
		return empty;
	}
	
}
