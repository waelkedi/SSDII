package SS2.io;

import java.awt.Color;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.NullPointerException;
import java.lang.NumberFormatException;
import java.lang.IndexOutOfBoundsException;
import SS2.geometry.Segment;

public class ParsedFile {

	Segment[] list;
	int x_bound, y_bound;
	
	public class NotFoundException extends NullPointerException {}
	
	public class WrongFormatException extends NumberFormatException {}
		
	private int firstLineReader(String line){
		
		String[] temp = line.split(" ");
		if (temp.length != 4) throw new IndexOutOfBoundsException();
		x_bound = Integer.parseInt(temp[1]);
		y_bound = Integer.parseInt(temp[2]);
		if (!(x_bound > 0 && y_bound > 0)) throw new IndexOutOfBoundsException();
		return Integer.parseInt(temp[3]);
	}
	
	private Segment segmentLineReader(String line){
		
		String[] temp = line.split(" ");
		if (temp.length != 5) throw new IndexOutOfBoundsException();
		float x1 = Float.parseFloat(temp[0]);
		float y1 = Float.parseFloat(temp[1]);
		float x2 = Float.parseFloat(temp[2]);
		float y2 = Float.parseFloat(temp[3]);
		if (!(Math.abs(x1) <= x_bound && Math.abs(y1) <= y_bound 
		&& Math.abs(x2) <= x_bound && Math.abs(y2) <= y_bound)) throw new IndexOutOfBoundsException();
		Color color = getColor(temp[4]);
		return new Segment(x1, y1, x2, y2, color);
	}
	
	private static Color getColor(String s){
		
		Color res;
		switch (s){
			case "Bleu": res = Color.BLUE; break;
			case "Rouge" : res = Color.RED; break;
			case "Orange" : res = Color.ORANGE; break;
			case "Jaune" : res = Color.YELLOW; break;
			case "Noir" : res = Color.BLACK; break;
			case "Violet" : res = new Color(148,0,211); break;
			case "Marron" : res = new Color(139,69,0); break;
			case "Vert" : res = Color.GREEN; break;
			case "Gris" : res = Color.GRAY; break;
			case "Rose" : res = Color.PINK; break;
			default : res = null; break;
		}
		if (res == null) throw new NumberFormatException();
		return res;
	}
	
	private void ParsedFileBuilder(Scanner scan) throws FileNotFoundException{
		
		int size = firstLineReader(scan.nextLine());
		list = new Segment[size];
		for(int i = 0; i < size; i++){
			list[i] = segmentLineReader(scan.nextLine());
		}
		if (scan.hasNextLine()) throw new IndexOutOfBoundsException();
		scan.close();
	}
	
	public ParsedFile(File file){
		
		Scanner scan = null;
		try{
			scan = new Scanner(file);
			ParsedFileBuilder(scan);
		}
		catch(FileNotFoundException e){
			throw new NotFoundException();
		}
		catch(NumberFormatException e){
			scan.close();
			throw new WrongFormatException();
		}
		catch(IndexOutOfBoundsException e){
			scan.close();
			throw new WrongFormatException();
		}
	}
	
	public ParsedFile(String s){
		
		Scanner scan = null;
		try{
			File file = new File(s);
			scan = new Scanner(file);
			ParsedFileBuilder(scan);
		}
		catch(FileNotFoundException e){
			throw new NotFoundException();
		}
		catch(NumberFormatException e){
			scan.close();
			throw new WrongFormatException();
		}
		catch(IndexOutOfBoundsException e){
			scan.close();
			throw new WrongFormatException();
		}
	}
	
	public Segment[] getSegments(){
		
		return list;
	}
	
	public int getXBound(){
		
		return x_bound;
	}
	
	public int getYBound(){
		
		return y_bound;
	}

}	
