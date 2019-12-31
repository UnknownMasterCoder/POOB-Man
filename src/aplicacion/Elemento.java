package aplicacion;

import java.util.ArrayList;

public abstract class Elemento {
	
	protected int puntaje = 0;
	protected int posX;
	protected int posY;
	protected int estado;
	protected int identifier;
	
	public abstract int[] mover();
	
	public boolean puedoMover(){
		
		boolean resp = false;
		if(estado == 0){
			resp = true;
		}
		return resp;		
	}
	
	public int getIdentifier(){
		return identifier;
	}
	
	public int getPuntaje(){
		return puntaje;
	}
	
	public int getEstado(){
		return estado;
	}
	
	public int getPosX(){
		return posX;
	}
	
	public int getPosY(){
		return posY;
	}
	
	public void changePos(int x, int y){
		posX = x; 
		posY = y; 
	}
	
	public  void serComido(int x, int y){		
	}
	
	public void actualizarEstado(int newEstado){
		estado = newEstado;
	}
}
