package br.com.caelum.leilao.servico;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class AvaliadorTest {
	
	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario jose;
	private Usuario maria;
	private Usuario carlos;

	@Before
	public void setUp() {
		this.leiloeiro = new Avaliador();
		this.joao = new Usuario("Joao");
		this.jose = new Usuario("Jose");
		this.maria = new Usuario("Maria");
		this.carlos = new Usuario("Carlos");
	}

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		Leilao leilao = new CriadorDeLeilao()
				.para("Passagem aerea")
				.lance(maria, 250.0)
				.lance(joao, 300.0)
				.lance(jose, 400.0)
				.constroi();
		
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
        Leilao leilao = new CriadorDeLeilao()
        		.para("Cafeteira")
        		.constroi();
        
        leiloeiro.avalia(leilao);

        assertEquals(0, leiloeiro.getMediaDosLances(), 0.0001);

    }
	
	@Test
	public void testaLeilaoComUmLance() {
		Leilao leilao = new CriadorDeLeilao()
				.para("Viagem")
				.lance(carlos, 400.0)
				.constroi();
		
		leiloeiro.avalia(leilao);

		assertEquals(400.0, leiloeiro.getMaiorDeTodos(), 0.0001);
		assertEquals(400.0, leiloeiro.getMenorDeTodos(), 0.0001);
		assertEquals(400.0, leiloeiro.getMediaDosLances(), 0.0001);
	}
	
	@Test
	public void testeLeilaoComLancesAleatorios() {
		Leilao leilao = new CriadorDeLeilao()
				.para("Passagem aerea")
				.lance(maria, 200.0)
				.lance(joao, 450.0)
				.lance(jose, 120.0)
				.lance(joao, 700.0)
				.lance(maria, 630.0)
				.lance(joao, 230.0)
				.constroi();
		
		leiloeiro.avalia(leilao);
		
		assertEquals(700.0, leiloeiro.getMaiorDeTodos(), 0.0001);
		assertEquals(120.0, leiloeiro.getMenorDeTodos(), 0.0001);
		
	}
	
	@Test
	public void testeLeilaoComLancesEmOrdemDescrescente() {
		Leilao leilao = new CriadorDeLeilao()
				.para("Boi gordo")
				.lance(maria, 400.0)
				.lance(joao, 300.0)
				.lance(jose, 200.0)
				.lance(maria, 100.0)
				.constroi();
		
		leiloeiro.avalia(leilao);
		
		assertEquals(400.0, leiloeiro.getMaiorDeTodos(), 0.0001);
		assertEquals(100.0, leiloeiro.getMenorDeTodos(), 0.0001);
		
	}
	
	@Test
	public void testaSeRetornaOsTresMaioresLances() {
		Leilao leilao = new CriadorDeLeilao()
				.para("Boi gordo")
				.lance(maria, 400.0)
				.lance(joao, 300.0)
				.lance(jose, 200.0)
				.lance(maria, 100.0)
				.constroi();
		
		leiloeiro.avalia(leilao);
		
		assertEquals(3, leiloeiro.getTresMaioresLances().size());
		assertEquals(400.0, leiloeiro.getTresMaioresLances().get(0).getValor(), 0.0001);
		assertEquals(300.0, leiloeiro.getTresMaioresLances().get(1).getValor(), 0.0001);
		assertEquals(200.0, leiloeiro.getTresMaioresLances().get(2).getValor(), 0.0001);
		
	}
	
	@Test
	public void testaLeilaoComDoisLances() {
		Leilao leilao = new CriadorDeLeilao()
				.para("Boi gordo")
				.lance(joao, 300.0)
				.lance(jose, 200.0)
				.constroi();
		
		leiloeiro.avalia(leilao);
		
		assertEquals(2, leiloeiro.getTresMaioresLances().size());
		assertEquals(300.0, leiloeiro.getTresMaioresLances().get(0).getValor(), 0.0001);
		assertEquals(200.0, leiloeiro.getTresMaioresLances().get(1).getValor(), 0.0001);
		
	}
	
	@Test
    public void deveDevolverListaVaziaCasoNaoHajaLances() {
		Leilao leilao = new CriadorDeLeilao()
				.para("Boi gordo")
				.constroi();

        leiloeiro.avalia(leilao);

        assertEquals(0, leilao.getLances().size());
        assertEquals(true, leiloeiro.getTresMaioresLances().isEmpty());
    }
}
