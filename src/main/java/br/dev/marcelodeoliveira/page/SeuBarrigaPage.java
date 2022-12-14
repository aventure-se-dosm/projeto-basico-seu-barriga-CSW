package br.dev.marcelodeoliveira.page;

import br.dev.marcelodeoliveira.core.BasePage;
import static br.dev.marcelodeoliveira.core.DriverFactory.getDriver;

import org.junit.Before;
import org.junit.Test;

public class SeuBarrigaPage extends BasePage{
	
	private String path = "https://seubarriga.wcaquino.me";
	
	@Before
	public void inicializa() {
		getDriver().get(path);
		getDriver().manage().window().maximize();
	}
	
	@Test
	public void criarContaTest() {
		
	}
	
	@Test
	public void alterarContaTest() {
		
	}
	
	@Test
	public void CriarContaComMesmoNomeTest() {
		
	}
	
	//Extra
	@Test
	public void DeletarContaTest() {
		
	}
	
	
}
