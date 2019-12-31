package aplicacion;
import java.io.*;

public class Archivo implements Serializable{
		
	public static void guarde (File f, Tablero t) throws IOException{
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
		System.out.println(t);
		out.writeObject((Object)t);
		out.close();
	}
	
	public static Tablero abra(File f)  throws PoobManExecption{
		Tablero t;
		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
			t = (Tablero)in.readObject();
			Tablero.nuevoTablero(t);
			System.out.println(t);
			in.close();
		}catch(IOException h){
			throw new PoobManExecption(PoobManExecption.NO_GUARDADO);			
		}catch(ClassNotFoundException e){
			throw new PoobManExecption(PoobManExecption.NO_GUARDADO);
		}
		return t;
	}
	
	public static Tablero importe(File f) throws PoobManExecption{	   
		Tablero t;
		try{
			t = Tablero.demeTablero(1,"Facil");
			BufferedReader in = new BufferedReader(new FileReader(f));
			String line = in.readLine();		
			while(line!=null){
				String [] obj = line.split(" ");
				String a = obj[0];
				Object b = a;				
			}
			
		}catch(IOException h){
			throw new PoobManExecption(PoobManExecption.NO_GUARDADO);
		}
		return t;			
	}
	
	public static void exporte(File f, Tablero t) throws PoobManExecption{
	    try{
			
			PrintWriter out = new PrintWriter(new FileWriter(f));
			for (int j = 0; j<t.demeLaberinto().length; j++){				
				for(int i = 0;i<t.demeLaberinto().length;i++){
					if(i<t.demeLaberinto().length-1){
						out.print(t.demeLaberinto()[i][j]+" ");
					}else{
						out.println(t.demeLaberinto()[i][j]);
					}
					
				}
			}
				
					
			out.close();
		}catch(IOException h){
			throw new PoobManExecption(PoobManExecption.NO_GUARDADO);
		}		
	}
	
}

