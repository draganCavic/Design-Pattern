package application;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import command.CmdAdd;
import command.CmdBringToBack;
import command.CmdBringToFront;
import command.CmdDelete;
import command.CmdDeselect;
import command.CmdManager;
import command.CmdModification;
import command.CmdSelect;
import command.CmdToBack;
import command.CmdToFront;
import command.Command;
import dialog.DlgChooseColor;
import dialog.DlgCircle;
import dialog.DlgDelete;
import dialog.DlgHexagon;
import dialog.DlgModificationCircle;
import dialog.DlgModificationHexagon;
import dialog.DlgModificationLine;
import dialog.DlgModificationPoint;
import dialog.DlgModificationRectangle;
import dialog.DlgModificationSquare;
import dialog.DlgRectangle;
import dialog.DlgSquare;
import geometry.Circle;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import geometry.Square;
import hexagon.Hexagon;
import serialization.SerializeDrawing;
import serialization.SerializeLog;
import serialization.Serializer;

public class Controller {

	private Model model;
	private Drawing drawing;
	private CmdManager commandManager;
	private Color edge=Color.BLACK;
	private Color area=Color.WHITE;
	private int selected;
	private int click=1;
	private int xPoint;
	private int yPoint;
	private int xPoint1;
	private int yPoint1;
	
	public Controller(Drawing drawing, Model model, CmdManager commandManager) {
		super();
		this.drawing = drawing;
		this.model = model;
		this.commandManager = commandManager;
	}

