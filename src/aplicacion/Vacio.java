package aplicacion;

import java.util.ArrayList;

public class Vacio extends Elemento{ 
	
	public Vacio(int x,int y){
		posX = x;
		
		posY = y;
		estado = 0;
		puntaje = 0;
		identifier = 0;
		
	}

	public String toString(){
		return "Vacio";
	}
	
	public void serComido(int x , int y){
		posX = x;
		posY = y;
	}
	
	public int[] mover(){
		int [] posicion = {0,0};
		return posicion;
	}
		

}
