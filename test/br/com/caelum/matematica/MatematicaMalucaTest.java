package br.com.caelum.matematica;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.caelum.matematica.MatematicaMaluca;

public class MatematicaMalucaTest {
	
	@Test
	public void testaValorMaiorQueTrinta() {
		MatematicaMaluca mm = new MatematicaMaluca();
		int resultado = mm.contaMaluca(40);
		assertEquals(160, resultado);
	}
	
	@Test
	public void testaValorMaiorQueDezMenorQueTrinta() {
		MatematicaMaluca mm = new MatematicaMaluca();
		int resultado = mm.contaMaluca(20);
		assertEquals(60, resultado);
	}
	
	@Test
	public void testaValorMenorQueDez() {
		MatematicaMaluca mm = new MatematicaMaluca();
		int resultado = mm.contaMaluca(5);
		assertEquals(10, resultado);
	}
	
	@Test
	public void testaValorIgualDez() {
		MatematicaMaluca mm = new MatematicaMaluca();
		int resultado = mm.contaMaluca(10);
		assertEquals(20, resultado);
	}
	
	@Test
	public void testaValorIgualTrinta() {
		MatematicaMaluca mm = new MatematicaMaluca();
		int resultado = mm.contaMaluca(30);
		assertEquals(90, resultado);
	}

}
