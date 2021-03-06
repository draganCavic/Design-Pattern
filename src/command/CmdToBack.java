package command;

import java.util.List;

import application.Model;
import geometry.Shape;

public class CmdToBack implements Command{

	private Shape shape;
	private Model model;
	List<Shape> shapes;
	
	public CmdToBack(Shape s, Model model) {
		// TODO Auto-generated constructor stub
		this.model = model;
		this.shapes =model.getSelectedShapes();
		this.shape = s;
	}
	
	@Override
	public void execute() {
		for(int j = 0;j<=model.getShapes().size()-1;j++){
			if(model.getShapes().get(j).isSelected()) {
				if(j==0) {
					return;
				}else {
					Shape s = model.getShapes().get(j-1);
					model.getShapes().set(j-1, model.getShapes().get(j));
					model.getShapes().set(j, s);
				}
			}
		}
	}
	@Override
	public void unexecute() {
		for(int j = 0;j<=model.getShapes().size()-1;j++){
			if(model.getShapes().get(j).isSelected()) {
				if(j==model.getShapes().size()-1 ) {
					
					return;
				}else {
					Shape s = model.getShapes().get(j+1);
					model.getShapes().set(j+1, model.getShapes().get(j));
					model.getShapes().set(j, s);
					return;
				}
			}

			
		}
	}

	@Override
	public String getCommand() {
		// TODO Auto-generated method stub
		String description = "Back->"+ shape.toString();
		return description ;
	}

}
