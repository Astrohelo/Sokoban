package soko;
import java.awt.event.ActionListener;

import javax.swing.JButton;
/**
 * A gombok inicializálása történik itt amiket majd használok a Képernyõn.
 * @author Axel
 *
 */
public class Gombok { //gombok létrehozása amik kellenek a programban
	private JButton jatek;
	private JButton dicsoseg;
	private JButton elsoSzint;
	private JButton masodikSzint;
	private JButton harmadikSzint;
	private JButton negyedikSzint;
	private JButton otodikSzint;
	private JButton hatodikSzint;
	private JButton vissza;
	private JButton sajatgame;
	private JButton mentes;
	public Gombok(ActionListener click){
	vissza = new JButton("Kezdõoldal");
	vissza.setBounds(0,500,200,50);
	vissza.addActionListener(click);
	vissza.setFocusable(false);
	
	mentes = new JButton("Mentés");
	mentes.setBounds(250,500,200,50);
	mentes.addActionListener(click);
	mentes.setFocusable(false);
	
	jatek = new JButton("Játék!");
	jatek.setBounds(150,100,300,60);
	jatek.addActionListener(click);
	jatek.setFocusable(false);
	
	sajatgame = new JButton("Saját pálya");
	sajatgame.setBounds(150,180,300,60);
	sajatgame.addActionListener(click);
	sajatgame.setFocusable(false);
	
	dicsoseg = new JButton("Dicsõséglista");
	dicsoseg.setBounds(150,260,300,60);
	dicsoseg.addActionListener(click);
	dicsoseg.setFocusable(false);
	
	elsoSzint = new JButton("1");
	elsoSzint.setBounds(30,100,80,80);
	elsoSzint.addActionListener(click);
	elsoSzint.setFocusable(false);
	
	masodikSzint = new JButton("2");
	masodikSzint.setBounds(120,100,80,80);
	masodikSzint.addActionListener(click);
	masodikSzint.setFocusable(false);
	
	harmadikSzint = new JButton("3");
	harmadikSzint.setBounds(210,100,80,80);
	harmadikSzint.addActionListener(click);
	harmadikSzint.setFocusable(false);
	
	negyedikSzint = new JButton("4");
	negyedikSzint.setBounds(300,100,80,80);
	negyedikSzint.addActionListener(click);
	negyedikSzint.setFocusable(false);
	
	otodikSzint = new JButton("5");
	otodikSzint.setBounds(390,100,80,80);
	otodikSzint.addActionListener(click);
	otodikSzint.setFocusable(false);
	
	hatodikSzint = new JButton("Custom");
	hatodikSzint.setBounds(480,100,80,80);
	hatodikSzint.addActionListener(click);
	hatodikSzint.setFocusable(false);
	}
	public JButton getVissza() {return vissza;}
	public JButton getJatek() {return jatek;}
	public JButton getSajat() {return sajatgame;}
	public JButton getDicsoseg() {return dicsoseg;}
	public JButton getElso() {return elsoSzint;}
	public JButton getMasodik() {return masodikSzint;}
	public JButton getHarmadik() {return harmadikSzint;}
	public JButton getNegyedik() {return negyedikSzint;}
	public JButton getOtodik() {return otodikSzint;}
	public JButton getHatodik() {return hatodikSzint;}
	public JButton getMentes() {return mentes;}
}
