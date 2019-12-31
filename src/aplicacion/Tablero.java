package aplicacion;

import java.util.*;

import javax.swing.*;

import java.io.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;


public class Tablero {	
	
	public static Elemento [][] existentes = new Elemento [20][20];
	private Random rnd,posi;
	private int randomI;
	private int randomJ;	
	private  int [][] tablero;
	public static Elemento [][] elementos;
	private Elemento personaAnt = new Vacio(1,1);
	private int [] objTemp = {0,0,0,0};
	private Elemento [] objTemp1 = {new Vacio(7,9),new Vacio(8,9),new Vacio(7,10),new Vacio(7,11)};
	private Persona persona;	
	public static Elemento [] ghosts;
	//public ArrayList<Elemento> awards = new ArrayList<Elemento>();		
	private static Tablero juego = null;
	private int cannibalX = 6, cannibalY = 12;
	private Elemento cannibalAnt = new PoobDot(cannibalX, cannibalY);
	private int moonX = 12, moonY = 6;
	private Elemento moonAnt = new PoobDot(moonX, moonY);
	private int sunX = 9, sunY = 13;
	private Elemento sunAnt = new PoobDot(sunX, sunY);
	public static int movCannibal = 0,movMoon = 0,movFun = 0;
	private static int players;
	private static String nivel;
	
	private Tablero(int jugadores, String dificultad){
		players = jugadores;
		nivel = dificultad;
		elementos = new Elemento[20][20];
		tablero = new int [20][20];	
		ghosts = new Elemento[4];
		crearLaberinto(dificultad);
		llenarTabInt();
	}
	
	
	public static void nuevoTablero(Tablero t){
		juego = t;
		
	}
	
	public  int [][] demeLaberinto(){
		return tablero;
	}
	
	public static Tablero demeTablero(int x,String y){
		if(juego == null || x != players || nivel != y){
			juego = new Tablero(x,y);
		}
		return juego;
	}
		
	private void crearLaberinto(String dificultad){
		rnd = new Random();
		
		for (int i=0; i<20;i++){
			for (int j=0; j<20; j++){
				if ((i==0 || j==0) || (i==19 || j==19)){
					elementos[i][j] = new Wall(i,j);
				}else{
					elementos[i][j] = new PoobDot(i,j);					
				}
				
			}
		}		
		
		elementos[7][9] = new Wall(7,9);
		elementos[8][9] = new Wall(8,9);
		elementos[7][10] = new Wall(7,10);
		elementos[7][11] = new Wall(7,11);
		elementos[8][11] = new Wall(8,11);
		elementos[9][11] = new Wall(9,11);
		elementos[10][11] = new Wall(10,11);
		elementos[11][11] = new Wall(11,11);
		elementos[11][10] = new Wall(11,10);
		elementos[11][9] = new Wall(11,9);
		elementos[10][9] = new Wall(10,9);		
		
		elementos[8][10] = new Vacio(7,9);
		elementos[9][10] = new Vacio(8,9);
		elementos[10][10] = new Vacio(7,10);
		elementos[9][9] = new Vacio(7,11);
		elementos[9][8] = new Vacio(7,9);
		
		int iteraciones = 0;
		if (dificultad.equals("Facil")){
			iteraciones = 1;
		}else if (dificultad.equals("Normal")){
			iteraciones = 50;
		}else if (dificultad.equals("Dificil")){
			iteraciones = 100;
		}
		
		
		for (int i = 0; i<iteraciones; i++){
			while (conexo(1,1)){	
				randomI = rnd.nextInt(20);
				randomJ = rnd.nextInt(20);
				
				if(!elementos[randomI][randomJ].toString().equals("Vacio")){
					elementos[randomI][randomJ] = new Wall(randomI, randomJ);				
				}else{
					randomI = rnd.nextInt(20);
					randomJ = rnd.nextInt(20);
				}			
			}
			elementos[randomI][randomJ] = new PoobDot(randomI, randomJ);			
		}
		
		
		
		elementos[2][9] = new PoobFun(2,9);
		elementos[17][9] = new PoobFun(17,9);
		elementos[9][2] = new PoobFun(9,2);
		elementos[9][17] = new PoobFun(9,17);
	
		elementos[cannibalX][cannibalY] = new PoobCannibal(cannibalX, cannibalY);
		elementos[moonX][moonY] = new PoobMoon(moonX, moonY);
		elementos[sunX][sunY] = new PoobSun(sunX, sunY);
		
		ghosts[0] = new Preparado(3,3);
		elementos[3][3] = ghosts[0];
		ghosts[1] = new Detective(7,7);
		elementos[7][7] = ghosts[1];
		ghosts[2] = new Patrullero(12,12);
		elementos[12][12] = ghosts[2];
		ghosts[3] = new Torpe(16,16);
		elementos[16][16] = ghosts[3];
		
		persona = new Persona(1,1);	
		elementos[1][1] = new Persona(1,1);
	}
	
	
	private boolean conexo(int i, int j){
		Stack<Integer> pos_i = new Stack<Integer>();
		Stack<Integer> pos_j = new Stack<Integer>();
		pos_i.add(i);
		boolean[][] visitados = new boolean[30][30];
		int[] dx = {0,0,1,-1};
		int[] dy = {1,-1,0,0};
		pos_j.add(j);
		visitados[i][j] = true;
		while(!pos_i.isEmpty()){
			int itemp = pos_i.pop();
			int jtemp = pos_j.pop();
			visitados[itemp][jtemp] = true;
			for (int k = 0; k < dy.length; k++) {
				int dx_temp = dx[k]+itemp;
				int dy_temp = dy[k]+jtemp;
				if(dx_temp<30 && dy_temp<30 && dx_temp>=0 && dy_temp >= 0 && elementos[dx_temp][dy_temp].toString().equals("PoobDot") && !visitados[dx_temp][dy_temp]){
					pos_i.add(dx_temp);
					pos_j.add(dy_temp);									
				}
			}
		}
		for (int k = 0; k < 20; k++) {
			for (int k2 = 0; k2 < 20; k2++) {
				if(elementos[k][k2].toString().equals("PoobDot") && !visitados[k][k2]){	
					return false;
				}
			}
		}		
        return true;
    }
	
