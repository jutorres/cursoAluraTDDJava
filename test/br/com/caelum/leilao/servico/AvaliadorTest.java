package br.com.caelum.leilao.servico;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
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
				.lance(maria, 200.0)
				.lance(joao, 300.0)
				.lance(jose, 400.0)
				.constroi();
		
		leiloeiro.avalia(leilao);
		
		assertThat(leiloeiro.getMaiorDeTodos(), equalTo(400.0));
		assertThat(leiloeiro.getMenorDeTodos(), equalTo(200.0));
		assertThat(leiloeiro.getMediaDosLances(), equalTo(300.0));
		
	}
	
	@Test(expected=RuntimeException.class)
    public void naoDeveAvaliarLeiloesSemNenhumLanceDado(){
        Leilao leilao = new CriadorDeLeilao()
        		.para("Cafeteira")
        		.constroi();
        
        leiloeiro.avalia(leilao);

    }
	
	@Test
	public void testaLeilaoComUmLance() {
		Leilao leilao = new CriadorDeLeilao()
				.para("Viagem")
				.lance(carlos, 400.0)
				.constroi();
		
		leiloeiro.avalia(leilao);
		
		assertThat(leiloeiro.getMaiorDeTodos(), equalTo(400.0));
		assertThat(leiloeiro.getMenorDeTodos(), equalTo(400.0));
		assertThat(leiloeiro.getMediaDosLances(), equalTo(400.0));
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
		
		assertThat(leiloeiro.getMaiorDeTodos(), equalTo(700.0));
		assertThat(leiloeiro.getMenorDeTodos(), equalTo(120.0));
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
		
		assertThat(leiloeiro.getMaiorDeTodos(), equalTo(400.0));
		assertThat(leiloeiro.getMenorDeTodos(), equalTo(100.0));
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
		
		assertThat(leiloeiro.getTresMaioresLances().size(), equalTo(3));
		assertThat(leiloeiro.getTresMaioresLances(), hasItems(
                new Lance(maria, 400), 
                new Lance(joao, 300),
                new Lance(jose, 200)
        ));
	}
	
	@Test
	public void testaLeilaoComDoisLances() {
		Leilao leilao = new CriadorDeLeilao()
				.para("Boi gordo")
				.lance(joao, 300.0)
				.lance(jose, 200.0)
				.constroi();
		
		leiloeiro.avalia(leilao);
		
		assertThat(leiloeiro.getTresMaioresLances().size(), equalTo(2));
		assertThat(leiloeiro.getTresMaioresLances(), hasItems(
                new Lance(joao, 300),
                new Lance(jose, 200)
        ));
	}
	
}
