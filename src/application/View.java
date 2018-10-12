package application;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import geometry.Shape;

public class View extends JPanel implements Observer {
	private Model model;
	public View()
	{
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for (Shape o : model.getShapes())
		{
			o.drawShape(g);
		}
		repaint();
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		this.paintComponent(this.getGraphics());
	}
	public void setModel(Model model) {
		this.model=model;
	}
	public Model getModel() {
		return model;
	}

}
