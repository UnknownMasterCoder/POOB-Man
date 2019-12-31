package aplicacion;

import java.util.ArrayList;
import java.util.Random;

public class Detective extends Ghost{
	
	private Random rnd;
	
	public Detective(int x,int y){
		posX = x;
		posY = y;
		estado = 1;
		puntaje = 30;
		identifier = 8;
	}	
	
	public int[] mover(){

		int [] posicion = {0,0};
		posicion = moverCasilla(posX, posY, 1);
		return posicion;
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
		if ((Tablero.elementos[x][y].getEstado() == 0 || Tablero.elementos[x][y].toString().equals("Persona")) && !Tablero.elementos[x][y].toString().equals("Wall") ){
			ok =  true;
		}
		return ok;
	}
	
	
	
	
	public String toString(){
		return "Detective";
	}
}
