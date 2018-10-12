package geometry;

import java.awt.Color;
import java.awt.Graphics;

import application.Model;

public class Point extends Shape implements Moveable{
	private int x;
	private int y;
	private long id;
	
	public Point(){

	}
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	public Point(int x, int y, Color boja, Model model){
		this(x, y);
		setColor(boja);
	}
	public void moveTo(int novoX, int novoY){
		setX(novoX);
		setY(novoY);
	}
	public void moveFor(int novoX, int novoY){
		setX(getX()+novoX);
		setY(getY()+novoY);
	}
	public double distance(Point t2){
		double dx = x - t2.getX();
		double dy = y - t2.y;
		double result = Math.sqrt(dx*dx + dy*dy);

		return result;
	}
	public String toString(){
		return "Point:(" + x+ ","+ getY()+ "),color="+getColor();
	}
	public String toStringPoint() {
		return "(" + x+ ","+ getY()+ ")";
	}
	public boolean equals(Object obj){
		if(obj instanceof Point){
			Point helper = (Point) obj;
			if(x == helper.x && y == helper.y)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	@Override
	public void drawShape(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(getColor());
		g.drawLine(x-2, y, x+2, y);
		g.drawLine(x, y-2, x, y+2);
		if(isSelected())
			selected(g);
	}
	@Override
	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(x-3, y-3, 6, 6);
		
	}
	@Override
	public boolean contains(int x, int y) {
		Point clickPoint = new Point(x, y);
		if(clickPoint.distance(this)<=2)
			return true;
		else
			return false;
	}
	public int compareTo(Object o) {
		if(o instanceof Point){
			Point helper = (Point) o;
			Point zeroPoint = new Point(0, 0);
			return (int) (this.distance(zeroPoint) - helper.distance(zeroPoint));
		}
		else 
			return 0;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
}
