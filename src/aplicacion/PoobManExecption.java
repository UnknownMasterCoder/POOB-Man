package aplicacion;

public class PoobManExecption extends Exception {
	
	public static final String NO_GUARDADO="Juego No Guardado";
	public static final String NO_HAYARCHIVO="Archivo no seleccionado";
    public PoobManExecption(String mensaje){
       super(mensaje);
    }
}
