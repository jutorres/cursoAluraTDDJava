package br.com.caelum.matcher.LeilaoMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class LeilaoMatcher extends TypeSafeMatcher<Leilao> {

	private Lance lance;
	
	public LeilaoMatcher(Lance lance) {
		this.lance = lance;
	}

	public void describeTo(Description description) {
		description.appendText("Leilao com lance" + lance.getValor());
	}

	@Override
	protected boolean matchesSafely(Leilao item) {
		return item.getLances().contains(lance);
	}

	public static Matcher<Leilao> temUmLance(Lance lance) {
		return new LeilaoMatcher(lance);
	}
	
}
