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
            int contInputs, contSelects, contLabels, contTotal;
            
            String fileName = "D:\\Users\\cblna\\Documents\\Paulo\\github\\urls.txt";
            
            ReadTextFile reader = new ReadTextFile();
            List<String> urls = reader.readFile(fileName);
            
            for (String url : urls)
            {
            	
            	if(!url.startsWith("*"))
            	{
            	
	            	System.out.println("Navigate to Url : " + url);
	            	
	            	navigateTo(url);
	            	
	            	
	                
	                System.out.println("Processando a página");
	                
	                contInputs = listInputs();
	                
	                contSelects = listSelects();
	                
	                contLabels = listLabels();
	                
	                contTotal = contInputs+ contLabels+ contSelects;
	                
	                System.out.println("End execute url : " + url);
	                System.out.println("Total de elementos encontrados : " + contTotal);
	                
            	}
            	
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	
        	System.out.println("FIM do Processo !!!");
            // Stop the browser and clean up
            stopBrowser();
            
            
        }
    }

    
    private int listLabels() {
		int cont;
		cont = 0;
		
		List<WebElement> labelElements = this.findElements(By.tagName("label"));
		
		for (WebElement element : labelElements) {
			System.out.println("Lista rótulos :" + extractedAttribute(element));
		    cont++;
		}
		
		List<WebElement> legendElements = this.findElements(By.tagName("legend"));
		
		for (WebElement element : legendElements) {
			System.out.println("Lista rótulos :" + extractedAttribute(element));
		    cont++;
		}
		
		return cont;
	}

	private String extractedAttribute(WebElement element) {
		
		if(element.getAttribute("name")==null) return element.getAttribute("id");
		
		if(element.getAttribute("name").isBlank()) return element.getTagName();
		//if(element.getAttribute("name").isBlank()) return element.getAttribute("id");
		
		return element.getAttribute("name");
	}
    
	private int listSelects() {
		int cont;
		cont = 0;
		
		List<WebElement> selectElements = this.findElements(By.tagName("select"));
		
		for (WebElement element : selectElements) {
			System.out.println("Listas suspensas:" + extractedAttribute(element));
		    cont++;
		}
		return cont;
	}

	private int listInputs() {
		int cont = 0;
		//List<WebElement> elements = this.findElements(By.tagName("input"));
		List<WebElement> inputElements = this.findElements(By.tagName("input"));
		
		for (WebElement element : inputElements) {
		    System.out.println("Caixa Texto ou Botão :" + extractedAttribute(element));
		    cont++;
		    String atributo = extractedAttribute(element);
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

