package soko;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.io.IOException;
/**
 * A képernyõn való kirajzolást kezeli.
 * @author Axel
 *
 */
public class Kepernyo extends JPanel {
	
	private boolean running=false;	//fut e a játék valamelyik szinten van e a játékos
	private JButton jatek,sajatgame,dicsoseg,vissza,mentes; // a fõbb gombok
	private JButton elsoSzint,masodikSzint,harmadikSzint,negyedikSzint,otodikSzint,hatodikSzint;  //A szintek gombjai
	private JTextField text;
	private String nev;
	Clicklistener click;
	private Character c;
	private Box b;
	private Szint sz;
	private Dicsoseglista dicso;
	private int szint=0;
	private KeyListener bill;
	private MouseListener eger;
	private boolean palyakeszit=false;
	private boolean listaz=false;
	
	/**
	 * Inicializálom a képernyõt.
	 */
	public Kepernyo()  { //Inicializálom a Képernyõt
		dicso = new Dicsoseglista();
		click= new Clicklistener();
		Gombok gombok=new Gombok(click);
		text = new JTextField("Adja meg a nevét.");  
	    text.setBounds(250,60, 100,30);  
		vissza = gombok.getVissza();
		jatek = gombok.getJatek();
		sajatgame = gombok.getSajat();
		dicsoseg = gombok.getDicsoseg();
		elsoSzint = gombok.getElso();
		masodikSzint = gombok.getMasodik();
		harmadikSzint = gombok.getHarmadik();
		negyedikSzint = gombok.getNegyedik();
		otodikSzint = gombok.getOtodik();
		hatodikSzint = gombok.getHatodik();
		mentes = gombok.getMentes();
		szint=1;
		this.setBackground(Color.CYAN);
		this.add(text);
		this.add(jatek);
		this.add(sajatgame);
		this.add(dicsoseg);
		this.setFocusable(true);
		this.setLayout(null);
		}
		/**
		 * A szintek képernyõje.
		 */
		public void szintek() { /// itt lehet választani a szintek között, ezt kirajzolom
			nev=text.getText();
			this.remove(text);
			this.remove(jatek);
			this.remove(sajatgame);
			this.remove(dicsoseg);
			this.add(elsoSzint);
			this.add(masodikSzint);
			this.add(harmadikSzint);
			this.add(negyedikSzint);
			this.add(otodikSzint);
			this.add(hatodikSzint);
			this.add(vissza);
			revalidate();
			repaint();
		}
		
		/**
		 * A pálya szerkesztõ ablaka
		 * @throws IOException Ha nem létezne a fájl.
		 */
		public void palyamaker() throws IOException { //pályakészitõ ablak
			this.remove(text);
			this.remove(jatek);
			this.remove(sajatgame);
			this.remove(dicsoseg);
			this.add(mentes);
			this.add(vissza);
			this.running=true;
			palyakeszit=true;
			this.removeMouseListener(eger);
			this.removeKeyListener(bill);
			this.sz=new Szint("palyamaker.txt");
			revalidate();
			repaint();
			this.addMouseListener(eger = new MouseListener() { //palya keszites egér kattintással
			    @Override
			    public void mouseClicked(MouseEvent e) {
			    	sz.katt(e.getX(),e.getY());
			        repaint();
			    }
				@Override
				public void mouseEntered(MouseEvent e) {
				}
				@Override
				public void mouseExited(MouseEvent e) {
				}
				@Override
				public void mousePressed(MouseEvent e) {
				}
				@Override
				public void mouseReleased(MouseEvent e) {
				}
			});
			}
		

		/**
		 * Ha vége a játéknak a menüt rajzolja ki újra
		 */
		public void endGame() { // vége a játéknak, ujra kirajzolja a menüt
			palyakeszit=false;
			listaz = false;
			szint=1;
			this.removeAll();
			running=false;
			this.remove(vissza);
			this.remove(mentes);
			this.add(text);
			this.add(jatek);
			this.add(dicsoseg);
			this.add(sajatgame);
			revalidate();
			repaint();
		}
		
