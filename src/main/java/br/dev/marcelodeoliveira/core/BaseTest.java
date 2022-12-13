package br.dev.marcelodeoliveira.core;

import static br.dev.marcelodeoliveira.core.DriverFactory.getDriver;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class BaseTest {

	protected static DSL dsl;
	protected String path;

	BaseTest() {
		dsl = new DSL();
		setPath("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}

	protected void setPath(String path) {
		this.path = path;

	}

	@Rule
	public TestName tname = new TestName();

	@After
	public void finaliza() {

		// Funcionalidade de Screenshot
		TakesScreenshot shoter = (TakesScreenshot) getDriver();
		var temp = shoter.getScreenshotAs(OutputType.FILE);

		try {

			FileUtils.copyFile(temp, new File(("./target/screenshots/" + tname.getMethodName() + ".jpg").replaceAll("/",
					File.separator + File.separator)));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}