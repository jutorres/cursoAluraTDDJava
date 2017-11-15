package br.com.caelum.leilao.dominio;

import org.junit.Test;

public class LanceTest {

	@Test(expected=IllegalArgumentException.class)
	public void testaLanceComValorIgualZero() {
		new Lance(new Usuario("Wando"), 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testaLanceComValorNegativo() {
		new Lance(new Usuario("Wando"), -200.0);
	}
	
}
