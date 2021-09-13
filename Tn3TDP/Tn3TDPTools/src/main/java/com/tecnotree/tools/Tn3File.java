package com.tecnotree.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Tn3File {
	
	/**
	  * Method copies a file
	  * 
	  * @param source
	  * @param dest
	  * @throws IOException
	  */
	public static void copyFileUsingStream(File source, File dest) throws IOException {
		 InputStream is = null;
		 OutputStream os = null;
		 try {
			 is = new FileInputStream(source);
			 os = new FileOutputStream(dest);
			 byte[] buffer = new byte[1024];
			 int length;
			 while ((length = is.read(buffer)) > 0) {
				 os.write(buffer, 0, length);
			 }
		 } finally {
			 is.close();
			 os.close();
		 }
	 }
}
