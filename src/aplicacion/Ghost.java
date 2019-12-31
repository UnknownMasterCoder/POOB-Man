package aplicacion;

public abstract class Ghost extends Elemento {
	
	protected int posInicial = 10;
	public abstract String toString();	
	
	public int[] mover(){
		int [] posicion = {0,0};
		return posicion;
	}
	
	public void serComido(int x , int y){
		posX = posInicial;
		posY = posInicial;
	}
}
