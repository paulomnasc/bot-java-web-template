package br.com.main;

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
            browse("https://botcity.dev");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Stop the browser and clean up
            stopBrowser();
        }
    }

    private void notFound(String label) {
        System.out.println("Element not found: " + label);
    }

    public static void main(String[] args) {
        BotExecutor.run(new FirstBotWeb(), args);
    }
}