	public boolean estadoGhost(){
		boolean resp = false;
		if(ghosts[0].getEstado() == 0){
			resp = true;
		}
		return resp;
	}
	
	private void llenarTabInt(){
		for(int i =0;i<20;i++){
			for(int j = 0 ; j<20; j++){
				tablero[i][j] =  elementos[i][j].getIdentifier();
				existentes[i][j] = elementos[i][j];
			}
		} 
	}

    
	public boolean rigth(int x, int y){
    	boolean resp = false;    	
    	if(elementos[x+1][y].puedoMover()){
    		tablero[x][y] = 0;
    		tablero[x+1][y] = 11;
    		persona.changePos(x+1, y);
    		persona.demePuntos(elementos[x+1][y].getPuntaje());
    		elementos[x+1][y].serComido(x, y);
    		elementos[x][y] = new Vacio(x,y);
    		elementos[x+1][y] = new Persona(x+1, y);
    		moveGhosts();
    		resp = actualiceDots();
    		
    	}
		return resp;
	}
	
	public boolean left(int x,int y){
		boolean resp = false;	
		
    	if(elementos[x-1][y].puedoMover()){
    		persona.changePos(x-1, y);
    		persona.demePuntos(elementos[x-1][y].getPuntaje());
    		elementos[x-1][y].serComido(x,y);
    		elementos[x][y] = new Vacio(x,y);
    		elementos[x-1][y] = new Persona(x-1, y);
    		llenarTabInt();
    		moveGhosts();
    		resp = actualiceDots();
    		
    	}
		return resp;
	}
	
	public boolean up(int x,int y){
		boolean resp = false;		
    	if(elementos[x][y-1].puedoMover()){
    		tablero[x][y] = 0;
    		tablero[x][y-1] = 11;
    		persona.changePos(x, y-1);
    		persona.demePuntos(elementos[x][y-1].getPuntaje());
    		elementos[x][y-1].serComido(x,y);
    		elementos[x][y] = new Vacio(x,y);
    		elementos[x][y-1] = new Persona(x, y-1);
    		llenarTabInt();
    		moveGhosts();
    		resp = actualiceDots();  
    		
    	}
		return resp;
	}
	
