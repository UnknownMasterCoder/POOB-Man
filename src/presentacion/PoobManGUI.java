package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import aplicacion.Archivo;
import aplicacion.Elemento;
import aplicacion.PoobManExecption;
import aplicacion.Tablero;

public class PoobManGUI extends JFrame {
	
	
	private JDialog dato;
	private JLabel vidas,poobMen3,pacMan,puntaje,ghostPa,ghostTo,ghostDe,ghostPr,jugadores,dificultad,color;
	private String lastMove = "R";
	private JMenuItem guardar,abrir,importar,exportar,cerrar,nuevo;	
	private JMenuBar menuBar;
	private JMenu menu;
	private JPanel juego,tablero,poobLaberinto,poobInicial;
	private JButton jugar,salir,nuevoJuego;
	private int [][] laberinto;
	private Tablero aplicacion;
	private ImageIcon poobMen2,poobMen2R,poobMenU,poobMenD,iconTemp, poobCannibal, poobMoon, poobSun, ghostComible,poobFun,vacio,poobMenLD,poobMenUPL,poobMenUp,poobMenLeft,poobMenRD,wall,poobDot,poobMen,preparado,patrullero,detective,torpe;
	private int posX = 1,posY =1,players = 0,posX2 = 18,posY2 = 18;
	private String nivel = "Facil";
	private JComboBox noJugadores,laDificultad;
	
	
	public PoobManGUI(){
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setTitle("POOB-MAN");
		this.setResizable(false);
		aplicacion = Tablero.demeTablero(players,nivel);
		laberinto = aplicacion.demeLaberinto();	
		prepareElementos();
		prepareAciones();		
	}		
	
	public static void main(String [] arg){
		
		
		PoobManGUI poobMan = new PoobManGUI();
	}
	
	private void prepareElementos(){
		changeSize();	
		crearMenu();
		prepareImagenes();
		elmentosTablero();
		crearDialogo();		
		elementosPanelMedio();	
		elementosPanelBajo();
		
		
		
	}
	
	private void prepareImagenes(){
		
		vacio = transformeImagen("vacio", 30, 30);
		wall = transformeImagen("wall", 32, 30);
		
		poobDot = transformeImagen("poobDot", 10, 10);
		poobFun = transformeImagen("poobFun", 14, 14);
		poobCannibal = transformeImagen("poobCannibal", 30, 30);
		poobMoon = transformeImagen("poobMoon", 45, 45);
		poobSun = transformeImagen("poobSun", 45, 45);
		
		preparado = transformeImagen("preparado", 30, 30);
		detective = transformeImagen("detective", 30, 30);
		patrullero = transformeImagen("patrullero", 30, 30);
		torpe = transformeImagen("torpe", 30, 30);		
		ghostComible = transformeImagen("ghostComible", 30, 30);
		
		poobMen = transformeImagen("poobMen", 30, 30);
		poobMenRD = transformeImagen("poobMenRD", 30, 30);
		poobMenLeft = transformeImagen("poobMenLeft", 30, 30);
		poobMenUp = transformeImagen("poobMenUp", 30, 30);
		poobMenUPL = transformeImagen("poobMenUPL", 30, 30);
		poobMenLD = transformeImagen("poobMenLD", 30, 30);
		
		poobMen2 = transformeImagen("poobMen2", 30, 30);
		poobMen2R = transformeImagen("poobMen2R", 30, 30);
		poobMenD = transformeImagen("poobMenD", 30, 30);
		poobMenU = transformeImagen("poobMenU", 30, 30);
	}
	
