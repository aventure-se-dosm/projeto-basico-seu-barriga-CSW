package br.dev.marcelodeoliveira.page;

import org.openqa.selenium.By;

import br.dev.marcelodeoliveira.core.BasePage;

public class ContasPage extends BasePage {

	private final By adicionarContaTextField = By.xpath("//input[@id='nome']");
	private final By editarContaTextField = By.xpath("//input[@id='nome']");
	//private final By editar_barry = By.xpath("//td[.='Barry Gibb']/following-sibling::*//a[starts-with (@href, '/editarConta?id=')]");

//	private final By editar_barbara = By.xpath("//td[.='Barbra Straisand']/following-sibling::*//a[starts-with (@href, '/editarConta?id=')]");
	private final By remover = By.xpath("//td[.='Barbra Straisand']/following-sibling::*//a[contains (@href, '/removerConta?id=')]");

	private final By alertDiv = By.xpath("//div[@role='alert']");
	private final By salvarButton= By.xpath("//button[@type='submit' and .='Salvar']");

	public String salvaConta(String nomeConta) {

		
		escrever(adicionarContaTextField, nomeConta);

		clicarBotao(salvarButton);

		return obterTexto(alertDiv);
	}
	
	
	//aqui as contas já estão listadas! é só clicar em "editar"
	public String editarConta(String nomeConta, String novoNomeConta) {
		
		var editButton = By.xpath("//td[.='"+nomeConta+"']/following-sibling::*//a[starts-with (@href, '/editarConta?id=')]");
		
		clicarBotao(editButton);
		escrever(editarContaTextField ,novoNomeConta);
		clicarBotao(salvarButton);
		return obterTexto(alertDiv);
	}
	
	public String excluirConta(String nomeConta) {
		var deleteButton = By.xpath("//td[.='"+nomeConta+"']/following-sibling::*//a[starts-with (@href, '/removerConta?id=')]");
		
		clicarBotao(deleteButton);

		return obterTexto(alertDiv);
	}
}
