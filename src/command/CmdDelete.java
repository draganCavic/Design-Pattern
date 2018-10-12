package command;

import java.util.ArrayList;
import java.util.List;

import application.Model;
import geometry.Shape;

public class CmdDelete implements Command{

	private List<Shape> shapes;
	private Model model;
	
	
	public CmdDelete(List<Shape> shapes, Model model) {
		super();
		this.shapes= new ArrayList<>(shapes);
		this.model = model;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		for(Shape s:shapes) {
			model.removeSelectedShape(s);
		}
		model.removeShapes(shapes);
	}

	@Override
	public void unexecute() {
		for(Shape shape : this.shapes) { 
			model.addShape(shape);
			model.addSelectedShape(shape);
			for(Shape s:model.getShapes()) {
				if(shape.equals(s)) {
					s.setSelected(true);
				}
			}
		}
	}

	@Override
	public String getCommand() {
		// TODO Auto-generated method stub
		String description="";
		for(Shape shape : this.shapes) {
			description+="Delete->"+shape.toString()+"\n";
		}
		description.trim();
		return description.trim();
	}
	
}
