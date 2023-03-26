package br.com.main;

import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class WriteToFile {
	
	private FileWriter myWriter;		
	
	
  public WriteToFile()
  {
	  try {
	    	
	      myWriter = new FileWriter("output.txt");
	      myWriter.write ("START PROCESS\n");
	      
	      
	    } catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
  }
	
	
  public void write(String arg) {
    try {
    	
      myWriter.write(arg+ "\n");
      
      
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
  
  public void close()
  {
	  try {
	    	
	      myWriter.close();
	      System.out.println("Successfully close the file.");
	      
	    } catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	  
  }
  
}