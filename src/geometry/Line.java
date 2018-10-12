package geometry;

import java.awt.Color;
import java.awt.Graphics;

import application.Model;

public class Line extends Shape {
	private Point startPoint;
	private Point endPoint;
	
	

	public Line(){

	}
	
	public Line(Point startPoint, Point endPoint){
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	public Line(Point startPoint, Point endPoint, Color color){
		this(startPoint,endPoint);
		setColor(color);
	}
	public void moveFor(int x, int y){
		this.startPoint.setX(startPoint.getX()+x);
		startPoint.setY(startPoint.getY()+y);
		endPoint.setX(endPoint.getX()+x);
		endPoint.setY(endPoint.getY()+y);
	}

	public double length(){
		return startPoint.distance(endPoint);
	}
	public String toString(){
		return "Line:("+startPoint.getX()+"," +startPoint.getY()+"),(" + endPoint.getX()+","+ endPoint.getY() + "),color="+ getColor();
	}	
	public boolean equals(Object obj){
		if(obj instanceof Line){
			Line helper = (Line) obj;
			if(startPoint.equals(helper.startPoint) && endPoint.equals(helper.endPoint))
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	public Point lineMiddle(){
		int middleX = (startPoint.getX() + endPoint.getX()) / 2;
		int middleY = (startPoint.getY() + endPoint.getY()) / 2;
		return new Point(middleX, middleY);
	}
	public boolean contains(int x, int y){
		Point clickPoint = new Point(x, y);
		if(clickPoint.distance(startPoint)+clickPoint.distance(endPoint)-this.length()<0.5)
			return true;
		else 
			return false;
	}
	public void selected(Graphics g){
		g.setColor(Color.BLUE);
		startPoint.selected(g);
		endPoint.selected(g);
		lineMiddle().selected(g);
	}
	public int compareTo(Object o) {
		if(o instanceof Line){
			Line helper = (Line) o;
			return (int) (this.length() - helper.length());
		}
		else
			return 0;
	}
	public Point getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	public Point getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
	@Override
	public void drawShape(Graphics g) {
		g.setColor(getColor());
		g.drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
		if(isSelected())
			selected(g);
	}
	
}
