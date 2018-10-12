package geometry;

import java.awt.Color;
import java.awt.Graphics;

import application.Model;

public class Rectangle extends Square{
private int height;
	
	public Rectangle(){

	}
	public Rectangle(Point upperLeft, int width, int height){
		super(upperLeft, width);
		this.height = height;
	}
	public Rectangle(Point upperLeft, int width, int height,Color edgeColor, Color innerColor){
		this(upperLeft,width,height);
		setColor(edgeColor);
		setInsideColor(innerColor);
	}
	
	public String toString(){
		return "Rectangle:"+upperLeft.toStringPoint()+",width="+edgeLength+",height="+height + ",colorLine="+getColor()+",colorArea="+getInsideColor();
	}
	public boolean equals(Object obj){
		if(obj instanceof Rectangle){
			Rectangle helper = (Rectangle) obj;
			if(upperLeft.equals(helper.upperLeft) && edgeLength == helper.edgeLength && height == helper.height)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	public Line diagonal(){
		return new Line(upperLeft, new Point(upperLeft.getX() + edgeLength,upperLeft.getY() + height));
	}
	public Point centar(){
		return diagonal().lineMiddle();
	}
	
	public int scope(){
		return 2 * (edgeLength + height);
	}
	public int surface(){
		return edgeLength * height;
	}
	public boolean contains(int x, int y) {
		if(this.getUpperLeft().getX()<=x 
				&& x<=(this.getUpperLeft().getX() + edgeLength)
				&& this.getUpperLeft().getY()<=y 
				&& y<=(this.getUpperLeft().getY() + height))
			return true;
		else 
			return false;

	}
	public void selected(Graphics g) {
		g.setColor(findColor("blue"));
		new Line(getUpperLeft(), new Point(getUpperLeft().getX()+edgeLength, getUpperLeft().getY())).selected(g);
		new Line(getUpperLeft(), new Point(getUpperLeft().getX(), getUpperLeft().getY()+height)).selected(g);
		new Line(new Point(getUpperLeft().getX()+edgeLength, getUpperLeft().getY()), diagonal().getEndPoint()).selected(g);
		new Line(new Point(getUpperLeft().getX(), getUpperLeft().getY()+height), diagonal().getEndPoint()).selected(g);
	}
	public void drawShape(Graphics g){
		g.setColor(getColor());
		g.drawRect(upperLeft.getX(), upperLeft.getY(), edgeLength, height);
		fill(g);
		if(isSelected())
			selected(g);
	}
	public void fill(Graphics g) {
		g.setColor(getInsideColor());
		g.fillRect(upperLeft.getX()+1, upperLeft.getY()+1, edgeLength-1, height-1);
		
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
}