	public boolean down(int x,int y){
		boolean resp = false;		
    	if(elementos[x][y+1].puedoMover()){
    		tablero[x][y] = 0;
    		tablero[x][y+1] = 11;
    		persona.changePos(x, y+1);
    		persona.demePuntos(elementos[x][y+1].getPuntaje());
    		elementos[x][y+1].serComido(x,y);
    		elementos[x][y] = new Vacio(x,y);
    		elementos[x][y+1] = new Persona(x, y+1);
    		llenarTabInt();
    		moveGhosts();
    		resp = actualiceDots();   
    		
    	}
		return resp;
	}
	
	public int getPuntaje(){
		return persona.getPuntaje();
	}
	
	/**
	 * Agrega los 4 tipos de Awards al tablero
	 */
	private void agregarAwards(){
		addCannibal();
		addMoon();
		addSun();
		llenarTabInt();
	}
	
	
	/**
	 * Agrega cada Cannibal
	 */
	private void addSun(){
		boolean ok = false;		
		while(ok != true){			
			elementos[sunX][sunY] = sunAnt;			
			rnd = new Random();		
			int newX = rnd.nextInt(18)+1;
			int newY = rnd.nextInt(18)+1;			
			if((elementos[newX][newY].getEstado() == 0) && (!elementos[newX][newY].toString().equals("Persona") || !elementos[newX][newY].toString().equals("PoobFun"))){				
				sunAnt = elementos[newX][newY];
				elementos[newX][newY] = new PoobSun(newX, newY);				
				sunX = newX;
				sunY = newY;				
				ok = true;
			}
		}
		
	}
	
	
	/**
	 * Agrega cada Cannibal
	 */
	private void addMoon(){
		boolean ok = false;		
		while(ok != true){			
			elementos[moonX][moonY] = moonAnt;			
			rnd = new Random();		
			int newX = rnd.nextInt(18)+1;
			int newY = rnd.nextInt(18)+1;			
			if((elementos[newX][newY].getEstado() == 0) && (!elementos[newX][newY].toString().equals("Persona") || !elementos[newX][newY].toString().equals("PoobFun"))){				
				moonAnt = elementos[newX][newY];
				elementos[newX][newY] = new PoobMoon(newX, newY);				
				moonX = newX;
				moonY = newY;				
				ok = true;
			}
		}
	}
	
	/**
	 * Agrega cada Cannibal
	 */
	private void addCannibal(){
		boolean ok = false;		
		while(ok != true){			
			elementos[cannibalX][cannibalY] = cannibalAnt;			
			rnd = new Random();		
			int newX = rnd.nextInt(18)+1;
			int newY = rnd.nextInt(18)+1;			
			if((elementos[newX][newY].getEstado() == 0) && (!elementos[newX][newY].toString().equals("Persona") || !elementos[newX][newY].toString().equals("PoobFun"))){				
				cannibalAnt = elementos[newX][newY];
				elementos[newX][newY] = new PoobCannibal(newX, newY);				
				cannibalX = newX;
				cannibalY = newY;				
				ok = true;
			}
		}
	}
	
	private boolean actualiceDots(){
		if(movCannibal == 11){
			agregarAwards();			
			movCannibal = 0;
		}
		if(movFun == 15){
			actualiceGhost();
			movFun = 0;
		}
		//crashGhost(persona.getPosX(),persona.getPosX());
		movFun++;
		movCannibal++;
		return true;
	}
	
	private void actualiceGhost(){
		cambieEstado(1);
	}
		
	
	private void moveGhosts(){
		movePreparado();
		moveDetective();
		movePatrullero();
		moveTorpe();
		llenarTabInt();
	}
	
	
	
	private void movePreparado(){
		int [] posiciones = ghosts[0].mover();
		moveGhost(posiciones[0], posiciones[1], 0);
	}
	
	private void moveDetective(){
		int [] posiciones = ghosts[1].mover();
		moveGhost(posiciones[0], posiciones[1], 1);
	}
	
	private void movePatrullero(){
		int [] posiciones = ghosts[2].mover();
		moveGhost(posiciones[0], posiciones[1], 2);
	}
	
