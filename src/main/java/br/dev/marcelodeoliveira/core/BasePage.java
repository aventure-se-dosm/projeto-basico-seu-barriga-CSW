package br.dev.marcelodeoliveira.core;

import static br.dev.marcelodeoliveira.core.DriverFactory.getDriver;


import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BasePage {
	protected DSL dsl;
	protected String path;

	protected BasePage(String path) {
		this.path = path;
	}

	public BasePage() {

	}

	/********* TextField e TextArea ************/

	public void escrever(By by, String texto) {
		getDriver().findElement(by).clear();
		getDriver().findElement(by).sendKeys(texto);
	}

	public void escrever(String id_campo, String texto) {
		escrever(By.id(id_campo), texto);
	}

	public String obterValorCampo(String id_campo) {
		return getDriver().findElement(By.id(id_campo)).getAttribute("value");
	}

	/********* Radio e Check ************/

	public void clicarRadio(String id) {
		getDriver().findElement(By.id(id)).click();
	}

	public void clicarRadio(By by) {
		getDriver().findElement(by).click();
	}

	public boolean isRadioMarcado(String id) {
		return getDriver().findElement(By.id(id)).isSelected();
	}

	public void clicarCheck(String id) {
		getDriver().findElement(By.id(id)).click();
	}

	public boolean isCheckMarcado(String id) {
		return getDriver().findElement(By.id(id)).isSelected();
	}

	/********* Combo ************/

	public void selecionarCombo(String id, String valor) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		combo.selectByVisibleText(valor);

	}

	public void deselecionarCombo(By xpath, String... values) {
		WebElement element = getDriver().findElement(xpath);
		Select combo = new Select(element);
		for (String value : values)
			combo.deselectByValue(value);

	}

//	public void deselecionarCombo(String id, String valor) {
//		WebElement element = getDriver().findElement(By.id(id));
//		Select combo = new Select(element);
//		combo.deselectByVisibleText(valor);
//	}

	public String obterValorCombo(String id) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}

	// faz uma DTO dos elementos selecionados no combo
	public List<String> obterValoresCombo(String id) {

		Select combo = new Select(getDriver().findElement(By.id(id)));

		return combo.getAllSelectedOptions().stream().map(p -> p.getText()).collect(Collectors.toList());

//		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
//		List<String> valores = new ArrayList<String>();
//		for (WebElement opcao : allSelectedOptions) {
//			valores.add(opcao.getText());
//		}
//		return valores;
	}

	public int obterQuantidadeOpcoesCombo(String id) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		return options.size();
	}

	public boolean verificarOpcaoCombo(String id, String opcao) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		for (WebElement option : options) {
			if (option.getText().equals(opcao)) {
				return true;
			}
		}
		return false;
	}

	/********* Botao ************/

	public void clicarBotao(String id) {
		getDriver().findElement(By.id(id)).click();
	}

	public String obterValueElemento(String id) {
		return getDriver().findElement(By.id(id)).getAttribute("value");
	}

	/********* Link ************/

	public void clicarLink(String link) {
		getDriver().findElement(By.linkText(link)).click();
	}

	/********* Textos ************/

	public String obterTexto(By by) {
		return getDriver().findElement(by).getText();
	}

	public String obterTexto(String id) {
		return obterTexto(By.id(id));
	}

	/********* Alerts ************/

	public String alertaObterTexto() {
		Alert alert = getDriver().switchTo().alert();
		return alert.getText();
	}

	public String alertaObterTextoEAceita() {
		Alert alert = getDriver().switchTo().alert();
		String valor = alert.getText();
		alert.accept();
		sairFrame();
		return valor;

	}

	public String alertaObterTextoENega() {
		Alert alert = getDriver().switchTo().alert();
		String valor = alert.getText();
		alert.dismiss();
		return valor;

	}

	public void alertaEscrever(String valor) {
		Alert alert = getDriver().switchTo().alert();
		alert.sendKeys(valor);
		alert.accept();
	}

	/********* Frames e Janelas ************/

	public void entrarFrame(String id) {
		getDriver().switchTo().frame(id);
	}

	public void sairFrame() {
		getDriver().switchTo().defaultContent();
	}

	public void trocarJanela(String id) {
		getDriver().switchTo().window(id);
	}

	/********* JavaScript ************/

	public void executaJS(String cod, Object... args) {

		JavascriptExecutor js = (JavascriptExecutor) getDriver();

		js.executeScript(cod, args);

	}

	/********* Tabela ************/

	// obter índices de linha e coluna para criar um xpath que clique no botão
	// correspondente ao nome.
	// testar a mensagem aberta.
	// só pelo classpath montado que o botão correspondente sertá clicado.
	// indexação html: 1 a n; n >=1.

//	private int obterIndiceColuna(WebElement tabela, String attribute) {
//
//		List<WebElement> colunas = tabela.findElements(By.xpath("./thead//th"));
//		return (colunas.indexOf(colunas.stream().filter(tr -> tr.getText().equals(attribute)).findFirst().get()) + 1);
//	}

	private int obterIndiceLinha(WebElement tabela, String value) {

		List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr"));
		Assert.assertEquals(linhas.size(), 5);

		WebElement elem = tabela.findElement(By.xpath("./tbody/tr/td[.='" + value + "']/.."));

		Assert.assertTrue(linhas.contains(elem));

		return (linhas.indexOf(elem) + 1);

	}

	public String interageComTabela_xpath(String value, String elemType, String tabelaId) {

		var tabela = getDriver().findElement(By.xpath("//table[@id='" + tabelaId + "']"));

		int indiceLin = obterIndiceLinha(tabela, value);

		var button = tabela.findElement(By.xpath("./tbody/tr[" + indiceLin + "]/td/input[@type='" + elemType + "']"));

		Assert.assertEquals("input", button.getTagName());
		Assert.assertEquals("button", button.getAttribute("type"));
		button.click();

		return alertaObterTextoENega();

	}

	public void clicarBotao(By xpath) {
		getDriver().findElement(xpath).click();

	}

	public void buscarPorXpathEClicarBotao(WebElement elem, By by) {

		elem.findElement(by);

	}

	public String obterValorCampo(By xpath) {
		return getDriver().findElement(xpath).getAttribute("value");
	}

}
