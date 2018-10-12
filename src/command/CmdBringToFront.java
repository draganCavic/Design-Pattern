package command;

import java.util.List;

import application.Model;
import geometry.Shape;

public class CmdBringToFront implements Command{
	private Shape shape;
	private Model model;
	
	public CmdBringToFront(Shape s, Model model) {
		this.shape = s;
		this.model = model;
	}

	@Override
	public void execute() {
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
	public void unexecute() {
		// TODO Auto-generated method stub
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
			model.notifyDraw();
		
		}
	}

	@Override
	public String getCommand() {
		// TODO Auto-generated method stub
		String description = "B-Front->"+ shape.toString();
		return description ;
	}
}
