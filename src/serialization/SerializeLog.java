package serialization;

import java.awt.Color;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import application.Drawing;
import application.Model;
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
import geometry.Circle;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import geometry.Square;
import hexagon.Hexagon;

public class SerializeLog implements SerializeStrategy{
	private CmdManager manager;
	private Model model;
	
	public  SerializeLog(Model model, CmdManager manager) {
		this.manager = manager;
		this.model = model;

	}
	@Override
	public void save(String path) {
		PrintWriter out = null; //ova klasa omogucuje upisivanje u tekstualni fajl
		try {
			out = new PrintWriter(path);
			for(int i = 0 ; i < manager.numberOfCommands(); i++) { 
				Command command = manager.getCommand(i);
				out.write(command.getCommand());
				out.write("\n");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error saving file. "+ path, "Save error", JOptionPane.ERROR_MESSAGE);

			
		}finally {
			out.close();
		}
	}

	@Override
	public void load(String path) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new FileReader(path));
			model.getShapes().clear();
			model.getSelectedShapes().clear();
			manager.clearAllCommands();
			
			while(scanner.hasNextLine()) {
				Command command=null;
				Shape shape =null;
				Shape newShape = null;
				
				
				String line = scanner.nextLine(); //scanner.nextLine() cita sve karaktere do kraja reda i vraca ga kao string
				String[] splitLine =line.split("->");
				String commandString = splitLine[0];//sve sto je sa leve srane strelice (Add, Delete...)
				String[] shapeLine = splitLine[1].split(":");
				String shapeString = shapeLine[0]; //uzimamo prvi element sa leve strane :
				String[] splitShape = shapeLine[1].split(","); //uzimamo sa desne strane : i to splitujemo po zarezima
				
				if(commandString.equals("Add")) {
					shape = getShape(splitShape, shapeString);
					command = new CmdAdd(shape, model);
				}else if(commandString.equals("Delete")) {
					shape = getShape(splitShape, shapeString);
					List<Shape> deleteList = new ArrayList<Shape>();
					for(Shape s:model.getShapes()) {
						if(shape.equals(s)) {
							deleteList.add(shape);
						}
					}
					command = new CmdDelete(deleteList, model); 
				}else if(commandString.equals("Modification")) {
					
					String[] splitShapes = splitLine[1].split(" to ");
					String[] splitShape1 = splitShapes[0].split(",");
					shape = model.getSelectedShapes().get(0);
					
					String[] splitShape2Class = splitShapes[1].split(":");
					String[] splitShape2 = splitShape2Class[1].split(",");
					newShape = getShape(splitShape2, shapeString); //kreiramo taj shape koji smo dobili iz splitShape2
					command = new CmdModification(shape, newShape, model); //i smestamo u komandu
				}
				else if(commandString.equals("Select")) {
					shape = getShape(splitShape, shapeString);
					command = new CmdSelect(shape, model);
				}
				else if(commandString.equals("Deselect")) {
					shape = getShape(splitShape, shapeString);
					command = new CmdDeselect(shape, model);
				}
				else if(commandString.equals("Back")) {
					shape = getShape(splitShape, shapeString);
					command = new CmdToBack(shape, model);
				}
				else if(commandString.equals("Front")) {
					shape = getShape(splitShape, shapeString);
					command = new CmdToFront(shape, model);
				}
				else if(commandString.equals("BBack")) {
					shape = getShape(splitShape, shapeString);
					command = new CmdBringToBack(shape, model);
				}
				else if(commandString.equals("B-Front")) {
					shape = getShape(splitShape, shapeString);
					command = new CmdBringToFront(shape, model);
				}
				else {
					System.out.println(commandString);
				}
				command.execute(); 
				manager.addCommand(command); 
				
				
			}
			for (int i = manager.numberOfCommands()-1; i>=0 ; i--) { //nakon svake napravljene i izvrsene komande, uzmem poslednju komandu i idem unazad i za svaku tu komandu uradim unexecute
				manager.getCommand(i).unexecute();
			}
			