	public void point() {
		selected = 1;
	}
	public void line() {
		selected = 2;
	}
	public void square() {
		selected = 3;
	}
	public void rectangle() {
		selected = 4;
	}
	public void circle() {
		selected = 5;
	}
	public void hexagon() {
		selected = 6;
	}
	public void color() {
		DlgChooseColor color = new DlgChooseColor();
		color.setVisible(true);
		if (color.potvrda == true){
			if (color.colorEdge != null)
				edge = color.getColorEdge();
			if (color.colorArea != null) {
				area = color.getColorArea();
			}
		}
	}
	public void select() {
		selected = 7;
	}
	public void modification() {
		if(model.getSelectedShapes().size() != 1) { //mora da se nalazi samo jedan jedini selektovani oblik
			return;
		}

		Shape shape = null;
		Shape selectedShape = model.getSelectedShapes().get(0); //uzimamo prvi i jedini selektovani oblik
		//u zavisnosti od oblika poziva se dijalog 
		if ( selectedShape instanceof Point)
		{

			DlgModificationPoint dt = new DlgModificationPoint(model);

			dt.color = ((Point) selectedShape).getColor();
			dt.x = ((Point) selectedShape).getX();
			dt.y = ((Point) selectedShape).getY();
			dt.setVisible(true);

			if (dt.getData()!=null)
			{
				shape = dt.getData();
			}
			if(dt.confirm == false) {
				return;
			}

		}else if(selectedShape instanceof Line) {
			DlgModificationLine dl = new DlgModificationLine(model);
			dl.color = ((Line) selectedShape).getColor();
			dl.x1 = ((Line) selectedShape).getStartPoint().getX();
			dl.x2 = ((Line) selectedShape).getEndPoint().getX();
			dl.y1 = ((Line) selectedShape).getStartPoint().getY();
			dl.y2 = ((Line) selectedShape).getEndPoint().getY();
			dl.setVisible(true);
			if (dl.getData()!=null)
			{

				shape = dl.getData();
			}
			if(dl.confirm == false) {
				return;
			}
		}else if(selectedShape instanceof Rectangle) {
			DlgModificationRectangle dr = new DlgModificationRectangle();
			dr.colorArea = ((Rectangle) selectedShape).getInsideColor();
			dr.colorEdge = ((Rectangle) selectedShape).getColor();
			dr.height = ((Rectangle) selectedShape).getHeight();
			dr.width = ((Rectangle) selectedShape).getEdgeLength();
			dr.x = ((Rectangle) selectedShape).getUpperLeft().getX();
			dr.y = ((Rectangle) selectedShape).getUpperLeft().getY();
			dr.setVisible(true);
			if (dr.getData()!=null)
			{
				shape = dr.getData();
			}
			if(dr.confirm == false) {
				return;
			}
		}else if(selectedShape instanceof Square) {
			DlgModificationSquare dk = new DlgModificationSquare();
			dk.colorArea = ((Square) selectedShape).getInsideColor();
			dk.colorEdge = ((Square) selectedShape).getColor();
			dk.length = ((Square) selectedShape).getEdgeLength();
			dk.x = ((Square) selectedShape).getUpperLeft().getX();
			dk.y = ((Square) selectedShape).getUpperLeft().getY();
			dk.setVisible(true);
			if (dk.getData()!=null)
			{
				shape = dk.getData();
			}
			if(dk.confirm == false) {
				return;
			}
		}else if(selectedShape instanceof Circle) {
			DlgModificationCircle dk = new DlgModificationCircle();
			dk.colorArea = ((Circle) selectedShape).getInsideColor();
			dk.colorEdge = ((Circle) selectedShape).getColor();
			dk.radius = ((Circle) selectedShape).getR();
			dk.x = ((Circle) selectedShape).getCenter().getX();
			dk.y= ((Circle) selectedShape).getCenter().getY();
			dk.setVisible(true);
			if (dk.getDataC()!=null)
			{
				shape = dk.getDataC();
			}
			if(dk.confirm == false) {
				return;
			}
		}
		else if(selectedShape instanceof HexagonAdapter) {
			DlgModificationHexagon dh = new DlgModificationHexagon(model);
			dh.colorArea = ((HexagonAdapter) selectedShape).getInsideColor();
			dh.colorEdge = ((HexagonAdapter) selectedShape).getColor();
			dh.radius = ((HexagonAdapter) selectedShape).getR();
			dh.x = ((HexagonAdapter) selectedShape).getX();
			dh.y= ((HexagonAdapter) selectedShape).getY();
			dh.setVisible(true);
			if (dh.getDataH()!=null)
			{
				shape = dh.getDataH();
			}
			if(dh.confirm == false) {
				return;
			}
		}

		if(shape == null) {
			return;
		}

		//CmdManager commandManager = CmdManager.getInstance(); 
		CmdModification modificationCommand = new CmdModification(selectedShape,shape, model); 
		modificationCommand.execute(); 
		commandManager.addCommand(modificationCommand);
		drawing.getBtnUndo().setEnabled(true);
		drawing.getBtnRedo().setEnabled(false);
	}
	
	
	public void drawing(MouseEvent arg0) {
		Shape shape = null;
		if (selected==1)
		{

			Point t1 = new Point (arg0.getX(), arg0.getY(), edge, model);
			shape = t1;

		}
		else if (selected==2)
		{
			if(click == 1){
				xPoint=arg0.getX();
				yPoint=arg0.getY();
				click=2;

			}else{
				xPoint1=arg0.getX();
				yPoint1=arg0.getY();

				Line l1 = new Line( new Point(xPoint,yPoint), new Point(xPoint1,yPoint1), edge);
				shape = l1;
				click=1;
			}



		}

		else if (selected==3)
		{
			DlgSquare dk = new DlgSquare();
			dk.setVisible(true);
			if(!dk.isEmpty()) {
				Square kv1=new Square(new Point(arg0.getX(), arg0.getY()), dk.getlblUnesiteDuzinuStranice(), edge, area);
				shape = kv1;
			}
		}
		else if (selected==4)
		{

			xPoint=arg0.getX();
			yPoint=arg0.getY();

			DlgRectangle dr = new DlgRectangle();
			dr.setVisible(true);
			if(!dr.isEmpty()) {
				Rectangle r= new Rectangle(new Point(xPoint, yPoint), dr.getlblWidth(),dr.getLblHeight(), edge, area);
				shape = r;
			}

		}
		else if (selected==5)
		{
			xPoint=arg0.getX();
			yPoint=arg0.getY();
			DlgCircle dc= new DlgCircle();
			dc.setVisible(true);
			if(!dc.isEmpty()) {
				Circle kr1= new Circle(new Point(xPoint, yPoint), dc.getTxtRadius(), edge, area);
				shape = kr1;
			}
		}
		else if (selected==6)
		{
			xPoint=arg0.getX();
			yPoint=arg0.getY();	
			DlgHexagon dx = new DlgHexagon();
			dx.setVisible(true);
			if(!dx.isEmpty()) {
				Hexagon hexagon= new Hexagon(xPoint, yPoint, dx.getTxtRadius());
				hexagon.setBorderColor(edge);
				hexagon.setAreaColor(area);
				shape = new HexagonAdapter(hexagon);

			}
		}
		else if (selected == 7) {
			xPoint=arg0.getX();
			yPoint=arg0.getY();
			List<Shape> allShapes = model.getShapes(); //uzimamo sve oblike iz repoziorijuma

			for(int i = allShapes.size() - 1; i >= 0  ; i--)
			{
				if(allShapes.get(i).contains(xPoint, yPoint)){
					Shape selectedShape = allShapes.get(i);
					if(selectedShape.isSelected()) {
						//CmdManager commandManager = CmdManager.getInstance();
						CmdDeselect deselectCommand = new CmdDeselect(selectedShape, model);
						deselectCommand.execute();
						commandManager.addCommand(deselectCommand);
						
						return;
					}
//					CmdManager commandManager = CmdManager.getInstance();
					CmdSelect selectCommand = new CmdSelect(selectedShape, model);
					selectCommand.execute();
					commandManager.addCommand(selectCommand); 

					return;
				}

			}
			//ako se nije desila ni selekcija ni deselekcija, tj ako je kliknuto u prazno
			if(model.getSelectedShapes().size()>0) {
				for(int i = model.getSelectedShapes().size()-1; i>=0; i--) {
//					CmdManager commandManager = CmdManager.getInstance();
					CmdDeselect deselectCommand = new CmdDeselect(model.getSelectedShapes().get(i), model);
					deselectCommand.execute();
					commandManager.addCommand(deselectCommand);
				}
			}
		}

		//ako nije uslo  u selekciju
		if(shape != null) {
//			CmdManager commandManager = CmdManager.getInstance();
			if(model.getSelectedShapes().size()>0) {
				for(int i = model.getSelectedShapes().size()-1; i>=0; i--) {
					CmdDeselect deselectCommand = new CmdDeselect(model.getSelectedShapes().get(i), model);
					deselectCommand.execute();
					commandManager.addCommand(deselectCommand);
				}
			}
			CmdAdd addCommand = new CmdAdd(shape, model);
			addCommand.execute();//
			commandManager.addCommand(addCommand); 
			drawing.getBtnUndo().setEnabled(true);
			drawing.getBtnRedo().setEnabled(false);
			
		}
	}
	public void delete() { 
		DlgDelete b= new DlgDelete();
		b.setVisible(true);

		if (b.getN()==1)
		{
//			CmdManager commandManager = CmdManager.getInstance();
			CmdDelete deleteCommand = new CmdDelete(model.getSelectedShapes(), model);
			commandManager.addCommand(deleteCommand);
			deleteCommand.execute();

			drawing.getBtnUndo().setEnabled(true);
			drawing.getBtnRedo().setEnabled(false);

		}
	}
	public void undo() { 
//		CmdManager commandManager = CmdManager.getInstance();
		int index = commandManager.getIndex();
		Command command = commandManager.getCommand(index); 
		command.unexecute();
		commandManager.setIndex(index-1); 
	}
	public void redo() {
//		CmdManager commandManager = CmdManager.getInstance();
		int index = commandManager.getIndex();
		Command command = commandManager.getCommand(index+1);
		command.execute();
		commandManager.setIndex(index+1);
	}
	public void toFront() {
//		CmdManager commandManager = CmdManager.getInstance();
		CmdToFront toFrontCommand = new CmdToFront(model.getSelectedShapes().get(0), model);
		commandManager.addCommand(toFrontCommand);
		toFrontCommand.execute();
		
	}
	public void toBack() {
//		CmdManager commandManager = CmdManager.getInstance();
		CmdToBack toBackCommand = new CmdToBack(model.getSelectedShapes().get(0), model);
		commandManager.addCommand(toBackCommand);
		toBackCommand.execute();
	}
	public void bringToBack() {
//		CmdManager commandManager = CmdManager.getInstance();
		CmdBringToBack bringToBackCommand = new CmdBringToBack(model.getSelectedShapes().get(0), model);
		commandManager.addCommand(bringToBackCommand);
		bringToBackCommand.execute();
	}
	public void bringToFront() {
//		CmdManager commandManager = CmdManager.getInstance();
		CmdBringToFront bringToFrontCommand = new CmdBringToFront(model.getSelectedShapes().get(0), model);
		commandManager.addCommand(bringToFrontCommand);
		bringToFrontCommand.execute();
	}
	public void save(int i) {
		
		Serializer serializer = new Serializer(); 
		JFileChooser fileChooser = new JFileChooser(); 
		fileChooser.setAcceptAllFileFilterUsed(false); 
		
		if(i==0) {
			serializer.setSerializeStrategy(new SerializeLog(model, commandManager));
			FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");
			fileChooser.setFileFilter(filter);
		}
		else if(i==1) {
			serializer.setSerializeStrategy(new SerializeDrawing(model, commandManager)); 
			FileNameExtensionFilter filter = new FileNameExtensionFilter("bin", "bin"); 
			fileChooser.setFileFilter(filter); 
		}
		int returnValue = fileChooser.showSaveDialog(null); 
		if(returnValue == JFileChooser.APPROVE_OPTION) { 
			String path = fileChooser.getSelectedFile().getAbsolutePath(); 
			
			if(i==0) {
				if(!path.endsWith(".txt")){ 
					path += ".txt"; 
				}
			}
			else if(i==1) {
				if(!path.endsWith(".bin")){
					path += ".bin";
				}
			}
			
			serializer.save(path); 
		}
	}
	public void load(int i) {
		Serializer serializer = new Serializer();
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		
		if(i==0) {
			serializer.setSerializeStrategy(new SerializeLog(model, commandManager));
			FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");
			fileChooser.setFileFilter(filter);
		}
		else if(i==1) {
			serializer.setSerializeStrategy(new SerializeDrawing(model, commandManager));
			FileNameExtensionFilter filter = new FileNameExtensionFilter("bin", "bin");
			fileChooser.setFileFilter(filter);
		}
		
		
		
		int returnValue = fileChooser.showOpenDialog(null);
		if(returnValue == JFileChooser.APPROVE_OPTION) {
			serializer.load(fileChooser.getSelectedFile().getPath()); 
		}
	}

	public Color getEdge() {
		return edge;
	}

	public void setEdge(Color edge) {
		this.edge = edge;
	}

	public Color getArea() {
		return area;
	}

	public void setArea(Color area) {
		this.area = area;
	}
	
	public Model getModel() {
		return model;
	}

}

