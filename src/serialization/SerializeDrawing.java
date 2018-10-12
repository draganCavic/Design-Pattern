package serialization;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.JOptionPane;

import application.Model;
import command.CmdManager;
import geometry.Shape;

public class SerializeDrawing implements SerializeStrategy{

	private Model model;
	private CmdManager manager;
	public  SerializeDrawing(Model model, CmdManager manager) {
		this.model = model;
		this.manager = manager;
	}
	@Override
	public void save(String path) {

		try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(path))) ){
			
			for(Shape shape : model.getShapes()) {
				oos.writeObject(shape); 
			}
		}
		catch (Exception e) {
		
			JOptionPane.showMessageDialog(null, "Error saving file: "+ path, "Save error", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	@Override
	public void load(String path) {
		
		try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(path))) ){
			model.getShapes().clear();
			model.getSelectedShapes().clear();
			manager.clearAllCommands();
			Shape shape;
			try {
				while( (shape=(Shape) ois.readObject()) != null) { 
					model.addShape(shape);
				}
			}
			catch (Exception e) {
				
				return;
			}
		}
		catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Error by reading file: "+ path, "Error loading file", JOptionPane.ERROR_MESSAGE);
		}
	}
}
