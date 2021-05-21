package soko;
import java.io.Serializable;
/**
 * A játékos karaktere.
 * @author Axel
 *
 */
public class Character extends Moveable implements Serializable{
	private String name;
	private int irany;
	private int lepesszam;
	
	public Character() {
		lepesszam=0;
	}
	/**
	 * Konstruktor, a lépést 0tól veszi illetve az irány 1 ami a jobbra felé nézõ karaktert rajzolja ki.
	 * @param xx Az x koordinátája.
	 * @param yy Az y koordinátája.
	 * @param oldalhossz A karakter szélessége és magassága
	 */
	public Character(int xx, int yy, int oldalhossz){
		super(xx, yy, oldalhossz);
		irany=1;
		lepesszam=0;
		name="None";
	};
	/**
	 * Egy négyzettel mozog arrébb amennyiben ezt megteheti, ha dobozt ér azt is eltolja amennyiben ezt megteheti(fal, pályaszéle nem engedik).
	 * @param irany Amelyik irányba néz a karakter
	 * @param xx Az x koordinátája.
	 * @param yy Az y koordinátája.
	 * @param palya A pálya-
	 * @param b A doboz.
	 */
	public void move(int irany,int xx, int yy,Field[][] palya,Box b) { //mozog egy négyzettel arrébb 
		this.irany = irany;
		if(this.x+xx<0 || this.x+xx>450 || this.y+yy<0 ||this.y+yy>450 ) //ha pályán kivûlre lépne
		{
			return;
		}
		if (palya[(this.y+yy)/oldalhossz][(this.x+xx)/oldalhossz].getFalE()==true) { //ha falra lépne
			return;
		}
		else if (b.getX()==this.x+xx && b.getY()==this.y+yy){ //ha dobozra lépne
			if(b.megtoltak(xx,yy,palya)==false) { //ha dobozra lépne ha a doboz falba ütközne
				return;
			}
		}
		this.prevX=this.x; //egyébként lép
		this.prevY=this.y;
		this.x+=xx;
		this.y+=yy;
		this.lepesszam+=1;
		
	}
	public int getIrany() {return irany;}
	public void setIrany(int x){this.irany=x;}
	public int getLepes() {return lepesszam;}
	public void addLepes(int x){lepesszam+=x;}
	
	public void setAll(int i, int j, int k) {
		this.x=i;
		this.y=j;
		this.prevX=i;
		this.prevY=j;
		this.kezdoX=i;
		this.kezdoY=j;
		this.lepesszam=k;
	}
	
	public void setName(String n) {
		name=n;
	}
	
	public String getName(){return name;}
	public void setLepes(int x){lepesszam=x;};
	
	/**
	 * A kezdõpontba helyezi el a karaktert.
	 */
	public void reset() {
		this.x=kezdoX;
		this.prevX=kezdoX;
		this.y=kezdoY;
		this.prevY=kezdoY;
		this.lepesszam=0;
	}
}
