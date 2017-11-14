package br.com.caelum.bissexto;

import static org.junit.Assert.*;

import org.junit.Test;

public class BissextoTest {
	
	@Test
	public void verificaSeUmAnoEhBissexto() {
		Bissexto bissexto = new Bissexto();
		
		assertEquals(false, bissexto.ehBissexto(2017));
		assertEquals(false, bissexto.ehBissexto(2000));
		assertEquals(true, bissexto.ehBissexto(2016));
	}

}
