package aplicacion;

import java.util.ArrayList;

public class PoobCannibal extends Elemento{
	
	public PoobCannibal(int x,int y){
		posX = x;
		posY = y;
		estado = 0;
		puntaje = 0;
		identifier = 4;
	}
	
	public int[] mover(){
		int [] posicion = {0,0};
		return posicion;
	}
	
	
	
	public String toString(){
		return "Cannibal";
	}
}
