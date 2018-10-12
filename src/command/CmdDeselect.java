package command;

import java.awt.Graphics;
import java.util.Observable;

import application.Drawing;
import application.Model;
import application.View;
import geometry.Shape;

public class CmdDeselect implements Command{

	private Shape shape;
	private Model model;
	
	public CmdDeselect(Shape shape, Model model) {
		this.shape = shape;
		this.model = model;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		model.getShape(model.getShapes().indexOf(shape)).setSelected(false);
		model.removeSelectedShape(shape);
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.addSelectedShape(shape);
		for(Shape s:model.getShapes()) {
			if(shape.equals(s)) {
				s.setSelected(true);
			}
		}
	}

	@Override
	public String getCommand() {
		// TODO Auto-generated method stub
		String description = "Deselect->"+ shape.toString();
		return description ;
	}

}