		/**
		 * Elindul az egyik pálya level.
		 * @param fileszint A pálya fájl neve.
		 * @param level	A szint számlagosan ez kell a következõ szintre való lépéshez.
		 * @throws IOException Ha nem találja a fájlt.
		 */
		public void startGame(String fileszint,int level) throws IOException { //Elindul a játék beolvassa a pályát, ad egy keylistenert ha még eddig nem volt 
			this.remove(elsoSzint);										//ami mozgatja a karaktert illetve rajzol ha megnyomták.
			this.remove(masodikSzint);
			this.remove(harmadikSzint);
			this.remove(negyedikSzint);
			this.remove(otodikSzint);
			this.remove(hatodikSzint);
			szint=level+1;
			this.running=true;
			this.sz=new Szint(fileszint);
			this.c = sz.getChar();
			this.c.setName(nev);
			this.b = sz.getBox();
			this.removeMouseListener(eger);
			this.removeKeyListener(bill);
			this.addKeyListener(bill=new KeyListener(){ //awsd mozgás,   r reset
				@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					switch(e.getKeyCode()) {  
					case KeyEvent.VK_A:
						c.move(-1,-50, 0,sz.getSzint(),b);
						kovetkezoszint();
						repaint(); 
						break;
					case KeyEvent.VK_D:
						c.move(1,50, 0,sz.getSzint(),b);
						kovetkezoszint();
						repaint();
						break;
					case KeyEvent.VK_W:
						c.move(c.getIrany(),0, -50,sz.getSzint(),b);
						kovetkezoszint();
						repaint();
						break;
					case KeyEvent.VK_S:
						c.move(c.getIrany(),0, 50,sz.getSzint(),b);
						kovetkezoszint();
						repaint();
						break;
					case KeyEvent.VK_R: //RESET
						c.reset();
						b.reset();
						repaint();
						break;
					}
				}
				catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			});
			this.revalidate();
			this.repaint();
		}
		
		/**
		 * Dicsõség listának az ablaka.
		 */
		public void dicsosegAblak() {  //dicsõséglista ablaka
			this.remove(text);
			this.remove(jatek);
			this.remove(sajatgame);
			this.remove(dicsoseg);
			this.add(vissza);
			listaz=true;
			this.revalidate();
			this.repaint();
		}
		
		public void paintComponent(Graphics g) { //rajzolás
			super.paintComponent(g);
			draw(g);
		}
		
		public void draw(Graphics g) { //rajzolás
			if(running) {
			sz.draw(g,palyakeszit);
			 if (!palyakeszit)
				 g.drawString("Lépés:"+c.getLepes(), 210, 520);
			}
			if(listaz) {
				dicso.draw(g);
			}
			
		}
		/**
		 * A következõ szintre lépést kezeli.
		 * @throws IOException Ha nem létezne a fájl
		 */
		public void kovetkezoszint() throws IOException {  //következõ szintre lépést kezeli
			if(b.getX()==sz.getWinX() && b.getY()==sz.getWinY()) {
				if (szint==2) {
					dicso.mentes(szint-2,c);
					startGame("masodikszint.txt",2);
				}
				else if (szint==3) {
					dicso.mentes(szint-2,c);
					startGame("harmadikszint.txt",3);
				}
				else if (szint==4) {
					dicso.mentes(szint-2,c);
					startGame("negyedikszint.txt",4);
				}
				else if (szint==5) {
					dicso.mentes(szint-2,c);
					startGame("otodikszint.txt",5);
				}
				else if (szint==6) {
					dicso.mentes(szint-2,c);
					startGame("palyamaker.txt",6);
				}
					else if(szint==7) {
					endGame();
				}
			}	
		}
			
		/**
		 * A gombok megnyomását figyeli.
		 * @author Axel
		 *
		 */
			private class Clicklistener implements ActionListener{ //gombok megnyomását kezeli

				public void actionPerformed(ActionEvent e){
					try {
					if (e.getSource() == jatek){
						szintek();
					}
					else if (e.getSource() == sajatgame){
						palyamaker();
					}
					else if (e.getSource() == dicsoseg){
						dicsosegAblak();
					}
					else if (e.getSource() == elsoSzint){
						startGame("elsoszint.txt",1);
					}
					else if (e.getSource() == masodikSzint){
						startGame("masodikszint.txt",2);
					}
					else if (e.getSource() == harmadikSzint){
						startGame("harmadikszint.txt",3);
					}
					else if (e.getSource() == negyedikSzint){
						startGame("negyedikszint.txt",4);
					}
					else if (e.getSource() == otodikSzint){
						startGame("otodikszint.txt",5);
					}
					else if (e.getSource() == hatodikSzint){
						startGame("palyamaker.txt",6);
					}
					else if (e.getSource() == vissza){
						endGame();
					}
					else if (e.getSource() == mentes){
						sz.mentes();
						endGame();
					}
					}
					catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
		public int getSzint() {return szint;} //teszteléshez van rá szükség
		public boolean getRun(){return running;}
		public boolean getListaz(){return listaz;}
		public boolean getPalyaKeszit(){return palyakeszit;}
}
