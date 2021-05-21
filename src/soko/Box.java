package soko;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
/**
 * A doboz amit a játékos tologat
 * @author Axel
 *
 */
public class Box extends Moveable{ //ezt fogja majd tologatni a karakter
	/**
	 * Konstruktor
	 * @param xx Az x koordinátája.
	 * @param yy Az y koordinátája.
	 * @param oldalhossz A karakter szélessége és magassága
	 */
	public Box(int xx, int yy, int oldalhossz){
		super(xx, yy, oldalhossz);
	};
	
	/**
	 * Amikor a dobozt megtolják
	 * @param xx Az x koordinátája.
	 * @param yy Az y koordinátája.
	 * @param palya A pálya tömbje.
	 * @return Visszaadja, hogy sikeresen eltolták-e.
	 */
	public boolean megtoltak(int xx, int yy, Field[][]palya) { 	
		if (palya[(this.y+yy)/oldalhossz][(this.x+xx)/oldalhossz].getFalE()==true) { //amikor a dobozt megtolják de falra tolnák igy nem történik semmi
			return false;
		}
		this.prevX=this.x;
		this.prevY=this.y;
		this.x+=xx;
		this.y+=yy;
		return true;
	}
	/**
	 * Beállitja az értékeket.
	 * @param i x koordináta
	 * @param j y koordináta
	 */
	public void setAll(int i, int j) {
		this.x=i;
		this.y=j;
		this.prevX=i;
		this.prevY=j;
		this.kezdoX=i;
		this.kezdoY=j;
		
	}
}
