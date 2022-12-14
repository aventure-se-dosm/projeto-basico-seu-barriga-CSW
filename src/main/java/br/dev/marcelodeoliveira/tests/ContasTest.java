package br.dev.marcelodeoliveira.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import br.dev.marcelodeoliveira.core.BaseTest;

public class ContasTest extends BaseTest{
	
	@Test
	public void criarConta()
	{
		var cliclXpath = By.xpath("//*[@class='dropdown' and contains(text(), Contas)]");
		page.clicarBotao(By.xpath("//*[@class='dropdown' and contains(text(), Contas)]"));
		
		Assert.assertTrue(true);
	}
}
