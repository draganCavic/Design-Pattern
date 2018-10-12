package command;



import java.awt.Graphics;
import java.util.Observable;

import application.Drawing;
import application.Model;
import application.View;
import geometry.Shape;

public class CmdSelect implements Command {

	private Shape shape;
	private Model model;
	
	public CmdSelect (Shape shape, Model model) {
		super();
		this.shape = shape;
		this.model = model;
	}
	
	@Override
	public void execute() {
		model.addSelectedShape(shape);
		for(Shape s:model.getShapes()) {
			if(shape.equals(s)) {
				s.setSelected(true);
			}
		}
	}

	@Override
	public void unexecute() {
		model.getShape(model.getShapes().indexOf(shape)).setSelected(false);
		model.removeSelectedShape(shape);
	}

	@Override
	public String getCommand() {
		String description = "Select->"+ shape.toString();
		return description ;
	}

}
