package br.com.caelum.leilao.dominio;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;

public class LeilaoTest {
	
	private Usuario zezinho;
	private Usuario carlos;
	private Usuario mario;
	private Usuario wando;
	private Usuario magal;
	private Usuario kaka;
	

	@Before
	public void setUp() {
		this.zezinho = new Usuario("Zezinho");
		this.carlos = new Usuario("Carlos");
		this.mario = new Usuario("Mario");
		this.wando = new Usuario("Wando");
		this.magal = new Usuario("Magal");
		this.kaka = new Usuario("Kaka");
	
	}
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		Leilao leilao = new CriadorDeLeilao()
				.para("Iphone")
				.lance(zezinho, 2000.0)
				.lance(zezinho, 3000.0)
				.constroi();
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarMaisDoQueCincoLancesDeUmUsuario() {
		Leilao leilao = new CriadorDeLeilao()
				.para("MacBook")
				.lance(carlos, 2000.0)
				.lance(mario, 3000.0)
				.lance(carlos, 4000.0)
				.lance(mario, 5000.0)
				.lance(carlos, 6000.0)
				.lance(mario, 7000.0)
				.lance(carlos, 8000.0)
				.lance(mario, 9000.0)
				.lance(carlos, 10000.0)
				.lance(mario, 11000.0)
				.lance(carlos, 12000.0)
				.constroi();
		
		int ultimoLance = leilao.getLances().size() - 1;
		
		assertEquals(10, leilao.getLances().size());
		assertEquals(11000.0, leilao.getLances().get(ultimoLance).getValor(), 0.00001);
	}
	
	@Test
	public void dobraUltimoLanceDoUsuario() {
		Leilao leilao = new CriadorDeLeilao()
				.para("Atari")
				.lance(wando, 2000.0)
				.lance(magal, 3000.0)
				.lance(wando, 4000.0)
				.constroi();
		
		leilao.dobraLance(magal);
		
		assertEquals(6000.0, leilao.getLances().get(3).getValor(), 0.0001);
	}
	
	@Test
	public void naoDeveDobrarSeNaoHouverLanceAnterior() {
		Leilao leilao = new CriadorDeLeilao()
				.para("Nintendo")
				.constroi();
	
		leilao.dobraLance(kaka);
		
		assertEquals(0, leilao.getLances().size());
	}
}
