package aplicacion;

import java.util.ArrayList;


public class PoobDot extends Elemento{
	
	public PoobDot(int x,int y){
		posX = x;
		posY = y;
		estado = 0;
		puntaje = 10;
		identifier = 2;
	}
	
	
	public int[] mover(){
		int [] posicion = {0,0};
		return posicion;
	}

    
	public String toString(){
		return "PoobDot";
	}
}
