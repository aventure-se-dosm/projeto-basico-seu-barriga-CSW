package br.dev.marcelodeoliveira.page;

import org.openqa.selenium.By;

import br.dev.marcelodeoliveira.core.BasePage;

public class MenuPage extends BasePage {

	private final By contas = By.xpath("//*[@class='dropdown' and contains(text(), Contas)]");
	private final By adicionarConta = By.xpath("//a[contains(text(),'Adicionar')]/parent::*");
	private final By listarContas = By.xpath("//a[contains(text(),'Listar')]/parent::*");

	private final By criarMovimentacao = By.xpath("//a[@href='/movimentacao']/parent::*");

	public void criarConta() {

		clicarBotao(contas);
		clicarBotao(adicionarConta);
	}
	
	public void listarConta() {

		clicarBotao(contas);
		clicarBotao(listarContas);
	}
	
	public void criarMovimentacao() {

		clicarBotao(criarMovimentacao);
		
		
	}

}
