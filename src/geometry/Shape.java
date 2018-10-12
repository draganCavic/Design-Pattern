package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Serializable {
	
	private boolean selected;
	private Color color = Color.BLACK;
	
	public Shape(){
	}
	
	public abstract void drawShape(Graphics g);
	public abstract void selected(Graphics g);
	public abstract boolean contains(int x, int y);
	
	public Color findColor(String boja){
		if(boja.equalsIgnoreCase("bela"))
			return Color.WHITE;
		else if(boja.equalsIgnoreCase("plava"))
			return Color.BLUE;
		else if(boja.equalsIgnoreCase("crvena"))
			return Color.RED;
		else if(boja.equalsIgnoreCase("zelena"))
			return Color.GREEN;
		else if(boja.equalsIgnoreCase("zuta"))
			return Color.YELLOW;
		else if(boja.equalsIgnoreCase("pink"))
			return Color.PINK;
		else
			return Color.BLACK;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
}
