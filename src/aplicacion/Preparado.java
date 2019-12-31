package aplicacion;

import java.util.ArrayList;
import java.util.Random;


public class Preparado extends Ghost{
	
	private Random rnd;
	private String masPoblada = "NO";
	
	
	public Preparado(int x,int y){
		posX = x;
		posY = y;
		estado = 1;
		puntaje = 30;
		identifier = 7;
	}
	
	
	
	public int [] moverCasilla(int posX, int posY, int i){
		rnd = new Random();
		int x = posX;
		int y = posY;
		int dir = rnd.nextInt(4);
		
		boolean ok = false;
		int xNew = x;
		int yNew = y;
		int [] posiciones = {xNew,yNew};
		while ( !ok ){
			dir = rnd.nextInt(4);
			if ( dir == 0 ){
				yNew = y-1;
				ok = moverFant(x, y-1, i);
			}else if ( dir == 1 ){
				xNew = x+1;
				ok = moverFant(x+1, y, i);
			}else if ( dir == 2 ){
				yNew = y+1;
				ok = moverFant(x, y+1, i);
			}else if ( dir == 3 ){
				xNew = x-1;
				ok = moverFant(x-1, y, i);
			}
		}
		posiciones[0]= xNew;
		posiciones[1]= yNew;
		return posiciones;
	}
	
	
	public boolean moverFant(int x, int y, int i){
		
		boolean ok = false;
		if (Tablero.elementos[x][y].getEstado() == 0  && !Tablero.elementos[x][y].toString().equals("Wall")){
			//objTemp1[i] = elementos[x][y];			
			//ghosts[i].changePos(x, y);
			//elementos[x][y] = ghosts[i];
			ok =  true;
		}
		return ok;
	}
	
	
	
	
	public int[] mover(){
		rnd = new Random();
		int [] posicion = {0,0};
		String zona = zonaMasPuntos();
		boolean ok = false;
		
		if(zona.equals(masPoblada)){
			posicion = moverCasilla(posX, posY, 0);
		
		}else{		
			while(!ok){
			
				if(zona.equals("NO")){
					int newX = rnd.nextInt(9)+1;
					int newY = rnd.nextInt(9)+1;
					
					if (Tablero.elementos[newX][newY].getEstado() == 0){
						posicion[0] = newX;
						posicion[1] = newY;
						ok = true;
					}
				}else if(zona.equals("NE")){
					int newX = rnd.nextInt(9)+10;
					int newY = rnd.nextInt(9)+1;
					
					if (Tablero.elementos[newX][newY].getEstado() == 0){
						posicion[0] = newX;
						posicion[1] = newY;
						ok = true;
					}
				}else if(zona.equals("SO")){
					int newX = rnd.nextInt(9)+1;
					int newY = rnd.nextInt(9)+10;
					
					if (Tablero.elementos[newX][newY].getEstado() == 0){
						posicion[0] = newX;
						posicion[1] = newY;
						ok = true;
					}
				}else if(zona.equals("SE")){
					int newX = rnd.nextInt(9)+10;
					int newY = rnd.nextInt(9)+10;
					if (Tablero.elementos[newX][newY].getEstado() == 0){
						posicion[0] = newX;
						posicion[1] = newY;
						ok = true;
					}
				}
			}
		}
		masPoblada = zona;
		return posicion;
	}
	
	private String zonaMasPuntos(){
		String zonaMayor = null;
		String[] cuadrantes = {"NO","NE","SO","SE"};
		int [] puntos = {0,0,0,0};
		for(int i = 1; i<19; i++){
			for(int j = 1; j<19; j++){
				if(Tablero.elementos[i][j].toString().equals("PoobDot")){
					if( i<10 && j<10 ){
						puntos[0]++;
					}else if( i>9 && j<10 ){
						puntos[1]++;
					}else if( i<10 && j>9 ){
						puntos[2]++;
					}else if( i>9 && j>9 ){
						puntos[3]++;
					}
				}
			}
		}		
		int max = Math.max( puntos[0], Math.max( puntos[1], Math.max( puntos[2], puntos[3] )));
		
		if (max == puntos[0]){
			zonaMayor = cuadrantes[0];
		}else if (max == puntos[1]){
			zonaMayor = cuadrantes[1];
		}else if (max == puntos[2]){
			zonaMayor = cuadrantes[2];
		}else if (max == puntos[3]){
			zonaMayor = cuadrantes[3];
		}
		return zonaMayor; 
	}
	
	
	
	public String toString(){
		return "Preparado";
	}
}
