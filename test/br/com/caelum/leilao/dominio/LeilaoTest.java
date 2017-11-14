package br.com.caelum.leilao.dominio;

import static org.junit.Assert.*;

import org.junit.Test;

public class LeilaoTest {
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		Leilao leilao = new Leilao("Iphone");
		Usuario zezinho = new Usuario("Zezinho");
		
		leilao.propoe(new Lance(zezinho, 2000.0));
		leilao.propoe(new Lance(zezinho, 3000.0));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarMaisDoQueCincoLancesDeUmUsuario() {
		Leilao leilao = new Leilao("MacBook");
		Usuario carlos = new Usuario("Carlos");
		Usuario mario = new Usuario("Mario");
		
		leilao.propoe(new Lance(carlos, 2000.0));
		leilao.propoe(new Lance(mario, 3000.0));
		leilao.propoe(new Lance(carlos, 4000.0));
		leilao.propoe(new Lance(mario, 5000.0));
		leilao.propoe(new Lance(carlos, 6000.0));
		leilao.propoe(new Lance(mario, 7000.0));
		leilao.propoe(new Lance(carlos, 8000.0));
		leilao.propoe(new Lance(mario, 9000.0));
		leilao.propoe(new Lance(carlos, 10000.0));
		leilao.propoe(new Lance(mario, 11000.0));
		
		leilao.propoe(new Lance(carlos, 12000.0));
		
		int ultimoLance = leilao.getLances().size() - 1;
		
		assertEquals(10, leilao.getLances().size());
		assertEquals(11000.0, leilao.getLances().get(ultimoLance).getValor(), 0.00001);
	}
	
	@Test
	public void dobraUltimoLanceDoUsuario() {
		Leilao leilao = new Leilao("Atari");
		Usuario wando = new Usuario("Wando");
		Usuario magal = new Usuario("Magal");
		
		leilao.propoe(new Lance(wando, 2000.0));
		leilao.propoe(new Lance(magal, 3000.0));
		leilao.propoe(new Lance(wando, 4000.0));
		leilao.dobraLance(magal);
		
		assertEquals(6000.0, leilao.getLances().get(3).getValor(), 0.0001);
	}
	
	@Test
	public void naoDeveDobrarSeNaoHouverLanceAnterior() {
		Leilao leilao = new Leilao("Nintendo");
		Usuario kaka = new Usuario("Kaka");
	
		leilao.dobraLance(kaka);
		
		assertEquals(0, leilao.getLances().size());
	}
}
