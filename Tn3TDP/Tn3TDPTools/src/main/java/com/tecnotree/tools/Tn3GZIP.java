package com.tecnotree.tools;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Tn3GZIP {
	private static final byte[] BUFFER = new byte[1024];
	
  public boolean gzip (File archivo) throws IOException {
  	return gzip(archivo, true);
		}	
  
  public boolean gzip (File archivo, boolean deleteOrg) {                
  	GZIPOutputStream out = null;
  	FileInputStream in = null;
  	
  	try {
      int len;
      out = new GZIPOutputStream (new FileOutputStream(archivo.getAbsolutePath() + ".gz"));
      in = new FileInputStream(archivo);
      
      while ((len = in.read(BUFFER)) != -1)
        out.write(BUFFER, 0, len);
      
      out.close(); in.close();
      
      if (deleteOrg)
      	archivo.delete();
    	}
    catch (IOException ex) {
      System.err.println(ex.getMessage());
      return false;
    	}
    
    return true;
  	}

  public boolean gunzip (File archivo) throws IOException {
  	return gunzip(archivo, true);
		}
  
  public boolean gunzip (File archivo, boolean deleteOrg) {
  	GZIPInputStream in = null;
  	FileOutputStream out = null;
  	//extrae la extension *.zip
    String salida = archivo.getAbsolutePath().replaceFirst("[.][^.]+$", "");        
    
    try {            
      int len;
      
      in = new GZIPInputStream(new FileInputStream(archivo));
      out = new FileOutputStream(salida);
      		
      while ((len = in.read(BUFFER)) != -1)
        out.write(BUFFER, 0, len);
      
      out.close(); in.close();
      
      if (deleteOrg)
      	archivo.delete();
    	} 
    catch (IOException ex) {      
      System.err.println(ex.getMessage());
      return false;
    	}
    
    return true;
  	}
  
  public static boolean gzipp (File archivo, boolean deleteOrg) {                
	  	GZIPOutputStream out = null;
	  	FileInputStream in = null;
	  	
	  	try {
	      int len;
	      out = new GZIPOutputStream (new FileOutputStream(archivo.getAbsolutePath() + ".gz"));
	      in = new FileInputStream(archivo);
	      
	      while ((len = in.read(BUFFER)) != -1)
	        out.write(BUFFER, 0, len);
	      
	      out.close(); in.close();
	      
	      if (deleteOrg)
	      	archivo.delete();
	    	}
	    catch (IOException ex) {
	      System.err.println(ex.getMessage());
	      return false;
	    	}
	    
	    return true;
	 }
  
}