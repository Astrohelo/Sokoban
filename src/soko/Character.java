package soko;
import java.io.Serializable;
/**
 * A j�t�kos karaktere.
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
	 * Konstruktor, a l�p�st 0t�l veszi illetve az ir�ny 1 ami a jobbra fel� n�z� karaktert rajzolja ki.
	 * @param xx Az x koordin�t�ja.
	 * @param yy Az y koordin�t�ja.
	 * @param oldalhossz A karakter sz�less�ge �s magass�ga
	 */
	public Character(int xx, int yy, int oldalhossz){
		super(xx, yy, oldalhossz);
		irany=1;
		lepesszam=0;
		name="None";
	};
	/**
	 * Egy n�gyzettel mozog arr�bb amennyiben ezt megteheti, ha dobozt �r azt is eltolja amennyiben ezt megteheti(fal, p�lyasz�le nem engedik).
	 * @param irany Amelyik ir�nyba n�z a karakter
	 * @param xx Az x koordin�t�ja.
	 * @param yy Az y koordin�t�ja.
	 * @param palya A p�lya-
	 * @param b A doboz.
	 */
	public void move(int irany,int xx, int yy,Field[][] palya,Box b) { //mozog egy n�gyzettel arr�bb 
		this.irany = irany;
		if(this.x+xx<0 || this.x+xx>450 || this.y+yy<0 ||this.y+yy>450 ) //ha p�ly�n kiv�lre l�pne
		{
			return;
		}
		if (palya[(this.y+yy)/oldalhossz][(this.x+xx)/oldalhossz].getFalE()==true) { //ha falra l�pne
			return;
		}
		else if (b.getX()==this.x+xx && b.getY()==this.y+yy){ //ha dobozra l�pne
			if(b.megtoltak(xx,yy,palya)==false) { //ha dobozra l�pne ha a doboz falba �tk�zne
				return;
			}
		}
		this.prevX=this.x; //egy�bk�nt l�p
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
	 * A kezd�pontba helyezi el a karaktert.
	 */
	public void reset() {
		this.x=kezdoX;
		this.prevX=kezdoX;
		this.y=kezdoY;
		this.prevY=kezdoY;
		this.lepesszam=0;
	}
}
