package com.danier.solr.slorsingle;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;

import com.danier.solr.server.AbstractSolrServer;
import com.danier.solr.server.Constants;

public class SolrServerImpl extends AbstractSolrServer {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(AbstractSolrServer.class);
	
	private SolrServer solrServer;
	
	@Override
	public SolrServer getSolrServer() {
		if(solrServer == null){
			solrServer = new HttpSolrServer(Constants.SOLR_URL);
		}
		return solrServer;
	}
}
