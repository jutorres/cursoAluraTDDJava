package br.com.caelum.leilao.servico;

import static org.junit.Assert.*;

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
		Avaliador leiloeiro = new Avaliador();
		
		leilao.propoe(new Lance(maria, 250.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(jose, 400.0));
		leiloeiro.avalia(leilao);
		
		double maiorEsperado = 400.0;
		double menorEsperado = 250.0;
		double mediaEsperada = 316.666666;
		
		assertEquals(maiorEsperado, leiloeiro.getMaiorDeTodos(), 0.0001);
		assertEquals(menorEsperado, leiloeiro.getMenorDeTodos(), 0.0001);
		assertEquals(mediaEsperada, leiloeiro.getMediaDosLances(), 0.0001);
		
	}
	
	@Test
    public void testaMediaDeZeroLance(){
        Leilao leilao = new Leilao("Cafeteira");
        Avaliador leiloeiro = new Avaliador();
        
        leiloeiro.avalia(leilao);

        assertEquals(0, leiloeiro.getMediaDosLances(), 0.0001);

    }
	
	@Test
	public void testaLeilaoComUmLance() {
		Usuario carlos = new Usuario("Carlos");
		Leilao leilao = new Leilao("Viagem");
		Avaliador leiloeiro = new Avaliador();
		
		leilao.propoe(new Lance(carlos, 400.0));
		leiloeiro.avalia(leilao);

		assertEquals(400.0, leiloeiro.getMaiorDeTodos(), 0.0001);
		assertEquals(400.0, leiloeiro.getMenorDeTodos(), 0.0001);
		assertEquals(400.0, leiloeiro.getMediaDosLances(), 0.0001);
	}
	
	@Test
	public void testeLeilaoComLancesAleatorios() {
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("Jose");
		Usuario maria = new Usuario("Maria");
		Leilao leilao = new Leilao("Passagem aerea");
		Avaliador leiloeiro = new Avaliador();
		
		leilao.propoe(new Lance(maria, 200.0));
		leilao.propoe(new Lance(joao, 450.0));
		leilao.propoe(new Lance(jose, 120.0));
		leilao.propoe(new Lance(joao, 700.0));
		leilao.propoe(new Lance(maria, 630.0));
		leilao.propoe(new Lance(joao, 230.0));
		leiloeiro.avalia(leilao);
		
		assertEquals(700.0, leiloeiro.getMaiorDeTodos(), 0.0001);
		assertEquals(120.0, leiloeiro.getMenorDeTodos(), 0.0001);
		
	}
	
	@Test
	public void testeLeilaoComLancesEmOrdemDescrescente() {
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("Jose");
		Usuario maria = new Usuario("Maria");
		Leilao leilao = new Leilao("Boi gordo");
		Avaliador leiloeiro = new Avaliador();
		
		leilao.propoe(new Lance(maria, 400.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(jose, 200.0));
		leilao.propoe(new Lance(maria, 100.0));
		leiloeiro.avalia(leilao);
		
		assertEquals(400.0, leiloeiro.getMaiorDeTodos(), 0.0001);
		assertEquals(100.0, leiloeiro.getMenorDeTodos(), 0.0001);
		
	}
	
	@Test
	public void testaSeRetornaOsTresMaioresLances() {
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("Jose");
		Usuario maria = new Usuario("Maria");
		Leilao leilao = new Leilao("Boi gordo");
		Avaliador leiloeiro = new Avaliador();
		
		leilao.propoe(new Lance(maria, 400.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(jose, 200.0));
		leilao.propoe(new Lance(maria, 100.0));
		leiloeiro.avalia(leilao);
		
		assertEquals(3, leiloeiro.getTresMaioresLances().size());
		assertEquals(400.0, leiloeiro.getTresMaioresLances().get(0).getValor(), 0.0001);
		assertEquals(300.0, leiloeiro.getTresMaioresLances().get(1).getValor(), 0.0001);
		assertEquals(200.0, leiloeiro.getTresMaioresLances().get(2).getValor(), 0.0001);
		
	}
	
	@Test
	public void testaLeilaoComDoisLances() {
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("Jose");
		Leilao leilao = new Leilao("Boi gordo");
		Avaliador leiloeiro = new Avaliador();
		
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(jose, 200.0));
		leiloeiro.avalia(leilao);
		
		assertEquals(2, leiloeiro.getTresMaioresLances().size());
		assertEquals(300.0, leiloeiro.getTresMaioresLances().get(0).getValor(), 0.0001);
		assertEquals(200.0, leiloeiro.getTresMaioresLances().get(1).getValor(), 0.0001);
		
	}
	
	@Test
    public void deveDevolverListaVaziaCasoNaoHajaLances() {
        Leilao leilao = new Leilao("Playstation 3 Novo");

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        assertEquals(0, leilao.getLances().size());
        assertEquals(true, leiloeiro.getTresMaioresLances().isEmpty());
    }
}
