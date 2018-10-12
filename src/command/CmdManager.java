package command;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;



public class CmdManager extends Observable{
	private List<Command> commands;
	private int index; //indeks je postavljen na poslednje izvrsenu komandu
	//private static CmdManager instance = null;
	
	public CmdManager() {
		setIndex(-1);
		commands = new ArrayList<>();
	}
	
//	public static CmdManager getInstance() {
//		if(instance == null) {
//			instance = new CmdManager();
//		}
//		return instance;
//	}
	
	public void addCommand(Command command) {
		if(index != commands.size()-1 ) {//ako indeks ne pokazuje na poslednju komandu tj ako je odradjen undo
			for(int i = commands.size()-1; i>index; i--) {
				commands.remove(i);
			}
		}
		commands.add(command);
		setIndex(getIndex() + 1);
		setChanged();
		notifyObservers(index); //da bi bili omoguceni undo i redo
	}
	
	public void removeCommand(Command command) {
		commands.remove(command); //brisanje komande
		setIndex(getIndex() - 1);
		setChanged();
		notifyObservers(index);
	}
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
		setChanged();
		notifyObservers(index);
	}
	
	public Command getCommand(int index) {
		return commands.get(index);
	}
	
	public int numberOfCommands() {
		return commands.size();
	}
	
	//zbog ucitavanja novog crteza moramo izbrisati sve prethodne komande
	public void clearAllCommands() { 
		commands.clear();
		index = -1;
		setChanged();
		notifyObservers(index); //da undo i redo budu invisible
	}
	public List<Command> getCommands(){
		return commands;
	}
}
