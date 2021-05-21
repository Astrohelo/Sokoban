package soko;
import java.awt.Graphics;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Dicsoseglista{
	
	private static  ArrayList<Character> chars = new ArrayList<Character>();
    private static int szintek = 5;
    
    public Dicsoseglista() {
    	
    	beolvas();
    }
    /**
     * F�jlba ment�s
     * @param szint Hanyadik p�lya
     * @param c	A Karakter
     */
    public void mentes(int szint,Character c) { //f�jlba ment�s
		
    	if(c.getLepes()<=chars.get(szint).getLepes()) {
    		chars.set(szint,c);
    	}
			try {
				FileOutputStream f = new FileOutputStream("dicsoseglista.ser");
				ObjectOutputStream o = new ObjectOutputStream(f);
				o.writeObject(chars);
				o.close();
				f.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
    }
    
    /**
     * F�jlb�l beolvassa a szerializ�lt dics�s�g list�t ha m�g nincs ilyen akkor gener�l egyet �rt�kekkel.
     */
	public void beolvas(){ //beolvasom a list�t ha nincs l�trehozok egyet pr�ba �rt�kekkel.
		try {
			FileInputStream f = new FileInputStream("dicsoseglista.ser");
	        ObjectInputStream o = new ObjectInputStream(f);
	        chars = (ArrayList<Character>)o.readObject();
	        o.close();
	        f.close();
	    }catch(IOException e){
	         e.printStackTrace();
	         for (int i=0; i<szintek; i++) {
	        	 Character c=new Character(20,20,50);
	        	 c.setLepes(500);
	        	 c.setName("pr�ba");
					chars.add(c);				
				}
	         Character c=new Character(20,20,50);
        	 c.setName("pr�ba");
        	 c.setLepes(500);
	         mentes(1,c);
		}catch(ClassNotFoundException c){
            c.printStackTrace();
        }
	}
	/**
	 * Rajzol�s.
	 * @param g Rajzol�shoz sz�ks�ges.
	 */
    public void draw(Graphics g) { 
    	for (int i=0; i<szintek; i++) {
    		g.drawString((i+1)+". szint "+chars.get(i).getName()+" "+chars.get(i).getLepes()+" l�p�s",200,(i*50)+100);				
		}
    }
}
