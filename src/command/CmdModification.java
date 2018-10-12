package command;

import application.View;
import geometry.Shape;
import application.Drawing;
import application.Model;

public class CmdModification implements Command{

	
	private Shape oldShape;
	private Shape newShape;
	private Model model;

	public CmdModification(Shape oldShape, Shape newShape, Model model) {
		super();
		this.oldShape =oldShape;
		this.newShape = newShape;
		this.model = model;
	}
	
	@Override
	public void execute() {
		
		model.removeSelectedShapes();
		this.oldShape.setSelected(false);
		this.newShape.setSelected(false);
		int index = model.getIndexOf(oldShape);
		System.out.println("Index" + index);
		model.removeShape(oldShape);
		model.add(index, newShape);
		model.addSelectedShape(newShape);
		newShape.setSelected(true);
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.removeSelectedShapes();
		this.oldShape.setSelected(false);
		this.newShape.setSelected(false);
		int index = model.getIndexOf(newShape);
		model.removeShape(newShape);
		model.add(index, oldShape);
		model.addSelectedShape(oldShape);
		oldShape.setSelected(true);
	}

	@Override
	public String getCommand() {
		// TODO Auto-generated method stub
		String description = "Modification->"+ oldShape.toString()+" to "+newShape.toString() ;
		return description;
	}

}
