package soko;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
/**
 * A doboz amit a j�t�kos tologat
 * @author Axel
 *
 */
public class Box extends Moveable{ //ezt fogja majd tologatni a karakter
	/**
	 * Konstruktor
	 * @param xx Az x koordin�t�ja.
	 * @param yy Az y koordin�t�ja.
	 * @param oldalhossz A karakter sz�less�ge �s magass�ga
	 */
	public Box(int xx, int yy, int oldalhossz){
		super(xx, yy, oldalhossz);
	};
	
	/**
	 * Amikor a dobozt megtolj�k
	 * @param xx Az x koordin�t�ja.
	 * @param yy Az y koordin�t�ja.
	 * @param palya A p�lya t�mbje.
	 * @return Visszaadja, hogy sikeresen eltolt�k-e.
	 */
	public boolean megtoltak(int xx, int yy, Field[][]palya) { 	
		if (palya[(this.y+yy)/oldalhossz][(this.x+xx)/oldalhossz].getFalE()==true) { //amikor a dobozt megtolj�k de falra toln�k igy nem t�rt�nik semmi
			return false;
		}
		this.prevX=this.x;
		this.prevY=this.y;
		this.x+=xx;
		this.y+=yy;
		return true;
	}
	/**
	 * Be�llitja az �rt�keket.
	 * @param i x koordin�ta
	 * @param j y koordin�ta
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
