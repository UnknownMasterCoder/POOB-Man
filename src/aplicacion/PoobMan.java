package aplicacion;

public abstract class PoobMan extends Elemento{
	
	protected int vidas = 3;
	protected int posXI;
	protected int posYI;
	
	public abstract String toString();
	
	public void serComido(int x, int y ){
		posX = posXI;
		posY = posYI;
	}
	
	/*
	public int getPosX(){
		return posX;
	}
	
	public int getPosY(){
		return posY;
	}	
	
	public int getPuntaje(){
		return puntaje;
	}
	*/
	
	public int getVidas(){
		return vidas;
	}
	
	public void perdiVida(){
		vidas--;
	}
	
	public void demePuntos(int valor){
		puntaje += valor;
	}
	
	
	    
	
}
