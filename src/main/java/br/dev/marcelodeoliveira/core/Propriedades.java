package br.dev.marcelodeoliveira.core;

public class Propriedades {

	public static boolean FECHAR_BROWSER = false;

	public static Browser default_browser = Browser.BALANCIAGA;

	public enum Browser {

		CHROME, EDGE, FIREFOX, IEXPLORER, SAFARI, BALANCIAGA,
	}

	public enum Movimentacao {

		RECEITA, DESPESA,
	}

	public enum Situacao {

		PAGA, PENDENTE,

	}
}
