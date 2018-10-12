package command;

import application.Model;
import geometry.Shape;

public class CmdAdd implements Command {

	private Shape shape;
	private Model model;
	
	public CmdAdd(Shape shape, Model model) {
		super();
		this.shape = shape;
		this.model =model;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		model.addShape(shape);
		
	}

	@Override
	public void unexecute() {
		int i = model.getIndexOf(shape);
		model.removeShape(shape);
	}

	@Override
	public String getCommand() {
		// TODO Auto-generated method stub
		String shape = this.shape.toString();
		return "Add->"+shape ;
	}


	
}
