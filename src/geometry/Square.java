package geometry;

import java.awt.Color;
import java.awt.Graphics;

import application.Model;

public class Square extends SurfaceShape implements Moveable{
	protected Point upperLeft;
	protected int edgeLength;


	public Square(){

	}
	public Square(Point upperLeft, int edgeLength){
		this.upperLeft = upperLeft;
		this.edgeLength = edgeLength;
	}
	public Square(Point upperLeft, int edgeLength, Color edgeColor, Color innerColor){
		this(upperLeft,edgeLength);
		setColor(edgeColor);
		setInsideColor(innerColor);
	}
	public void moveTo(int x, int y){
		upperLeft.setX(x);
		upperLeft.setY(y);
	}

	public void moveFor(int x, int y){
		upperLeft.setX(upperLeft.getX()+x);
		upperLeft.setY(upperLeft.getY()+y);
	}

	public int scope(){
		return 4 * edgeLength;
	}
	public int surface(){
		return edgeLength * edgeLength;
	}

	//Tacka gore levo=(xGoreLevo,yGoreLevo), duzina stranice=duzinaStranice
	public String toString(){
		return "Square:("+upperLeft.getX()+","+upperLeft.getY()+"),side="+edgeLength + ",colorLine=" + getColor()+ ",colorArea=" + getInsideColor();
	}
	public boolean equals(Object obj){
		if(obj instanceof Square){
			Square helper = (Square) obj;
			if(upperLeft.equals(helper.upperLeft) && edgeLength == helper.edgeLength)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	public Line diagonal(){
		return new Line(upperLeft, new Point(upperLeft.getX() + edgeLength,upperLeft.getY() + edgeLength));
	}

	public Point centar(){
		return diagonal().lineMiddle();
	}
	public boolean contains(int x, int y) {
		if(this.getUpperLeft().getX()<=x 
				&& x<=(this.getUpperLeft().getX() + edgeLength)
				&& this.getUpperLeft().getY()<=y 
				&& y<=(this.getUpperLeft().getY() + edgeLength))
			return true;
		else 
			return false;

	}
	public void selected(Graphics g) {
		g.setColor(findColor("plava"));
		new Line(getUpperLeft(), new Point(getUpperLeft().getX()+edgeLength, getUpperLeft().getY())).selected(g);
		new Line(getUpperLeft(), new Point(getUpperLeft().getX(), getUpperLeft().getY()+edgeLength)).selected(g);
		new Line(new Point(getUpperLeft().getX()+edgeLength, getUpperLeft().getY()), diagonal().getEndPoint()).selected(g);
		new Line(new Point(getUpperLeft().getX(), getUpperLeft().getY()+edgeLength), diagonal().getEndPoint()).selected(g);

	}
	public void drawShape(Graphics g){
		g.setColor(getColor());
		g.drawRect(upperLeft.getX(), upperLeft.getY(), edgeLength, edgeLength);
		fill(g);
		if(isSelected())
			selected(g);
	}
	public void fill(Graphics g){
		g.setColor(getInsideColor());
		g.fillRect(upperLeft.getX()+1, upperLeft.getY()+1, edgeLength-1, edgeLength-1);	
	}
	public int compareTo(Object o) {
		if(o instanceof Square){
			Square helper = (Square) o;
			return (int) (this.surface() - helper.surface());
		}
		else
			return 0;
	}
	public Point getUpperLeft() {
		return upperLeft;
	}
	public void setUpperLeft(Point upperLeft) {
		this.upperLeft = upperLeft;
	}
	public int getEdgeLength() {
		return edgeLength;
	}
	public void setEdgeLength(int edgeLength) {
		this.edgeLength = edgeLength;
	}
	
}