	private void moveTorpe(){
		int [] posiciones = ghosts[3].mover();
		moveGhost(posiciones[0], posiciones[1], 3);
	}
	
	
	
	public void moveGhost(int newX, int newY, int i){
		int x = ghosts[i].getPosX();
		int y = ghosts[i].getPosY();
		elementos[x][y] = objTemp1[i];
		if (elementos[newX][newY].getEstado() == 0){
			objTemp1[i] = elementos[newX][newY];			
			ghosts[i].changePos(newX, newY);
			elementos[newX][newY] = ghosts[i];
		}else if (elementos[newX][newY].toString().equals("Persona")){
			elementos[newX][newY] = personaAnt;
			ghosts[i].changePos(newX, newY);
			persona.perdiVida();
			persona.serComido(1, 1);
			elementos[1][1] = new Persona(1,1);
		}
		llenarTabInt();		
	}	
	
	public static void cambieEstado(int x){
		movFun = 0;
		for(int i = 0 ; i<4;i++){
			ghosts[i].actualizarEstado(x);
		}
	}
	
	public Elemento [] getGhosts(){
		return ghosts;
	}
	
	public int getVidas(){
		return persona.getVidas();
	}
	
	private void crashGhost(int x, int y){
		int [] gh = {9,8,7,6};
		for( int i = 0; i<4;i++){
			//System.out.println((x+1)+"---"+y);
			if(tablero[persona.getPosX()+1][persona.getPosY()] == gh[i] && ghosts[i].getEstado() == 1){
				persona.perdiVida();
				tablero[x+1][y] = 11;
				tablero[x][y] = gh[i];
				tablero[1][1] = 3;
				persona.changePos(1, y);
				persona.changePos(x, 1);
				elementos[1][1] = null;
				elementos[x+1][y] = new Vacio(x+1,y);
				elementos[x][y] = ghosts[i];
				
			}
		}	
	}
	
	public void guarde() throws PoobManExecption{
		try{
			JFileChooser file = new JFileChooser();
			file.showSaveDialog(null);
			File guarde=file.getSelectedFile();
		
			if (guarde!=null){
				Archivo.guarde(guarde,juego);
			}
		}catch(IOException e){
			throw new PoobManExecption(PoobManExecption.NO_HAYARCHIVO);
		}
	}
	
	public void exporte() throws PoobManExecption, IOException{
		JFileChooser file = new JFileChooser();
		file.showSaveDialog(null);
		File guarde=file.getSelectedFile();

		if (guarde!=null){
			Archivo.exporte(guarde,juego);
		}
	}
	
	public int pacManGetX(){
		return persona.getPosX();
	}
	
	public int pacManGetY(){
		return persona.getPosY();
	}
	
	private void actualiceElementos(){
		for(int i = 0; i < existentes.length;i++){
			for(int j = 0; j< existentes[0].length;j++){
				elementos[existentes[i][j].getPosX()][existentes[i][j].getPosY()] = existentes[i][j];
			}
		}
	}
	
	public boolean terminar(){
		boolean resp = false;
		int cont = 0;
		for(int i = 0; i< 20; i++){
			for(int j =0; j< 20; j++){
				if(elementos[i][j].getIdentifier() == 2){
					cont ++;
				}
			}
		}
		if(cont == 0 || persona.getVidas() == 0){
			resp = true;
		}
		return resp;
	}
	
	public void imprimeMatr(){
		System.out.println("OBJETOS!!!");
		for(int i=0; i<20; i++){
			for (int j=0; j<20; j++){
				System.out.print(elementos[j][i]+" ");               
			}
			System.out.println();
		}
		System.out.println("\n\n");
		
		System.out.println("INT!!!");
		for(int i=0; i<20; i++){
			for (int j=0; j<20; j++){
				System.out.print(tablero[j][i]+" ");               
			}
			System.out.println();
		}
		System.out.println("\n\n");
		
		System.out.println("ESTADO!!!");
		for(int i=0; i<20; i++){
			for (int j=0; j<20; j++){
				System.out.print(elementos[j][i].getEstado()+" ");                 
			}
			System.out.println();
		}
		System.out.println("\n\n");
	}
	
	
}