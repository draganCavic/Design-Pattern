package geometry;

import java.awt.Color;
import java.awt.Graphics;

import application.Model;

public class Circle extends SurfaceShape implements Moveable{
	private Point center;
	private int r;
	
	public Circle(){

	}
	public Circle(Point center, int r){
		this.center = center;
		this.r = r;
	}
	public Circle(Point centar, int r,Color edgeColor, Color innerColor){
		this(centar, r);
		setColor(edgeColor);
		setInsideColor(innerColor);
	}
	
	public void moveTo(int x, int y){
		center.moveTo(x, y);
	}
	public void moveFor(int x, int y){
		center.moveFor(x, y);
	}
	public double surface(){
		return r * r * Math.PI;
	}
	public double scope(){
		return 2 * r * Math.PI;
	}
	public String toString(){
		return "Circle:" + center.toStringPoint() + ",radius=" + r + ",colorLine="+getColor()+",colorArea="+getInsideColor();
	}
	public boolean equals(Object obj){
		if(obj instanceof Circle){
			Circle helper = (Circle) obj;
			if(center.equals(helper.center) && r == helper.r)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	public boolean contains(int x, int y){
		Point clickPoint = new Point(x, y);
		if(clickPoint.distance(center)<=r)
			return true;
		else
			return false;
		
	}
	public void selected(Graphics g) {
		new Line(new Point(center.getX(), center.getY()-r), new Point(center.getX(), center.getY() + r)).selected(g);
		new Line(new Point(center.getX()-r, center.getY()), new Point(center.getX()+r, center.getY())).selected(g);
	}
	public void drawShape(Graphics g){
		g.setColor(getColor());
		g.drawOval(center.getX()-r, center.getY()-r, 2*r, r+r);
		fill(g);
		if(isSelected())
			selected(g);
	}
	
	public void fill(Graphics g) {
		g.setColor(getInsideColor());
		g.fillOval(center.getX()-r+1, center.getY()-r+1, 2*r-2, r+r-2);
		
	}
	public int compareTo(Object o) {
		if(o instanceof Circle){
			Circle helper = (Circle) o;
			return (int) (this.r - helper.r);
		}
		else
			return 0;
	}
	public Point getCenter() {
		return center;
	}
	public void setCenter(Point center) {
		this.center = center;
	}
	public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
	}
	
}
