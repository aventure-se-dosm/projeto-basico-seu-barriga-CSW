package br.dev.marcelodeoliveira.page;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;

import br.dev.marcelodeoliveira.core.BasePage;
import br.dev.marcelodeoliveira.core.Propriedades.Movimentacao;
import br.dev.marcelodeoliveira.core.Propriedades.Situacao;

public class MovimentacaoPage extends BasePage {

	private MenuPage menuPage = new MenuPage();
	private final By alertDiv = By.xpath("//div[@role='alert']");
	private final By salvarButton= By.xpath("//button[@type='submit' and .='Salvar']");

	public void setTipoMovimentacao(Movimentacao m) {
	
		String resp;
		switch (m) {
		case DESPESA:
			resp = "Despesa";
			break;
		case RECEITA:
		default:
			resp = "Receita";
		}
		
		selecionarCombo(By.xpath("//select[@id='tipo']"), resp);
	}
	
	private String formatarData (LocalDate ld, String pattern) {
		return ld.format(DateTimeFormatter.ofPattern(pattern));
	}
	
	private String formatarData (LocalDate ld) {
		return ld.format(DateTimeFormatter.ofPattern("dd/MM/YYYY"));
	}
	
	
	public void setDataMovimentacao (LocalDate dataTransacao) {
		escrever(By.xpath("//input[@id='data_transacao']"), formatarData(dataTransacao));
	}
	
	public void setDataPagamento(LocalDate dataPgto) {
		escrever(By.xpath("//input[@id='data_pagamento']"), formatarData(dataPgto));
	};
	public void setDescricao(String descricao) {
		escrever(By.xpath("//input[@id='descricao']"), descricao);
	};
	public void setInteressado(String interessado) {
		escrever(By.xpath("//input[@id='interessado']"), interessado);
	};
	public void setValor(Float valor) {
		escrever(By.xpath("//input[@id='valor']"), valor.toString().replace(',','.'));
	};
	public void setConta(String conta) {
		escrever(By.xpath("//input[@id='conta']"), conta);
	};
	public void setSituacao(Situacao s) {
		escrever(By.xpath("//input[@id='data_pagamento']"), s.toString());
	}


	public String finalizarTeste() {
	
			clicarBotao(salvarButton);
			var texto = obterTexto(alertDiv);
			
			return texto;
	
	}

}
