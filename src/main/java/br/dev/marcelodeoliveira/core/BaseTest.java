package br.dev.marcelodeoliveira.core;

import static br.dev.marcelodeoliveira.core.DriverFactory.getDriver;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import br.dev.marcelodeoliveira.page.LoginPage;

public class BaseTest {


	protected String path;
	protected LoginPage page = new LoginPage();

	public BaseTest() {

		setPath("https://seubarriga.wcaquino.me/login");
	}

	protected void setPath(String path) {
		this.path = path;
	}

	@Rule
	public TestName tname = new TestName();

	@Before 
		public void inicializa() {
		page.entraNaTelaInicial();
		page.login("dev.marcelodeoliveira@gmail.com", "kiko");
	}

	
	@After
	public void finaliza() {

		// Funcionalidade de Screenshot
		TakesScreenshot shoter = (TakesScreenshot) getDriver();
		var temp = shoter.getScreenshotAs(OutputType.FILE);

		try {

			FileUtils.copyFile(temp, new File(("./target/screenshots/" + tname.getMethodName() + ".jpg").replaceAll("/",
					File.separator + File.separator)));

		} catch (IOException e) {
			System.out.println("Erro: não foi possível salvar o arquivo!");
		}

		catch (Exception e) {
			System.out.println("Erro:\n" + e.getLocalizedMessage());

		}

	}

}