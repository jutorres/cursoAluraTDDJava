package br.com.caelum.bissexto;

public class Bissexto {

	private int ano;
	
	public int getAno() {
		return ano;
	}

	public boolean ehBissexto(int ano) {
		if(ano % 4 == 0 && ano % 100 != 0) {
			return true;
		} else if (ano % 400 == 0) {
			return false;
		}
		return false;
	}

}
