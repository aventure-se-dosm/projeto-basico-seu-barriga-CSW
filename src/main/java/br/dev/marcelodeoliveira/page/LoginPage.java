package br.dev.marcelodeoliveira.page;

import static br.dev.marcelodeoliveira.core.DriverFactory.getDriver;

import org.openqa.selenium.By;

import br.dev.marcelodeoliveira.core.BasePage;

public class LoginPage extends BasePage {
	



	


	public void entraNaTelaInicial() {
		super.setPath("https://seubarriga.wcaquino.me/login");
		getDriver().get(path);
		getDriver().manage().window().maximize();
		
	}
	
	
	

	
	public void setUsermail(String usermail) {
		
		escrever(By.xpath("//input[@id='email']"), usermail);
		
	}
	
	public void setPassword(String senha) {
		
		escrever(By.xpath("//input[@id='senha']"), senha);
		
	}
	
	public void entrar () {
		clicarBotao(By.xpath("//button[.='Entrar' and @class='btn btn-primary']"));
	}
	
	public void login(String user, String password) {
		setUsermail(user);
		setPassword(password);
		entrar();
	}
	
	
	
	
	
}
