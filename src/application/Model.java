package application;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import geometry.Shape;

public class Model extends Observable {
	
	private List<Shape> shapes;

	private List<Shape> selectedShapes;
	
	
	
	public Model() { 
		this.shapes = new ArrayList<>();
		this.selectedShapes = new ArrayList<>();
	}
	
	
	public List<Shape> getShapes() {
		return shapes;
	}

	public List<Shape> getSelectedShapes() {
		return selectedShapes;
	}
	public void addShape(Shape shape) {
		shapes.add(shape);

		setChanged();
		notifyObservers(shapes.size());
	}
	public void removeShapes(List<Shape> shapes) {
		this.shapes.removeAll(shapes);
		removeSelectedShapes();
	}
	
	public void removeShape(Shape s) {
		this.shapes.remove(s);
		removeSelectedShape(s);
	}
	
	public void addSelectedShape(Shape shape) {
		selectedShapes.add(shape);
		setChanged();
		notifyObservers(selectedShapes.size());
	}
	public void removeSelectedShape(Shape shape) {
		selectedShapes.remove(shape);
		shape.setSelected(false);
		setChanged();
		notifyObservers(selectedShapes.size());
	}
	public void removeSelectedShapes() {
		selectedShapes.clear();
		shapes.stream().forEach(s->s.setSelected(false));
		setChanged();
		notifyObservers(selectedShapes.size());
	}
	public Shape getShape(long id) { //get shape na osnovu id-ja
		return shapes.get((int)id);
	}
	
	public int getIndexOf(Shape shape) {
		for(int i = 0; i<shapes.size(); i++) {
			if(shapes.get(i).equals(shape)) {
				return i;
			}
		}
		return -1;
	}
	
	
	public void notifyDraw() {
		setChanged();
		notifyObservers(selectedShapes.size());
	}

	public void add(int index, Shape shape) {
		shapes.add(index ,shape);
		setChanged();
		notifyObservers(selectedShapes.size());
	}
}
