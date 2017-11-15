package br.com.caelum.leilao.servico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {
	
	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	private double menorDeTodos = Double.POSITIVE_INFINITY;
	private double mediaDosLances = 0;
	private List<Lance> maiores = new ArrayList<Lance>();
	
	public void avalia(Leilao leilao) {
		double totalDosLances = 0;
		
		if(leilao.getLances().size() == 0) {
			throw new RuntimeException("Não é possível avaliar um leilão sem lances!");
		}
		
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
		retornaMaioresValores(leilao);
	}

	private void retornaMaioresValores(Leilao leilao) {
		maiores = new ArrayList<Lance>(leilao.getLances());
		Collections.sort(maiores, new Comparator<Lance>() {
			public int compare(Lance lance1, Lance lance2) {
				if(lance1.getValor() < lance2.getValor()) {
					return 1;
				}
				if(lance1.getValor() > lance2.getValor()) {
					return -1;
				}
				return 0;
			}
		});
		maiores = maiores.subList(0, maiores.size() > 3 ? 3 : maiores.size());
	}

	public List<Lance> getTresMaioresLances() {
		return maiores;
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
