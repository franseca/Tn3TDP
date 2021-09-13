/**
 * Class store the methods than reference a MySQL data base
 */
package com.tecnotree.tools;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * @author franklin
 *
 */
public class Tn3MySQL {
	
	private String dbDriver = "com.mysql.cj.jdbc.Driver";
	private Connection conn;
	private Session session= null;
		
	public Tn3MySQL() {}
	
	public Tn3MySQL(String ipServer, String portServer, String schema, String user, String password) throws ClassNotFoundException, SQLException {
		Class.forName(dbDriver);
		String sURL = "jdbc:mysql://"+ipServer+":"+portServer+"/"+schema+"";
		this.conn = DriverManager.getConnection(sURL, user, password);
	}
	
	public Connection startConnection(String ipServer, String portServer, String schema, String user, String password) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		Class.forName(dbDriver);
		String sURL = "jdbc:mysql://"+ipServer+":"+portServer+"/"+schema;
		this.conn = DriverManager.getConnection(sURL, user, password);
		
		return this.conn;
	}
	
	public void finalizarConexion() throws SQLException {
	    if (this.conn != null) {
	    	this.conn.close();
	    	this.conn = null;
	    }
	}
	

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}


	public void doSshTunnel(String strSshUser, String strSshPassword, String strSshHost, int nSshPort,
          String strRemoteHost, int nLocalPort, int nRemotePort) throws JSchException {
  	
	  	
	  	
	  	Properties config = new Properties(); 
	  	config.put("StrictHostKeyChecking", "no");
	  	
	  	JSch jsch = new JSch();
	  	session=jsch.getSession(strSshUser, strSshHost, nSshPort);
	  	session.setPassword(strSshPassword);
	  	session.setConfig(config);
	  	session.connect();
	  	session.setPortForwardingL(nLocalPort, strRemoteHost, nRemotePort);
	  	//System.out.println("Connected");
	  	//int assinged_port=session.setPortForwardingL(nLocalPort, strRemoteHost, nRemotePort);
	  	//System.out.println("localhost:"+assinged_port+" -> "+strRemoteHost+":"+nRemotePort);
	  	//System.out.println("Port Forwarded");
	  }
	
	public void closeSshTunnel() throws JSchException {
	  	
		session.disconnect();
	  }
}
