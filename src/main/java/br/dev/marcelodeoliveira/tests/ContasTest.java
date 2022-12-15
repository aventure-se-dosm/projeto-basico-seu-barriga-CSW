package br.dev.marcelodeoliveira.tests;

import org.junit.Assert;
import org.junit.Test;

import br.dev.marcelodeoliveira.core.BaseTest;
import br.dev.marcelodeoliveira.page.ContasPage;
import br.dev.marcelodeoliveira.page.MenuPage;

public class ContasTest extends BaseTest {

	private MenuPage menuPage = new MenuPage();
	private ContasPage contasPage = new ContasPage();

	@Test
	public void criarConta() {

		var nome = "Barbara Streiszand";
		menuPage.criarConta();
		var texto = contasPage.salvaConta(nome);

		Assert.assertEquals("Conta adicionada com sucesso!", texto);
		contasPage.excluirConta(nome);
	}

	@Test
	public void criarContaDuplicada() {

		var nome = "Barbara Streiszand";
		menuPage.criarConta();
		var texto = contasPage.salvaConta(nome);

		Assert.assertEquals("Conta adicionada com sucesso!", texto);
		menuPage.criarConta();
		texto = contasPage.salvaConta(nome);

		Assert.assertEquals("Já existe uma conta com esse nome!", texto);
		menuPage.listarConta();
		contasPage.excluirConta(nome);
	}

	@Test
	public void editarConta() {

		var nome = "Barney Geebles";
		var nome_corrigido = "Barry Gibb";
		menuPage.criarConta();

		contasPage.salvaConta(nome);

		menuPage.listarConta();

		var texto = contasPage.editarConta(nome, nome_corrigido);

		Assert.assertEquals("Conta alterada com sucesso!", texto);

		contasPage.excluirConta(nome_corrigido);
	}

	@Test
	public void excluirConta() {

		var nome = "Andy Gibb";
		menuPage.criarConta();

		contasPage.salvaConta(nome);

		menuPage.listarConta();

		var texto = contasPage.excluirConta(nome);

		Assert.assertEquals("Conta removida com sucesso!", texto);

	}

}
