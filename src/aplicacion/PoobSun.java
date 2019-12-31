package aplicacion;

import java.util.ArrayList;

public class PoobSun extends Elemento{
	
	public PoobSun(int x,int y){
		posX = x;
		posY = y;
		estado = 0;
		puntaje = 15;
		identifier = 6;
	}
	
	public int[] mover(){
		int [] posicion = {0,0};
		return posicion;
	}
	
	
	
	public String toString(){
		return "PoobSun";
	}
}
