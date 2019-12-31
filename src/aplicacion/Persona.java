package aplicacion;



public class Persona extends PoobMan{
	
	public Persona(int X, int Y){
		posX = X;
		posY = Y;	
		posXI = X;
		posYI = Y;
		estado = 1;
		identifier = 11;
	}  
	
	public int[] mover(){
		int [] posicion = {0,0};
		return posicion;
	}
	
	public String toString(){
		return "Persona";
	}
}
