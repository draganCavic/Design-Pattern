package geometry;

import java.awt.Color;
import java.awt.Graphics;

import application.Model;
import hexagon.Hexagon;

public class HexagonAdapter  extends SurfaceShape {
	
	private Hexagon hexagon;

	public HexagonAdapter(Hexagon hexagon) {
		super();
		this.hexagon = hexagon;
	}
	@Override
	public void fill(Graphics g) {
		g.setColor(hexagon.getAreaColor());
		int y1 = getY()- getR();
		int y4 = getY() + getR();
		int x6 = getX() - getR();
		int x3 = getX() + getR();
		int x1  =getX() - getR()/2;
		int x2 =  getX() - getR()/2;
		int[] xPoints = {x1,x2,x3,x2,x1,x6}; 
		int[] yPooints = {y1,y1,getY(),y4,y4,getY()}; 
		g.fillPolygon(xPoints,yPooints , 6);
	}

	@Override
	public void drawShape(Graphics g) {
		hexagon.paint(g);
		if(isSelected())
			selected(g);
	}
	public boolean equals(Object obj){
		if(obj instanceof HexagonAdapter){
			HexagonAdapter pomocna = (HexagonAdapter) obj;
			Point t = new Point(hexagon.getX(),hexagon.getY());
			Point t1 = new Point(pomocna.hexagon.getX(),pomocna.hexagon.getY());
			if(t.equals(t1) && hexagon.getR()==pomocna.getHexagon().getR())
				return true;
			else
				return false;

		}
		else
			return false;
	}
	@Override
	public void selected(Graphics g) {
		new Line(new Point(getX(), getY()-getR()+getR()/7), new Point(getX(), getY() + getR()-getR()/7)).selected(g);
		new Line(new Point(getX()-getR(), getY()), new Point(getX()+getR(), getY())).selected(g);
	}

	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}
	
	public int getX() {
		return hexagon.getX();
	}
	public int getY() {
		return hexagon.getY();
	}
	public int getR() {
		return hexagon.getR();
	}
	
	@Override
	public Color getColor() {
		return hexagon.getBorderColor();
	}
	
	@Override
	public Color getInsideColor() {
		return hexagon.getAreaColor();
	}
	
	@Override
	public String toString() {
		return "Hexagon:("+hexagon.getX()+","+hexagon.getY()+"),radius="+hexagon.getR()+",colorLine="+hexagon.getBorderColor()+",colorArea="+hexagon.getAreaColor();
	}
	public Hexagon getHexagon() {
		return hexagon;
	}

	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}
}
