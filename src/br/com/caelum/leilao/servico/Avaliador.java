package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {
	
	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	private double menorDeTodos = Double.POSITIVE_INFINITY;
	private double mediaDosLances = 0;
	
	public void avalia(Leilao leilao) {
		double totalDosLances = 0;
		for (Lance lance : leilao.getLances()) {
			if(lance.getValor() > maiorDeTodos) {
				maiorDeTodos = lance.getValor();
			}
			if(lance.getValor() < menorDeTodos) {
				menorDeTodos = lance.getValor();
			}
			totalDosLances  += lance.getValor();
		}
		if(totalDosLances == 0) {
			mediaDosLances = 0;
			return;
		}
		mediaDosLances = totalDosLances/leilao.getLances().size();
	}

	public double getMaiorDeTodos() {
		return maiorDeTodos;
	}

	public double getMenorDeTodos() {
		return menorDeTodos;
	}
	
	public double getMediaDosLances() {
		return mediaDosLances;
	}

}
