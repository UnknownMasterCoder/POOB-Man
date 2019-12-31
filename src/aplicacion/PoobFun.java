package aplicacion;

import java.util.ArrayList;

public class PoobFun extends Elemento{
	
	public PoobFun(int x,int y){
		posX = x;
		posY = y;
		estado = 0;
		puntaje = 0;
		identifier = 3;
	}
	
	public int[] mover(){
		int [] posicion = {0,0};
		return posicion;
	}
	
	public void serComido(int x , int y){
		Tablero.cambieEstado(0);
	}
	
	public String toString(){
		return "PoobFun";
	}
}
