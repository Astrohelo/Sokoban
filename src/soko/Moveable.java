package soko;

/**
 * A mozgathat� oszt�lyok �se
 * @author Axel
 *
 */
public class Moveable {
	protected int x; //bal fels� pontja a n�gyzetnek
	protected int y;
	protected int prevX;
	protected int prevY;
	protected int kezdoX;
	protected int kezdoY;
	protected int oldalhossz;
	
	public Moveable() {
		
	}
	
	public Moveable(int xx, int yy, int oldalhossz){
		this.x=xx;
		this.prevX=xx;
		this.y=yy;
		this.prevY=yy;
		this.kezdoX=xx;
		this.kezdoY=yy;
		this.oldalhossz=oldalhossz;
	};
	
	/**
	 * Vissza�llitja a kezd� �llapotot.
	 */
	public void reset() {
		this.x=kezdoX;
		this.prevX=kezdoX;
		this.y=kezdoY;
		this.prevY=kezdoY;
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
	public int getPrevX() {return prevX;}
	public int getPrevY() {return prevY;}
	public int getKezdoX() {return kezdoX;}
	public int getKezdoY() {return kezdoY;}
}
