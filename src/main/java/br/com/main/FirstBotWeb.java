package br.com.main;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import dev.botcity.framework.bot.WebBot;
import dev.botcity.framework.web.browsers.Browser;
import dev.botcity.maestro_sdk.BotExecutor;
import dev.botcity.maestro_sdk.runner.BotExecution;
import dev.botcity.maestro_sdk.runner.RunnableAgent;

public class FirstBotWeb extends WebBot implements RunnableAgent {
    
	private WriteToFile log;
	
	public FirstBotWeb() {
        try {
            setClassLoader(this.getClass().getClassLoader());
            log = new WriteToFile();
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
            int contInputs, contSelects, contLabels, contImages,  contTotal;
            contSelects = 0;
            String fileName = "D:\\Users\\cblna\\Documents\\Paulo\\github\\urls.txt";
            
            ReadTextFile reader = new ReadTextFile();
            List<String> urls = reader.readFile(fileName);
            
            for (String url : urls)
            {
            	
            	if(!url.startsWith("*"))
            	{
            	
	            	log.write("Navigate to Url : " + url);
	            	
	            	navigateTo(url);
	            	
	            	
	            	log.write("***************************************************************************");
	            	log.write("Processando a página " + url);
	            	log.write("***************************************************************************");
	                
	                contInputs = listInputs();
	                log.write("Total de Inputs: " + contInputs);
	                
	                contLabels = listLabels();
	                log.write("Total de Rótulos: " + contLabels);
	                
	                contSelects = listSelects();
	                log.write("Total de Listas Suspensas: " + contSelects);
	                
	                contImages = listImages();
	                log.write("Total de Iamgens ou Botões Clicáveis: " + contImages);

	                
	        		
	                log.write("***************************************************************************");
	                log.write("End execute url : " + url);
	                log.write("***************************************************************************");
	                
	                
            	}
            	
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	
        	log.write("FIM do Processo !!!");
        	log.close();
            // Stop the browser and clean up
            stopBrowser();
            
            
        }
    }

	private int listLabels() {
		int contTotal;
		//Obtendo as rótulos
		int cont;
		cont = 0;
		
		String expression = "//fieldset//fieldset//label[contains(text(), ':')]";
		//String expression = "//fieldset//fieldset//span//label[contains(text(), ':')]";
		List<WebElement> labelEements =this.findElements(By.xpath(expression));
			        		
		for (WebElement element : labelEements) {
			log.write("Rótulo:  :" + extractedAttribute(element));
			
		    cont++;
		}
		contTotal = cont;
		return contTotal;
	}

    
    private int listImages() {
		int cont;
		cont = 0;
		
		List<WebElement> imageElements = this.findElements(By.tagName("img"));
		
		for (WebElement element : imageElements) {
			log.write("Lista rótulos :" + extractedAttribute(element));
		    cont++;
		}
		
		
		return cont;
	}
    
    /*
    private int listLabels() {
		int cont;
		cont = 0;
		
		//Para este caso vamos primeiro recuperar o elemento que agrupa os labels
		WebElement elementContainer = this.findElement(By.tagName("span"));
		
		List<WebElement> labelElements = elementContainer.findElements(By.tagName("label"));
		
		for (WebElement element : labelElements) {
			log.write("Lista rótulos :" + extractedAttribute(element));
		    cont++;
		}
		
		List<WebElement> legendElements = this.findElements(By.tagName("legend"));
		
		for (WebElement element : legendElements) {
			log.write("Lista rótulos :" + extractedAttribute(element));
		    cont++;
		}
		
		return cont;
	}
	*/
	private String extractedAttribute(WebElement element) {
		
		if(element.getAttribute("name")==null || element.getAttribute("name").isEmpty())
		{
			if(!element.getAttribute("id").isEmpty())
				return element.getAttribute("id");
			else
				return element.getText();
		}
		
		if(element.getAttribute("name").isBlank()) return element.getTagName();
		//if(element.getAttribute("name").isBlank()) return element.getAttribute("id");
		
		return element.getAttribute("name");
	}
    
	private int listSelects() {
		int cont;
		cont = 0;
		
		List<WebElement> selectElements = this.findElements(By.tagName("select"));
		
		for (WebElement element : selectElements) {
			log.write("Listas suspensas:" + extractedAttribute(element));
		    cont++;
		}
		return cont;
	}

	private int listInputs() {
		int cont = 0;
		List<WebElement> inputElements = this.findElements(By.tagName("input"));
		
		
		for (WebElement element : inputElements) {
		    log.write("Caixa Texto ou Botão :" + extractedAttribute(element));
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
		
		
		List<WebElement> imgElements = this.findElements(By.tagName("img"));
		
		
		for (WebElement element : imgElements) {
		    log.write("Imagem ou Imagem que se passa por Botão :" + extractedAttribute(element));
		    cont++;
		}
		
		return cont;
	}

    private void notFound(String label) {
        log.write("Element not found: " + label);
    }

    public static void main(String[] args) {
        BotExecutor.run(new FirstBotWeb(), args);
    }
}