			manager.setIndex(-1); //sada indeks ne pokazuje ni na jednu komandu, ali ce biti omogucen redo zato sto je redo omogucen kada je indeks komandi >0
			if(scanner != null) {
				scanner.close();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error by reading file: "+ path, "Error loading file", JOptionPane.ERROR_MESSAGE);
		}
	}
	private Shape getShape(String[] splitShape, String shapeString) { 
		String xPointString = splitShape[0];
		String yPointString = splitShape[1];
		int x = Integer.parseInt(xPointString.substring(1,xPointString.length()));
		int y = Integer.parseInt(yPointString.substring(0,yPointString.length()-1));
		
		switch(shapeString) {
			case "Point" : {
				String rString  = splitShape[2];	
				int r = Integer.parseInt(rString.substring(23,rString.length()));
				String gString  = splitShape[3];
				int g = Integer.parseInt(gString.substring(2,gString.length()));
				String bString  = splitShape[4];
				int b = Integer.parseInt(bString.substring(2,bString.length()-1));
				Point shape  = new Point(x,y,new Color(r, g, b), model);
				return shape;
			}
			case "Line" :{
				String xPointString2 = splitShape[2];
				String yPointString2 = splitShape[3];
				int x2 = Integer.parseInt(xPointString2.substring(1,xPointString2.length()));
				int y2 = Integer.parseInt(yPointString2.substring(0,yPointString2.length()-1));
				String rString  = splitShape[4];	
				int r = Integer.parseInt(rString.substring(23,rString.length()));
				String gString  = splitShape[5];
				int g = Integer.parseInt(gString.substring(2,gString.length()));
				String bString  = splitShape[6];
				int b = Integer.parseInt(bString.substring(2,bString.length()-1));
				Line shape  = new Line(new Point(x, y), new Point(x2, y2) ,new Color(r, g, b));
				return shape;
				
			}
			case "Square":{
				String sideString = splitShape[2];
				int side =  Integer.parseInt(sideString.substring(5,sideString.length()));
				
				String rLineString  = splitShape[3];	
				int rLine = Integer.parseInt(rLineString.substring(27,rLineString.length()));
				String gLineString  = splitShape[4];
				int gLine = Integer.parseInt(gLineString.substring(2,gLineString.length()));
				String bLineString  = splitShape[5];
				int bLine = Integer.parseInt(bLineString.substring(2,bLineString.length()-1));
				
				String rAreaString  = splitShape[6];	
				int rArea = Integer.parseInt(rAreaString.substring(27,rAreaString.length()));
				String gAreaString  = splitShape[7];
				int gArea = Integer.parseInt(gAreaString.substring(2,gAreaString.length()));
				String bAreaString  = splitShape[8];
				int bArea = Integer.parseInt(bAreaString.substring(2,bAreaString.length()-1));
				Square shape  = new Square(new Point(x, y),side,new Color(rLine,gLine,bLine), new Color(rArea, gArea, bArea));
				return shape;
			}
			case "Rectangle":{
				String widthString = splitShape[2];
				int width =  Integer.parseInt(widthString.substring(6,widthString.length()));
				String heightString = splitShape[3];
				int height = Integer.parseInt(heightString.substring(7,heightString.length()));
				
				String rLineString  = splitShape[4];	
				int rLine = Integer.parseInt(rLineString.substring(27,rLineString.length()));
				String gLineString  = splitShape[5];
				int gLine = Integer.parseInt(gLineString.substring(2,gLineString.length()));
				String bLineString  = splitShape[6];
				int bLine = Integer.parseInt(bLineString.substring(2,bLineString.length()-1));
				
				String rAreaString  = splitShape[7];	
				int rArea = Integer.parseInt(rAreaString.substring(27,rAreaString.length()));
				String gAreaString  = splitShape[8];
				int gArea = Integer.parseInt(gAreaString.substring(2,gAreaString.length()));
				String bAreaString  = splitShape[9];
				int bArea = Integer.parseInt(bAreaString.substring(2,bAreaString.length()-1));
				Rectangle shape  = new Rectangle(new Point(x, y),width,height,new Color(rLine,gLine,bLine), new Color(rArea, gArea, bArea));
				return shape;
			}
			case "Circle":{
				String sideString = splitShape[2];
				int r =  Integer.parseInt(sideString.substring(7,sideString.length()));
				
				String rLineString  = splitShape[3];	
				int rLine = Integer.parseInt(rLineString.substring(27,rLineString.length()));
				String gLineString  = splitShape[4];
				int gLine = Integer.parseInt(gLineString.substring(2,gLineString.length()));
				String bLineString  = splitShape[5];
				int bLine = Integer.parseInt(bLineString.substring(2,bLineString.length()-1));
				
				String rAreaString  = splitShape[6];	
				int rArea = Integer.parseInt(rAreaString.substring(27,rAreaString.length()));
				String gAreaString  = splitShape[7];
				int gArea = Integer.parseInt(gAreaString.substring(2,gAreaString.length()));
				String bAreaString  = splitShape[8];
				int bArea = Integer.parseInt(bAreaString.substring(2,bAreaString.length()-1));
				Circle shape  = new Circle(new Point(x, y),r,new Color(rLine,gLine,bLine), new Color(rArea, gArea, bArea));
			
				return shape;
			}
			case "Hexagon":{
				String sideString = splitShape[2];
				int r =  Integer.parseInt(sideString.substring(7,sideString.length()));
				
				String rLineString  = splitShape[3];	
				int rLine = Integer.parseInt(rLineString.substring(27,rLineString.length()));
				String gLineString  = splitShape[4];
				int gLine = Integer.parseInt(gLineString.substring(2,gLineString.length()));
				String bLineString  = splitShape[5];
				int bLine = Integer.parseInt(bLineString.substring(2,bLineString.length()-1));
				
				String rAreaString  = splitShape[6];	
				int rArea = Integer.parseInt(rAreaString.substring(27,rAreaString.length()));
				String gAreaString  = splitShape[7];
				int gArea = Integer.parseInt(gAreaString.substring(2,gAreaString.length()));
				String bAreaString  = splitShape[8];
				int bArea = Integer.parseInt(bAreaString.substring(2,bAreaString.length()-1));
				Hexagon hexagon = new Hexagon(x, y, r);
				hexagon.setBorderColor(new Color(rLine,gLine,bLine));
				hexagon.setAreaColor(new Color(rArea, gArea, bArea));
				HexagonAdapter shape  = new HexagonAdapter(hexagon);
									
				return shape;
			}
		
		}
		return null;
	}

}
