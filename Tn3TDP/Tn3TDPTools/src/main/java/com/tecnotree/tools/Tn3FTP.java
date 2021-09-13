package com.tecnotree.tools;


import org.apache.commons.net.ftp.FTP; // Nos permite indicar si transfer BINARY o ASCII
import org.apache.commons.net.ftp.FTPClient; // Para FTP plano
import java.io.File;
import java.io.FileInputStream; // Abrir y leer el fichero
import java.io.IOException;
import java.io.InputStream;

public class Tn3FTP {

	public boolean upload (String[] args) {
		String server = args[0];
    int port = Integer.parseInt(args[1]);
    String user = args[2];
    String pass = args[3];
    String file = args[4];
    FTPClient ftpClient = new FTPClient();
    
    try {
      ftpClient.connect(server, port);
      ftpClient.login(user, pass);
      ftpClient.enterLocalPassiveMode();

      ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

      // APPROACH #1: uploads first file using an InputStream
      File firstLocalFile = new File(file);
      String firstRemoteFile = firstLocalFile.getName();
      InputStream inputStream = new FileInputStream(firstLocalFile);

      System.out.println("Start uploading first file");
      boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
      inputStream.close();
      
      if (done) {      	
      	System.out.println("The first file is uploaded successfully.");
      	return true;
      	}      
    	}
    catch (IOException ex) {
      System.out.println("Error: " + ex.getMessage());
      ex.printStackTrace();      
    	}
    finally {
      try {
        if (ftpClient.isConnected()) {
            ftpClient.logout();
            ftpClient.disconnect();
        	}
      	}
      catch (IOException ex) {
        ex.printStackTrace();
      	}
    	}
		return false;
    }
	
	public static boolean uploadd (String[] args) {
		String server = args[0];
    int port = Integer.parseInt(args[1]);
    String user = args[2];
    String pass = args[3];
    String file = args[4];
    FTPClient ftpClient = new FTPClient();
    
    try {
      ftpClient.connect(server, port);
      ftpClient.login(user, pass);
      ftpClient.enterLocalPassiveMode();

      ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

      // APPROACH #1: uploads first file using an InputStream
      File firstLocalFile = new File(file);
      String firstRemoteFile = firstLocalFile.getName();
      InputStream inputStream = new FileInputStream(firstLocalFile);

      System.out.println("Start uploading first file");
      boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
      inputStream.close();
      
      if (done) {      	
      	System.out.println("The first file is uploaded successfully.");
      	return true;
      	}      
    	}
    catch (IOException ex) {
      System.out.println("Error: " + ex.getMessage());
      ex.printStackTrace();      
    	}
    finally {
      try {
        if (ftpClient.isConnected()) {
            ftpClient.logout();
            ftpClient.disconnect();
        	}
      	}
      catch (IOException ex) {
        ex.printStackTrace();
      	}
    	}
		return false;
    }
}
