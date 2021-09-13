/**
 * Class store the methods than reference a MySQL data base
 */
package com.tecnotree.tools;


import java.io.IOException;
import java.util.HashMap;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;

/**
 * @author franklin
 *
 */
public class Tn3ElasticSearch {
	
	private RestHighLevelClient client;
	private String ipServer = "";
	private int portServer = 9200;
	private String schema = "";
	private String index = "";
	private int sizeHits = 10;
		
	public Tn3ElasticSearch() {}
	
	public Tn3ElasticSearch(String ipServer,int portServer, String schema, String index) {
		this.ipServer = ipServer;
		this.portServer = portServer;
		this.schema = schema;
		this.index = index;
	}
	
	public RestHighLevelClient startConnection() {
		
		this.client = new RestHighLevelClient(
				RestClient.builder(new HttpHost(this.ipServer, this.portServer, this.schema)));
		
		return this.client;
	}
	
	public void finishConnection() throws IOException {
	    if (this.client != null) {
	    	this.client.close();
	    	this.client = null;
	    }
	}
	

	public RestHighLevelClient getConn() {
		return this.client;
	}

	public void setConn(RestHighLevelClient client) {
		this.client = client;
	}
	
	public RestHighLevelClient getClient() {
		return client;
	}

	public void setClient(RestHighLevelClient client) {
		this.client = client;
	}

	public String getIpServer() {
		return ipServer;
	}

	public void setIpServer(String ipServer) {
		this.ipServer = ipServer;
	}

	public int getPortServer() {
		return portServer;
	}

	public void setPortServer(int portServer) {
		this.portServer = portServer;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}
	
	public int getSizeHits() {
		return sizeHits;
	}

	public void setSizeHits(int sizeHits) {
		this.sizeHits = sizeHits;
	}

	public String getDataRequest(SearchHit searchH, String field) {
    	@SuppressWarnings("rawtypes")
		HashMap request = (HashMap) searchH.getSourceAsMap().get("request");
    	//System.out.println("request:" + request);
		
		@SuppressWarnings("rawtypes")
		HashMap Request = (HashMap) request.get("Request");
		System.out.println(" - " + field + " " +(String) Request.get(field));
		return Request.get(field) != null ? (String) Request.get(field) : "blank";
    }
    
    public String getCodeQuery(SearchHit searchH, String field) {
    	@SuppressWarnings("rawtypes")
		HashMap response = (HashMap) searchH.getSourceAsMap().get("response");
    	//System.out.println("response.get(\""+response+"\"):" + response.get(field));
		
		@SuppressWarnings("rawtypes")
		HashMap responseQuery = (HashMap) response.get(field);
		if(responseQuery != null) {
			@SuppressWarnings("rawtypes")
			HashMap responseCode = (HashMap) responseQuery.get("error");
					
			System.out.println(" - " + field + " " +(Integer) responseCode.get("code"));
			return String.valueOf((Integer) responseCode.get("code"));
		}
		
		return "blank";
    }

}
