package aplicacion;

public abstract class Awards extends Elemento {		
	
	public abstract String toString();	
	
	public void serComido(int x, int y){
		Tablero.existentes[posX][posY]= new Vacio (posX,posY);
	}
	
	public int[] mover(){
		int [] posicion = {0,0};
		return posicion;
	}
}
