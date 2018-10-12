package application;


import javax.swing.UIManager;

import command.CmdAdd;
import command.CmdManager;
import command.CmdModification;


public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
			Model model = new Model();
			CmdManager manager = new CmdManager();
			Drawing frame = new Drawing(model, manager);
			frame.getView().setModel(model);
			Controller controller = new Controller(frame, model, manager);
			frame.setController(controller);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
