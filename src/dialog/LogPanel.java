package dialog;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import command.CmdManager;

public class LogPanel extends JPanel implements Observer{
	private JTextPane textPane;
	
	public LogPanel() {
		textPane = new JTextPane();
		textPane.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textPane);
		scrollPane.setPreferredSize(new Dimension(200, 450));
		this.add(scrollPane);
			
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		CmdManager commandManager = (CmdManager) arg0;
		String log = ""; //string koji ce se ispisati u textPane
		for(int i = 0 ; i< commandManager.numberOfCommands(); i++) {
			if(i == commandManager.getIndex()) { //ako je i-ta komanda na tekucem indeksu
				log+="--->>";
			}
			log += commandManager.getCommand(i).getCommand();
			log += "\n";		
		}
		textPane.setText(log);
	}

}
