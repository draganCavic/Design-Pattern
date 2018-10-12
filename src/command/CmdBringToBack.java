package command;

import java.util.List;

import application.Model;
import geometry.Shape;

public class CmdBringToBack implements Command{

	
	private Shape shape;
	private Model model;
	List<Shape> selectedShapes;
	int i=0;
	
	public CmdBringToBack(Shape shape, Model model) {
		this.shape = shape;
		this.model = model;
		this.selectedShapes =model.getSelectedShapes();
	}
	@Override
	public void execute() {
		for(int j = model.getShapes().size()-1;j>=0;j--){
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
				}
			}
			model.notifyDraw();
		}
	}

	@Override
	public String getCommand() {
		// TODO Auto-generated method stub
		String description = "BBack->"+ shape.toString();
		return description ;
	}

}
