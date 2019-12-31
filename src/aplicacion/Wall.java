package aplicacion;

import java.util.ArrayList;


public class Wall extends Elemento{ 

   
	public Wall(int x,int y){
		posX = x;
		posY = y;
		estado = 1;
		puntaje = 0;
		identifier = 1;
	}
	
	public int[] mover(){
		int [] posicion = {0,0};
		return posicion;
	}
	
	public String toString(){
		return "Wall";
	}
	
	

}