	private ImageIcon transformeImagen(String nombre, int sizeX, int sizeY){
		iconTemp = new ImageIcon("./resources/images/"+nombre+".png");
		Image imageTemp = iconTemp.getImage(); // transform image icon
		Image newTemp = imageTemp.getScaledInstance(sizeX, sizeY,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		iconTemp = new ImageIcon(newTemp); // transform it back
		return iconTemp;
	}
	
	private void panelInicial(){
		poobInicial = new JPanel();
		poobInicial.setBackground(Color.black);
		poobInicial.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5),new TitledBorder("")));
		poobInicial.setLayout(new BorderLayout());
		JPanel datos = new JPanel();
		datos.setBorder(new EmptyBorder(30,30,30,30));
		datos.setLayout(new GridLayout(2,2,5,5));	
		datos.setBackground(Color.black);
		jugadores = new JLabel("Jugadores  : ");
		jugadores.setForeground(Color.white);
		jugadores.setFont(new java.awt.Font("Tahoma", 0, 36));
		dificultad = new JLabel("Dificultad   : ");
		dificultad.setForeground(Color.white);
		dificultad.setFont(new java.awt.Font("Tahoma", 0, 36));
		noJugadores = new JComboBox();
		noJugadores.addItem(1);
		noJugadores.addItem(2);
		noJugadores.setEditable(false);
		laDificultad = new JComboBox();
		laDificultad.addItem("Facil");
		laDificultad.addItem("Normal");
		laDificultad.addItem("Dificil");
		laDificultad.setEditable(false);		
		datos.add(jugadores);
		datos.add(noJugadores);
		datos.add(dificultad);
		datos.add(laDificultad);
		nuevoJuego = new JButton("Jugar");
		nuevoJuego.setBackground(Color.black);
		nuevoJuego.setForeground(Color.white);
		nuevoJuego.setForeground(Color.white);
		nuevoJuego.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						juegoNuevo();
					}
				});			
		poobInicial.add(datos,BorderLayout.CENTER);
		poobInicial.add(nuevoJuego,BorderLayout.SOUTH);		
	}
	private void prepareAciones (){		
		
		abrir.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						try{
							abrir(players,nivel);
						}catch(PoobManExecption k){
							System.out.println("exception en abrir");
						}
					}
				});
		
		cerrar.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						cerrar();
					}
				});
		
		nuevo.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						nuevoReset();
					}
				});
		
		jugar.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						nuevoReset();
					}
				});
		
		guardar.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						try{
							guardar();
						}catch(PoobManExecption e1){
							
						}
					}
				});
		exportar.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						try{
							exportar();
						}catch(IOException e1){
							
						} catch (PoobManExecption e1) {
							
							
						}
					}
				});	
		
		if(players ==1){
			poobLaberinto.addKeyListener( new KeyAdapter(){
				public void keyPressed(KeyEvent e) {
					System.out.println(".....");
					mover(e);
				}
			});
			poobLaberinto.setFocusable(true);
		}/*else if(players == 2){
			poobLaberinto.addKeyListener( new KeyAdapter(){
				public void keyPressed(KeyEvent e) {					
					mover2(e);
				}
			});	
			poobLaberinto.setFocusable(true);
			poobLaberinto.addKeyListener( new KeyAdapter(){
				public void keyPressed(KeyEvent e) {					
					mover(e);
				}
			});	
			poobLaberinto.setFocusable(true);
		}*/
		
		
		
		
		addWindowListener(new WindowAdapter(){            
            public void windowClosing(WindowEvent e){
                cerrar();                
            }
        });
			
		
		
	}
	private void changeSize(){		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		double w = screen.getWidth();
		double h = screen.getHeight();
		int w1 = (int) w/2;
		int h1 = (int) h-40;
		setSize(w1,h1);
	}
	
	private void crearMenu(){
		setLayout(null);
		menuBar = new JMenuBar();		
        setJMenuBar(menuBar);
        menu = new JMenu("Archivo");
        menuBar.add(menu);
        guardar = new JMenuItem("guardar");         
        menu.add(guardar);
        abrir = new JMenuItem("abrir");        
        menu.add(abrir);
        importar = new JMenuItem("importar");        
        menu.add(importar);
        exportar = new JMenuItem("exportar");
        menu.add(exportar);
        nuevo = new JMenuItem("nuevo");        
        menu.add(nuevo);        	
        cerrar = new JMenuItem("cerrar");        
        menu.add(cerrar);
        
	}
	
	private void cerrar(){			
		int confirm = JOptionPane.showConfirmDialog(null, " desea cerrar?");
		if(confirm == JOptionPane.YES_OPTION){					
			System.exit(0);
		};		
		setVisible(true);
	}
	
	private void elmentosTablero(){
		tablero = new JPanel();
		tablero.setBackground(Color.black);	
		iconTemp = new ImageIcon("./resources/images/fondo.png");
		
		Image imageTemp = iconTemp.getImage(); // transform image icon
		Image newTemp = imageTemp.getScaledInstance(650, 60,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		iconTemp = new ImageIcon(newTemp);		
		
		JLabel fondos = new JLabel();
		fondos.setIcon(iconTemp);	
		tablero.add(fondos);
		
		this.setLayout(new BorderLayout());
		
		this.getContentPane().add(tablero,BorderLayout.NORTH);
	}
	
	private void elementosPanelMedio(){		
		poobLaberinto = new JPanel();
		poobLaberinto.setBackground(Color.black);
		poobLaberinto.setLayout(new GridLayout(20,20,0,0));			
		for(int i =0;i<laberinto.length;i++){
			for(int j = 0 ; j<laberinto[0].length; j++){
				if(laberinto[j][i] == 0){
					JLabel vac = new JLabel();						
					vac.setIcon(vacio);
					poobLaberinto.add(vac);
				}else if (laberinto[j][i] == 1){
					JLabel wal = new JLabel();
					wal.setIcon(wall);						
					poobLaberinto.add(wal);
				}else if (laberinto[j][i]==2){
					JLabel dot = new JLabel();
					dot.setIcon(poobDot);						
					poobLaberinto.add(dot);
				}else if (laberinto[j][i]==3){
					JLabel fun = new JLabel();
					fun.setIcon(poobFun);						
					poobLaberinto.add(fun);
				}else if (laberinto[j][i]==4){
					JLabel cannibal = new JLabel();
					cannibal.setIcon(poobCannibal);						
					poobLaberinto.add(cannibal);
				}else if (laberinto[j][i]==5){
					JLabel moon = new JLabel();
					moon.setIcon(poobMoon);						
					poobLaberinto.add(moon);
				}else if (laberinto[j][i]==6){
					JLabel sun = new JLabel();
					sun.setIcon(poobSun);						
					poobLaberinto.add(sun);
				}else if (laberinto[j][i]==7){
					ghostPr = new JLabel();
					ghostPr.setIcon(preparado);						
					poobLaberinto.add(ghostPr);
				}else if (laberinto[j][i]==8){
					ghostDe = new JLabel();
					ghostDe.setIcon(detective);						
					poobLaberinto.add(ghostDe);
				}else if (laberinto[j][i]==9){
					ghostPa = new JLabel();
					ghostPa.setIcon(patrullero);
					poobLaberinto.add(ghostPa);
				}else if (laberinto[j][i]==10){
					ghostTo = new JLabel();
					ghostTo.setIcon(torpe);
					poobLaberinto.add(ghostTo);
				}else if (laberinto[j][i]==11){
					pacMan = new JLabel();
					pacMan.setIcon(poobMen);
					poobLaberinto.add(pacMan);
				}else if (laberinto[j][i]==12){
					poobMen3 = new JLabel();
					poobMen3.setIcon(poobMen2);
					poobLaberinto.add(poobMen3);
				}
			}
		}
		this.getContentPane().add(poobLaberinto,BorderLayout.CENTER);		
	}
	
	
	
	private void elementosPanelBajo(){		
		juego = new JPanel();
		TitledBorder titulo = new TitledBorder("Opciones");
		titulo.setTitleColor(Color.white);
		juego.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5),titulo));
		juego.setLayout(new GridLayout(2,2));
		juego.setBackground(Color.black);		
		jugar = new JButton("Jugar");
		jugar.setBackground(Color.black);
		jugar.setForeground(Color.white);
		salir = new JButton("Salir");
		salir.setBackground(Color.black);
		salir.setForeground(Color.white);
		salir.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						cerrar();
					}
				});	
		puntaje = new JLabel("Puntaje : "+aplicacion.getPuntaje());
		puntaje.setForeground(Color.white);
		vidas = new JLabel("Vidas : "+aplicacion.getVidas());
		vidas.setForeground(Color.white);
		juego.add(puntaje);
		juego.add(vidas);
		juego.add(jugar);		
		juego.add(salir);
		this.getContentPane().add(juego,BorderLayout.SOUTH);	
		
	}
	private void refresh(){	
		poobLaberinto.removeAll();
		elementosPanelMedio();
		poobLaberinto.updateUI();		
		juego.removeAll();
		elementosPanelBajo();
		juego.updateUI();	
	}
	
	private void mover(KeyEvent e){
		if("Up" == KeyEvent.getKeyText(e.getKeyCode())){
			if(aplicacion.up(posX,posY)){
				laberinto = aplicacion.demeLaberinto();
				refresh();
				posY -= 1;
				if(lastMove == "R"){
					pacMan.setIcon(poobMenUp);
				}else{
					pacMan.setIcon(poobMenUPL);
				}
				lastMove = "U";				
			}			
		}else if("Down" == KeyEvent.getKeyText(e.getKeyCode())){
			if(aplicacion.down(posX,posY)){
				laberinto = aplicacion.demeLaberinto();
				refresh();
				posY+=1;
				if(lastMove == "R"){
					pacMan.setIcon(poobMenRD);
				}else{
					pacMan.setIcon(poobMenLD);
				}
				lastMove = "D";
			}		
		}else if("Right" == KeyEvent.getKeyText(e.getKeyCode())){
			if(aplicacion.rigth(posX,posY)){
				laberinto = aplicacion.demeLaberinto();
				refresh();
				posX+=1;
				lastMove = "R";
			}
		}else if("Left" == KeyEvent.getKeyText(e.getKeyCode())){
			if(aplicacion.left(posX,posY)){
				laberinto = aplicacion.demeLaberinto();
				refresh();
				posX-=1;
				pacMan.setIcon(poobMenLeft);
				lastMove = "L";
			}		
		}
		posX = aplicacion.pacManGetX();
		posY = aplicacion.pacManGetY();
		if(aplicacion.estadoGhost()){			
			ghostPa.setIcon(ghostComible);
			ghostTo.setIcon(ghostComible);
			ghostDe.setIcon(ghostComible);
			ghostPr.setIcon(ghostComible);		
		}	
		if (aplicacion.terminar()){
			stopGame();
		}
	}
	
	private void guardar() throws PoobManExecption{
		aplicacion.guarde();
	}
	private void exportar() throws PoobManExecption, IOException{
		aplicacion.exporte();
	}
	private void abrir(int players2, String nivel2) throws PoobManExecption {
		try{
			JFileChooser file = new JFileChooser();
			file.showOpenDialog(null);
			File abra=file.getSelectedFile();
		
			if (abra!=null){
				Tablero.nuevoTablero(Archivo.abra(abra));
				aplicacion = Tablero.demeTablero(players, nivel);
				laberinto = aplicacion.demeLaberinto();
			}
		}catch(PoobManExecption e){
			
		}

	}
	
	private void juegoNuevo(){
		nivel = (String) laDificultad.getSelectedItem();
		players = (Integer) noJugadores.getSelectedIndex()+1;		
		aplicacion = Tablero.demeTablero(players,nivel);
		refresh();
		prepareAciones();
		this.setVisible(true);
		dato.setVisible(false);
	}
	
	private void crearDialogo(){
		dato = new JDialog();
		dato.setBounds(500, 100, 550, 250);
		panelInicial();
		dato.addWindowListener(new WindowAdapter(){            
            public void windowClosing(WindowEvent e){
                cerrar();                
            }
        });
		dato.setTitle(" Poob Man ");
		dato.add(poobInicial);
		dato.setVisible(true);		
		
	}
	/*
	private void mover2(KeyEvent e){
		System.out.println(aplicacion.up2(posX2,posY2));
		System.out.println(aplicacion.down2(posX2,posY2));
		System.out.println(aplicacion.left2(posX2,posY2));
		System.out.println(aplicacion.rigth2(posX2,posY2));
		
		if("W" == KeyEvent.getKeyText(e.getKeyCode())){			
			if(aplicacion.up2(posX2,posY2)){
				laberinto = aplicacion.demeLaberinto();
				refresh();
				posY2 -= 1;
				poobMen3.setIcon(poobMenU);
			}
		
				
		}else if("S" == KeyEvent.getKeyText(e.getKeyCode())){
			if(aplicacion.down2(posX2,posY2)){
				laberinto = aplicacion.demeLaberinto();
				refresh();
				posY2+=1;
			}
		}else if("D" == KeyEvent.getKeyText(e.getKeyCode())){
			if(aplicacion.rigth2(posX2,posY2)){
				laberinto = aplicacion.demeLaberinto();
				refresh();
				posX2+=1;
				poobMen3.setIcon(poobMen2R);
			}
		}else if("A" == KeyEvent.getKeyText(e.getKeyCode())){
			if(aplicacion.left2(posX2,posY2)){
				laberinto = aplicacion.demeLaberinto();
				refresh();
				posX2-=1;
			}		
		}
		posX2 = aplicacion.pacManGetX2();
		posY2 = aplicacion.pacManGetY2();
		if(aplicacion.estadoGhost()){			
			ghostPa.setIcon(ghostComible);
			ghostTo.setIcon(ghostComible);
			ghostDe.setIcon(ghostComible);
			ghostPr.setIcon(ghostComible);		
		}
		
	}
	*/
	
	private void nuevoReset(){
		poobLaberinto.removeAll();
		this.setVisible(false);
		prepareElementos();
	}
	
	private void stopGame(){
		JOptionPane.showMessageDialog(null, "Juego Terminado "+" Puntaje Total : "+aplicacion.getPuntaje() );
	}
}

