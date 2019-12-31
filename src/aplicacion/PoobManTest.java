package aplicacion;
import java.io.IOException;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PoobManTest {	
	
	public PoobManTest(){
		
	}

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void deberiaNoDejarMoverPacMan(){
		Tablero poobMen = Tablero.demeTablero(1, "facil");
		assertFalse("deberia ser falso mover arriba",poobMen.up(1, 1));
		assertFalse("deberia ser falso mover izquierda",poobMen.up(1, 1));
	}
	
	@Test
	public void deberiaTenerAlmenosUnaSalidaElPacMan(){
		Tablero poobMen = Tablero.demeTablero(1, "facil");
		if(poobMen.rigth(1, 1) || poobMen.down(1, 1)){
			assertTrue("tiene una salida",true);
		}
	}
	
	@Test
	public void comerUnPoobFunCambiaEstadoDeLosGhost(){
		Tablero poobMen = Tablero.demeTablero(1, "facil");
		assertFalse("si el estado es 1 retorna false",poobMen.estadoGhost());
		Tablero.elementos[2][9].serComido(2,2);			
		assertTrue("si el estado es 0 retorna true",poobMen.estadoGhost());
	}
	
	@Test
	public void cuandoSeComeUnGhostDeberiaCambiarASuPosicionInicial(){
		Tablero poobMen = Tablero.demeTablero(1, "facil");
		Elemento [] ghost = poobMen.getGhosts();
		ghost[0].serComido(0, 0);
		assertEquals("deberia de haber cambiado al posicion en X",10,ghost[0].getPosX());
		assertEquals("deberia de haber cambiado al posicion en Y",10,ghost[0].getPosY());
	}
	
	@Test
	public void deberiaGanarPuntosAlComerDots(){
		Tablero poobMen = Tablero.demeTablero(1, "facil");
		int puntaje = poobMen.getPuntaje();
		if(poobMen.rigth(1, 1)){
			poobMen.down(2, 1);
		}else{
			poobMen.down(1, 1);
		}
		System.out.println(puntaje+" equals "+ poobMen.getPuntaje());
		assertNotSame("deberia valor inical dferente al final", puntaje, poobMen.getPuntaje());
		
	}
	
	@Test
	public void deberiaNoSumarPuntosUnPoobFun(){
		Tablero poobMen = Tablero.demeTablero(1, "facil");
		Tablero.elementos[2][9].serComido(2,2);
		System.out.println(poobMen.getPuntaje());
		assertEquals("el puntaje inicial debria conservarce",10,poobMen.getPuntaje());
	}
	
	
	
	
	

	@After
	public void tearDown() throws Exception {
	}

	
}
