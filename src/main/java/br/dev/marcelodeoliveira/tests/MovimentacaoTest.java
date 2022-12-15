package br.dev.marcelodeoliveira.tests;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;

import br.dev.marcelodeoliveira.core.BaseTest;
import br.dev.marcelodeoliveira.core.Propriedades.Movimentacao;
import br.dev.marcelodeoliveira.core.Propriedades.Situacao;
import br.dev.marcelodeoliveira.page.MenuPage;
import br.dev.marcelodeoliveira.page.MovimentacaoPage;

public class MovimentacaoTest extends BaseTest {

	private MovimentacaoPage movimentacaoPage = new MovimentacaoPage();
	private MenuPage menuPage = new MenuPage();
	private final By salvarButton = By.xpath("//button[@type='submit' and .='Salvar']");
	List<String> camposObrigatorios = List.of("Data da Movimentação é obrigatório", "Data do pagamento é obrigatório",
			"Descrição é obrigatório", "Interessado é obrigatório", "Valor é obrigatório", "Valor deve ser um número");

	// FACADE

	@Test
	public void criaMovimentaçaoReceita() {

		menuPage.criarMovimentacao();
		movimentar(LocalDate.now().plus(1, ChronoUnit.MONTHS), LocalDate.now().plus(2, ChronoUnit.DAYS), "Aluguel",
				"Boarriga", 688.5f, "contaMarkito", Situacao.PENDENTE, Movimentacao.RECEITA);

		String result = movimentacaoPage.finalizarTeste();

		Assert.assertEquals(result, "Movimentação adicionada com sucesso!");

	}

	@Test
	public void criaMovimentacaoDespesa() {

		menuPage.criarMovimentacao();
		movimentar(LocalDate.now().plus(-1, ChronoUnit.MONTHS), LocalDate.now().plus(0, ChronoUnit.DAYS), "Aluguel",
				"Boarriga", 688.5f, "contaMarkito", Situacao.PAGA, Movimentacao.DESPESA);

		String result = movimentacaoPage.finalizarTeste();

		Assert.assertEquals(result, "Movimentação adicionada com sucesso!");
	}

	// Interessante tentar fazê-lo nos moldes da regra de negócio"
	@Test
	@Ignore
	public void testarCamposObrigatoriosMovimentacao() {
		;
	}

	private String movimentar(LocalDate dtMovimentacao, LocalDate dtPagamento, String descricao, String interessado,
			Float valor, String nomeDaConta, Situacao situacao, Movimentacao movimentacao) {
		menuPage.criarMovimentacao();
		movimentacaoPage.setDataMovimentacao(dtMovimentacao);

		movimentacaoPage.setDataPagamento(dtPagamento);
		movimentacaoPage.setDescricao(descricao);
		movimentacaoPage.setInteressado(interessado);
		movimentacaoPage.setValor(valor);
		movimentacaoPage.setConta(nomeDaConta);
		movimentacaoPage.setSituacao(situacao);
		movimentacaoPage.setTipoMovimentacao(movimentacao);

		return movimentacaoPage.finalizarTeste();

	}

}
