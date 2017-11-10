package br.com.caelum.palindromo.teste;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.palindromo.Palindromo;

public class PalindromoTest {

	@Test
	public void testaSeFraseEhPalindromo() {
		Palindromo palindromo = new Palindromo();
		boolean frase = palindromo.ehPalindromo("Anotaram a data da maratona");
		
		Assert.assertEquals(true, frase);
	}
	
	@Test
    public void deveIdentificarPalindromo() {
        Palindromo palindromo = new Palindromo();
        boolean resultado = palindromo.ehPalindromo("Socorram-me subi no onibus em Marrocos");
        
        Assert.assertTrue(resultado);
    }
	
	@Test
    public void deveIdentificarSeNaoEhPalindromo() {
        Palindromo palindromo = new Palindromo();
        boolean resultado = palindromo.ehPalindromo("E preciso amar as pessoas como se nao houvesse amanha");
        
        Assert.assertFalse(resultado);
    }
	
}
