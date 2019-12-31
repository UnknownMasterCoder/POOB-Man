package aplicacion;

import java.util.ArrayList;

public class PoobMoon extends Elemento{
	
	public PoobMoon(int x,int y){
		posX = x;
		posY = y;
		estado = 0;
		puntaje = 15;
		identifier = 5;
	}
	
	public int[] mover(){
		int [] posicion = {0,0};
		return posicion;
	}
	
	
	
	public String toString(){
		return "PoobMoon";
	}
}
