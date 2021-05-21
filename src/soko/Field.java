package soko;

/**
 * A pálya négyzetei, fal/háttér/cél
 * @author Axel
 *
 */
public class Field {
private int x; //bal felsõ pontja a négyzetnek
private int y;
private int oldalhossz; //negyzet lesz eleg 1 oldal
private boolean fal;
private boolean win;

	Field(int xx, int yy , int oldal,boolean falE,boolean winE){
		x=xx;
		y=yy;
		oldalhossz=oldal;
		fal=falE;
		win=winE;
		
	}
	public void setField(int xx, int yy, int oldal,boolean falE) {
		x=xx;
		y=yy;
		oldalhossz=oldal;
		fal=falE;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	public int getOldal() {
		return oldalhossz;
	}
	public boolean getFalE() {
		return fal;
	}
	public boolean getWinE() {
		return win;
	}
	public void setFal(boolean b) {
		fal=b;
	}
	public void setWin(boolean b) {
		win=b;
	}
}
