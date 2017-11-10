package br.com.caelum.leilao.teste;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class AvaliadorTest {

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("Jose");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Passagem aerea");
		
		leilao.propoe(new Lance(maria, 250.0));
		leilao.propoe(new Lance(joao, 400.0));
		leilao.propoe(new Lance(jose, 300.0));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		double maiorEsperado = 400.0;
		double menorEsperado = 250.0;
		double mediaEsperada = 316.666666;
		
		Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorDeTodos(), 0.0001);
		Assert.assertEquals(menorEsperado, leiloeiro.getMenorDeTodos(), 0.0001);
		Assert.assertEquals(mediaEsperada, leiloeiro.getMediaDosLances(), 0.0001);
		
	}
	
	@Test
    public void testaMediaDeZeroLance(){

        Usuario luis = new Usuario("Luis");

        Leilao leilao = new Leilao("Cafeteira");

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        Assert.assertEquals(0, leiloeiro.getMediaDosLances(), 0.0001);

    }
	
}
