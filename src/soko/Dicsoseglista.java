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
     * Fájlba mentés
     * @param szint Hanyadik pálya
     * @param c	A Karakter
     */
    public void mentes(int szint,Character c) { //fájlba mentés
		
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
     * Fájlból beolvassa a szerializált dicsõség listát ha még nincs ilyen akkor generál egyet értékekkel.
     */
	public void beolvas(){ //beolvasom a listát ha nincs létrehozok egyet próba értékekkel.
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
	        	 c.setName("próba");
					chars.add(c);				
				}
	         Character c=new Character(20,20,50);
        	 c.setName("próba");
        	 c.setLepes(500);
	         mentes(1,c);
		}catch(ClassNotFoundException c){
            c.printStackTrace();
        }
	}
	/**
	 * Rajzolás.
	 * @param g Rajzoláshoz szükséges.
	 */
    public void draw(Graphics g) { 
    	for (int i=0; i<szintek; i++) {
    		g.drawString((i+1)+". szint "+chars.get(i).getName()+" "+chars.get(i).getLepes()+" lépés",200,(i*50)+100);				
		}
    }
}
