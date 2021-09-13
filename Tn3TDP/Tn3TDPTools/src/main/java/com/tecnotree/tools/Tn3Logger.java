package com.tecnotree.tools;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.*;

@SuppressWarnings("unused")
public class Tn3Logger {
	private Logger logger;

  public Tn3Logger(String logClass) {
    logger = Logger.getLogger(logClass);
    configure();
  	}
  
  public Tn3Logger(String logClass, String logsDirectoryFolder) {
	  logger = Logger.getLogger(logClass);
	  configure(logsDirectoryFolder);
  }

  private void configure() {
    try {
    	logger.setUseParentHandlers(false); //To remove the console handler put false, otherwise put true
    	String logsDirectoryFolder = "logs";
    	Files.createDirectories(Paths.get(logsDirectoryFolder));
    	FileHandler fileHandler = new FileHandler(logsDirectoryFolder + File.separator + logger.getName() + "_" + getCurrentDateString() + ".log", true);
    	logger.addHandler(fileHandler);
    	SimpleFormatter formatter = new SimpleFormatter(){
	   
    		@Override
    		public String format(LogRecord record) {            
    			return getCurrentTimeString() + " " + record.getLevel() + ": "
    					+ record.getLoggerName() + " - " + record.getMessage() + "\n";
    		}
  		};
  		
  		fileHandler.setFormatter(formatter);
    }catch (IOException e) {
    	e.printStackTrace();
    }

    addCloseHandlersShutdownHook();
  }
  
  private void configure(String logsDirectoryFolder) {
	    try {
	    	logger.setUseParentHandlers(false); //To remove the console handler put false, otherwise put true
	    	Files.createDirectories(Paths.get(logsDirectoryFolder));
	    	FileHandler fileHandler = new FileHandler(logsDirectoryFolder + File.separator + logger.getName() + "_" + getCurrentDateString() + ".log", true);
	    	logger.addHandler(fileHandler);
	    	SimpleFormatter formatter = new SimpleFormatter(){
		   
	    		@Override
	    		public String format(LogRecord record) {            
	    			return getCurrentTimeString() + " " + record.getLevel() + ": "
	    					+ record.getLoggerName() + " - " + record.getMessage() + "\n";
	    		}
	  		};
	  		
	  		fileHandler.setFormatter(formatter);
	    }catch (IOException e) {
	    	e.printStackTrace();
	    }

	    addCloseHandlersShutdownHook();
	  }

  private void addCloseHandlersShutdownHook() {
	  Runtime.getRuntime().addShutdownHook(new Thread(() ->
      {
        // Close all handlers to get rid of empty .LCK files
        for (Handler handler : logger.getHandlers()) {
          handler.close();
        	}
      }));
  }
  
  private String getCurrentTimeString() {
	  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  return dateFormat.format(new Date());
  }
  
  private String getCurrentDateString() {
	  DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	  return dateFormat.format(new Date());
  }

  public void severe(Exception exception) {
	  logger.log(Level.SEVERE, exception.getMessage(), exception);
  }
  
  public void info(String msg) {
	  logger.log(Level.INFO, msg);
  }
}
