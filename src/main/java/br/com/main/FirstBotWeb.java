package br.com.main;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import dev.botcity.framework.bot.WebBot;
import dev.botcity.framework.web.browsers.Browser;
import dev.botcity.maestro_sdk.BotExecutor;
import dev.botcity.maestro_sdk.runner.BotExecution;
import dev.botcity.maestro_sdk.runner.RunnableAgent;

public class FirstBotWeb extends WebBot implements RunnableAgent {
    public FirstBotWeb() {
        try {
            setClassLoader(this.getClass().getClassLoader());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void action(BotExecution botExecution) {

        try {
            // Configure whether to run on headless mode
            setHeadless(false);

            // Uncomment to change the default Browser to Firefox
            //setBrowser(Browser.FIREFOX);

            // Uncomment to set the WebDriver path
            //setDriverPath("C://");

            // Opens the BotCity website
            String url = "";
            //browse("https://botcity.dev");
            url = "https://sicg.homologacao.iphan.gov.br/sicg/login";
            //url = "https://sicg.homologacao.iphan.gov.br/sicg/bens/pesquisaBem";
            //browse(url);
            navigateTo(url);
            
            
            
            int cont = 0; 
            System.out.println("Processando a página");
            cont = listInputs();
            cont = 0;
            cont = listSelects();
            
            System.out.println("Total de elementos encontrados : " + cont);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	
        	System.out.println("FIM do Processo !!!");
            // Stop the browser and clean up
            //stopBrowser();
            
            
        }
    }

	private int listSelects() {
		int cont;
		cont = 0;
		
		List<WebElement> selectElements = this.findElements(By.tagName("select"));
		
		for (WebElement element : selectElements) {
			System.out.println("Lista :" + element.getAttribute("name"));
		    cont++;
		}
		return cont;
	}

	private int listInputs() {
		int cont = 0;
		//List<WebElement> elements = this.findElements(By.tagName("input"));
		List<WebElement> inputElements = this.findElements(By.tagName("input"));
		
		for (WebElement element : inputElements) {
		    System.out.println("Caixa Texto ou Botão :" + element.getAttribute("name"));
		    String atributo = element.getAttribute("name");
		    if(atributo.contains("login"))
		    {
		    	element.sendKeys("teste.sistemas");
		    }
		    if(atributo.contains("senha"))
		    {
		    	element.sendKeys("S!st#mas2019");
		    	
		    	WebElement form = this.findElement(By.tagName("form"));
		    	form.submit();
		    	
		    }
		    cont++;
		}
		
		return cont;
	}

    private void notFound(String label) {
        System.out.println("Element not found: " + label);
    }

    public static void main(String[] args) {
        BotExecutor.run(new FirstBotWeb(), args);
    }
}

